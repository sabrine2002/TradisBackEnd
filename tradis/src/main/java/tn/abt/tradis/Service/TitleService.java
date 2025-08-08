package tn.abt.tradis.Service;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.abt.tradis.Config.TitleCreationRequest;
import tn.abt.tradis.Config.TitleUpdateRequest;
import tn.abt.tradis.Config.TitleWithLabelsDTO;
import tn.abt.tradis.Entites.*;
import tn.abt.tradis.Repository.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TitleService {
    private static final Logger logger = LoggerFactory.getLogger(TitleService.class);

    @Autowired
    private TitleRepository titleRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ParameterRepository parameterRepository;
    @Autowired
    private ParameterRepository paramRepository;

    @Transactional
    public Title createTitle(TitleCreationRequest request) {
        logger.info("Creating title with request: {}", request);
        logger.info("Received TitleCreationRequest: clientId={}, clientName={}, numDom={}",
                request.getClientId(), request.getClientName(), request.getNumDom());

        // Validation complète des champs du request
        Pnom titleCode = getTitleCode(request.getTitleCode());
        Pnom titleStatus = paramRepository.findByCnomAndCacc("007", "NON_APURE")
                .orElseThrow(() -> {
                    logger.error("NON_APURE status not found: cnom='007', cacc='NON_APURE'");
                    return new IllegalArgumentException("'NON_APURE' status not found");
                });
        String currencyCode = request.getCurrencyCode() != null ? request.getCurrencyCode().trim() : null;
        Pnom currency = paramRepository.findByCnomAndLabel4("014", currencyCode)
                .orElseThrow(() -> {
                    logger.error("Currency code not found: cnom='014', label4='{}'", currencyCode);
                    return new IllegalArgumentException("Currency code invalid: " + currencyCode);
                });
        Client client = getClient(request.getClientId(), request.getClientName());

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
        title.setAdvancePaymentAmount(request.getAdvancePaymentAmount());
        title.setCancelled(false);
        title.setClearanceDate(null);
        title.setTitleCode(titleCode);
        title.setTitleStatus(titleStatus);
        title.setCurrencyTitle(currency);
        title.setClient(client);
        titleRepository.save(title);
        return title;
    }
    private Pnom getCurrencyLabel(String currencyCode) {
        return parameterRepository.findByCnomAndLabel4("014", currencyCode)
                .or(() -> parameterRepository.findByCnomAndLabel1("014", currencyCode))
                .or(() -> parameterRepository.findByCnomAndLabel2("014", currencyCode))
                .or(() -> parameterRepository.findByCnomAndLabel3("014", currencyCode))
                .orElseThrow(() -> {
                    logger.error("Devise non trouvée ou non supportée: {}", currencyCode);
                    return new IllegalArgumentException("Devise non supportée: " + currencyCode);
                });
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


    private Pnom getCurrency(String currencyCode) {
        return parameterRepository.findByCnomAndLabel1("014", currencyCode)
                .or(() -> parameterRepository.findByCnomAndLabel2("014", currencyCode))
                .or(() -> parameterRepository.findByCnomAndLabel3("014", currencyCode))
                .orElseThrow(() -> {
                    logger.error("Devise non trouvée ou non supportée: {}", currencyCode);
                    return new IllegalArgumentException("Devise non supportée: " + currencyCode);
                });
    }

    private Client getClient(Long clientId, String firstname) {
        if (clientId == null || firstname == null || firstname.trim().isEmpty()) {
            logger.error("Validation failed: clientId={}, firstname={}", clientId, firstname);
            throw new IllegalArgumentException("L'ID client et le prénom sont obligatoires");
        }
        String normalizedFirstname = firstname.trim();
        Optional<Client> client = clientRepository.findByIdCliAndFirstname(clientId, normalizedFirstname);
        logger.info("Client query result for id={} and firstname={}: {}", clientId, normalizedFirstname, client.isPresent());
        return client.orElseThrow(() -> new IllegalArgumentException("Client non trouvé pour ID: " + clientId + " et prénom: " + normalizedFirstname));
    }
    public List<Client> getAllClients() {
        return clientRepository.findAll();
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



    public List<Title> getAllTitles() {
        return titleRepository.findAll();
    }

    public Title getTitleById(String numDom) {
        return titleRepository.findById(numDom)
                .orElseThrow(() -> new IllegalArgumentException("Titre introuvable"));
    }
    @Transactional
    public Title updateTitle(String numDom, TitleUpdateRequest request) {
        logger.info("Updating title {} with request: {}", numDom, request);

        if (request.getTotalAmountCurr().compareTo(BigDecimal.ZERO) <= 0 ||
                request.getTotalAmountTND().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Total amounts must be positive");
        }

        Title title = titleRepository.findByNumDom(numDom)
                .orElseThrow(() -> new IllegalArgumentException("Title not found: " + numDom));
        Pnom titleCode = getTitleCode(request.getTitleCode());
        Pnom titleStatus = paramRepository.findByCnomAndCacc("007", "NON_APURE")
                .orElseThrow(() -> {
                    logger.error("NON_APURE status not found: cnom='007', cacc='NON_APURE'");
                    return new IllegalArgumentException("'NON_APURE' status not found");
                });
        String currencyCode = request.getCurrencyCode() != null ? request.getCurrencyCode().trim() : null;
        Pnom currency = paramRepository.findByCnomAndLabel4("014", currencyCode)
                .orElseThrow(() -> {
                    logger.error("Currency code not found: cnom='014', label4='{}'", currencyCode);
                    return new IllegalArgumentException("Currency code invalid: " + currencyCode);
                });
        Client client = getClient(request.getClientId(), request.getClientName());

        title.setDomYear(request.getDomYear());
        title.setDomDate(request.getDomDate());
        title.setEndTitleDate(request.getEndTitleDate());
        title.setContractNum(request.getContractNum());
        title.setContractDate(request.getContractDate());
        title.setTotalAmountCurr(request.getTotalAmountCurr());
        title.setTotalAmountTND(request.getTotalAmountTND());
        title.setAdvancePaymentAmount(request.getAdvancePaymentAmount());
        title.setTitleCode(titleCode);
        title.setTitleStatus(titleStatus);
        title.setCurrencyTitle(currency);
        title.setClient(client);
        title.setLastUpdatedDate(LocalDateTime.now());

        BigDecimal usedAmountCurr = title.getUsedAmountCurr() != null ? title.getUsedAmountCurr() : BigDecimal.ZERO;
        BigDecimal usedAmountTND = title.getUsedAmountTND() != null ? title.getUsedAmountTND() : BigDecimal.ZERO;
        title.setRemainingAmountCurr(request.getTotalAmountCurr().subtract(usedAmountCurr));
        title.setRemainingAmountTND(request.getTotalAmountTND().subtract(usedAmountTND));
        titleRepository.save(title);
        return title;
    }

    private Pnom getTitleStatus(String statusCode) {
        return paramRepository.findByCnomAndCacc("007", statusCode)
                .orElseThrow(() -> new IllegalArgumentException("Invalid status code: " + statusCode));
    }

    public List<TitleWithLabelsDTO> getAllTitlesWithLabels() {
        List<Title> titles = titleRepository.findAll();
        return titles.stream()
                .map(TitleWithLabelsDTO::new)
                .collect(Collectors.toList());
    }

}