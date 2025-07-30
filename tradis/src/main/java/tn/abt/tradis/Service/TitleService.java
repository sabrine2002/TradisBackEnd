package tn.abt.tradis.Service;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.abt.tradis.Config.TitleCreationRequest;
import tn.abt.tradis.Entites.*;
import tn.abt.tradis.Repository.*;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TitleService {
    private static final Logger logger = LoggerFactory.getLogger(TitleService.class);

    @Autowired private TitleRepository titleRepository;
    @Autowired private ClientRepository clientRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private ParameterRepository parameterRepository;

    @Transactional
    public Title createTitle(TitleCreationRequest request) {
        logger.info("Creating title with request: {}", request);

        // Validation des champs
        validateRequest(request);

        // Récupération des paramètres
        Pnom titleCode = getTitleCode(request.getTitleCode());
        Pnom titleStatus = getTitleStatus(request.getTitleStatusCode());
        Pnom currency = getCurrency(request.getCurrencyCode());
        Client client = getClient(request.getClientId());
        User user = getUser(request.getUserId());

        // Construction et sauvegarde
        return buildAndSaveTitle(request, titleCode, titleStatus, currency, client, user);
    }

    private void validateRequest(TitleCreationRequest request) {
        if (request.getTitleCode() == null || request.getTitleCode().trim().isEmpty()) {
            throw new IllegalArgumentException("Le code titre est obligatoire");
        }
        if (request.getTitleStatusCode() == null || request.getTitleStatusCode().trim().isEmpty()) {
            throw new IllegalArgumentException("Le statut du titre est obligatoire");
        }
        if (request.getCurrencyCode() == null || request.getCurrencyCode().trim().isEmpty()) {
            throw new IllegalArgumentException("La devise est obligatoire");
        }

        // ✅ Valider ici les devises autorisées
        List<String> allowedCurrencies = List.of("USD", "EUR", "TND");
        if (!allowedCurrencies.contains(request.getCurrencyCode())) {
            throw new IllegalArgumentException("Devise non supportée: " + request.getCurrencyCode());
        }
    }

    private Pnom getTitleCode(String code) {
        List<String> allowedCodes = List.of("021", "022", "031", "033");
        if (!allowedCodes.contains(code)) {
            throw new IllegalArgumentException("Code titre invalide. Valeurs autorisées: " + allowedCodes);
        }

        return parameterRepository.findByCacc(code)
                .orElseThrow(() -> {
                    logger.error("Code titre non trouvé dans la base: {}", code);
                    return new IllegalArgumentException("Code titre non configuré: " + code);
                });
    }

    private Pnom getTitleStatus(String status) {
        List<String> allowedStatuses = List.of("007", "008");
        if (!allowedStatuses.contains(status)) {
            throw new IllegalArgumentException("Statut invalide. Valeurs autorisées: " + allowedStatuses);
        }

        return parameterRepository.findByCacc(status)
                .orElseGet(() -> {
                    // Create default status if not found
                    Pnom defaultStatus = new Pnom();
                    defaultStatus.setCacc(status);
                    defaultStatus.setLabel1(status.equals("007") ? "Active" : "Inactive");
                    return parameterRepository.save(defaultStatus);
                });
    }

    private Pnom getCurrency(String currencyCode) {
        return parameterRepository.findByCnomAndLabel1("014", currencyCode)
                .or(() -> parameterRepository.findByCnomAndLabel2("014", currencyCode))
                .or(() -> parameterRepository.findByCnomAndLabel3("014", currencyCode)) // Ajouté
                .orElseThrow(() -> {
                    logger.error("Devise non trouvée ou non supportée: {}", currencyCode);
                    return new IllegalArgumentException("Devise non supportée: " + currencyCode);
                });
    }



    private Client getClient(Long clientId) {
        if (clientId == null) {
            throw new IllegalArgumentException("L'ID client est obligatoire");
        }
        return clientRepository.findById(clientId)
                .orElseThrow(() -> {
                    logger.error("Client non trouvé avec ID: {}", clientId);
                    return new IllegalArgumentException("Client introuvable");
                });
    }

    private User getUser(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("L'ID utilisateur est obligatoire");
        }
        return userRepository.findById(userId)
                .orElseThrow(() -> {
                    logger.error("Utilisateur non trouvé avec ID: {}", userId);
                    return new IllegalArgumentException("Utilisateur introuvable");
                });
    }

    private Title buildAndSaveTitle(TitleCreationRequest request, Pnom titleCode,
                                    Pnom titleStatus, Pnom currency, Client client, User user) {
        Title title = new Title();
        title.setNumDom(request.getNumDom());
        title.setDomYear(request.getDomYear());
        title.setDomDate(request.getDomDate());
        title.setEndTitleDate(request.getEndTitleDate());
        title.setContractNum(request.getContractNum());
        title.setContractDate(request.getContractDate());
        title.setTotalAmountCurr(request.getTotalAmountCurr());
        title.setTotalAmountTND(request.getTotalAmountTND());
        title.setUsedAmountCurr(BigDecimal.ZERO);
        title.setUsedAmountTND(BigDecimal.ZERO);
        title.setRemainingAmountCurr(request.getTotalAmountCurr());
        title.setRemainingAmountTND(request.getTotalAmountTND());
        title.setIsAdvancePayment(request.getIsAdvancePayment());
        title.setAdvancePaymentAmount(request.getAdvancePaymentAmount());
        title.setIsCancelled(false);
        title.setClearanceDate(null);
        title.setTitleCode(titleCode);
        title.setTitleStatus(titleStatus);
        title.setCurrencyTitle(currency);
        title.setClient(client);
        title.setUser(user);

        return titleRepository.save(title);
    }

    public List<Title> getAllTitles() {
        return titleRepository.findAll();
    }

    public Title getTitleById(String numDom) {
        return titleRepository.findById(numDom)
                .orElseThrow(() -> new IllegalArgumentException("Titre introuvable"));
    }
}
