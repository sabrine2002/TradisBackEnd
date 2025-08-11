package tn.abt.tradis.Service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import tn.abt.tradis.Config.DeepSeekResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

@Service
public class ChatbotService {

    private static final Logger logger = LoggerFactory.getLogger(ChatbotService.class);
    private WebClient webClient;

    @Value("${deepseek.api.key}")
    private String apiKey;

    @org.springframework.beans.factory.annotation.Autowired
    private WebClient.Builder webClientBuilder;

    @PostConstruct
    public void init() {
        this.webClient = webClientBuilder
                .baseUrl("https://api.deepseek.com/v1")
                .build();
    }

    @Cacheable(value = "chatResponses", key = "#userMessage")
    public Mono<String> getChatResponse(String userMessage) {
        String sanitizedMessage = userMessage.replaceAll("[<>\"&]", "");
        logger.debug("Sending request to DeepSeek API with message: {}", sanitizedMessage);

        String requestBody = """
        {
          "model": "deepseek-chat",
          "messages": [{"role": "user", "content": "%s"}],
          "max_tokens": 1024,
          "temperature": 0.7
        }
        """.formatted(sanitizedMessage);

        return webClient.post()
                .uri("/chat/completions")
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(DeepSeekResponse.class)
                .map(response -> {
                    logger.debug("Received response: {}", response);
                    if (response.getChoices() != null && !response.getChoices().isEmpty()) {
                        return response.getChoices().get(0).getMessage().getContent();
                    } else {
                        logger.warn("No choices in response");
                        return "Désolé, pas de réponse disponible.";
                    }
                })
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(2))
                        .filter(throwable -> throwable instanceof WebClientResponseException.TooManyRequests)
                        .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> {
                            logger.error("Rate limit exceeded after {} attempts", retrySignal.totalRetries());
                            return new RuntimeException("Rate limit exceeded after retries");
                        }))
                .onErrorResume(WebClientResponseException.class, e -> {
                    logger.error("API error: Status {}, Message: {}", e.getStatusCode(), e.getResponseBodyAsString());
                    if (e.getStatusCode().value() == 429) {
                        return Mono.just("Désolé, trop de requêtes. Veuillez réessayer plus tard.");
                    }
                    return Mono.just("Erreur API : " + e.getStatusCode() + " - " + e.getMessage());
                })
                .onErrorResume(Exception.class, e -> {
                    logger.error("Unexpected error: {}", e.getMessage(), e);
                    return Mono.just("Une erreur est survenue : " + e.getMessage());
                });
    }
}