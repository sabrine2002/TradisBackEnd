package tn.abt.tradis.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import tn.abt.tradis.Service.ChatbotService;

@RestController
@RequestMapping("/api/chatbot")
public class ChatBotController {

    @Autowired
    private ChatbotService chatbotService;

    @PostMapping("/message")
    public Mono<ResponseEntity<String>> chat(@RequestBody String userMessage) {
        return chatbotService.getChatResponse(userMessage)
                .map(ResponseEntity::ok);
    }
}