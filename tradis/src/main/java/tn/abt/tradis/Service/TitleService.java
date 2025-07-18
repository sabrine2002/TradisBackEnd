// src/main/java/tn/abt/tradis/Service/TitleService.java
package tn.abt.tradis.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.abt.tradis.Config.TitleCreationRequest;
import tn.abt.tradis.Entites.*;
import tn.abt.tradis.Repository.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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

    @Transactional
    public Title createTitle(TitleCreationRequest request) {

        String titleCode = request.getTitleCode() != null ? request.getTitleCode().trim() : null;
        String titleStatus = request.getTitleStatusCode() != null ? request.getTitleStatusCode().trim() : null;

        if (titleCode == null || titleStatus == null) {
            throw new IllegalArgumentException("Title code and status must not be null");
        }

        // Updated to match frontend dropdown
        List<String> allowedTitleCnomCodes = List.of("021", "022", "031", "033");

        Pnom titleCodeParam = allowedTitleCnomCodes.stream()
                .map(cnom -> parameterRepository.findByCnomAndCacc(cnom, titleCode))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Invalid title code: " + titleCode + ". Not found in allowed cnom values: " + allowedTitleCnomCodes));

        List<String> allowedStatusCnomCodes = List.of("007", "008");

        Pnom titleStatusParam = allowedStatusCnomCodes.stream()
                .map(cnom -> parameterRepository.findByCnomAndCacc(cnom, titleStatus))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Invalid title status: " + titleStatus + ". Not found in cnom values: " + allowedStatusCnomCodes));

        if (request.getClientId() == null) {
            throw new IllegalArgumentException("Client ID cannot be null");
        }
        if (request.getUserId() == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Client not found with ID: " + request.getClientId()));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + request.getUserId()));

        String currencyCode = request.getCurrencyCode() != null ? request.getCurrencyCode().trim() : null;

        List<Pnom> currencyList = parameterRepository.findAllByCnomAndLabel6("013", currencyCode);

        if (currencyList.isEmpty()) {
            logger.error("Currency code not found: cnom='013', label6='{}'", currencyCode);
            throw new IllegalArgumentException("Currency code invalid: " + currencyCode);
        }

        if (currencyList.size() > 1) {
            logger.warn("More than one currency found for code '{}', using the first one", currencyCode);
        }

        Pnom currency = currencyList.get(0);

        Title title = new Title();
        title.setNumDom(request.getNumDom());
        title.setDomYear(request.getDomYear());
        title.setDomDate(request.getDomDate());
        title.setEndTitleDate(request.getEndTitleDate());
        title.setContractNum(request.getContractNum());
        title.setContractDate(request.getContractDate());
        title.setTotalAmountCurr(request.getTotalAmountCurr());
        title.setTotalAmountTND(request.getTotalAmountTND());

        if (request.getTotalAmountCurr() != null) {
            title.setUsedAmountCurr(BigDecimal.ZERO);
            title.setRemainingAmountCurr(request.getTotalAmountCurr());
        }

        if (request.getTotalAmountTND() != null) {
            title.setUsedAmountTND(BigDecimal.ZERO);
            title.setRemainingAmountTND(request.getTotalAmountTND());
        }

        title.setIsAdvancePayment(request.getIsAdvancePayment());
        title.setAdvancePaymentAmount(request.getAdvancePaymentAmount());
        title.setIsCancelled(false);
        title.setClearanceDate(null);
        title.setClient(client);
        title.setUser(user);
        title.setTitleStatus(titleStatusParam);
        title.setTitleCode(titleCodeParam);
        title.setCurrencyTitle(currency);

        return titleRepository.save(title);
    }

    public List<Title> getAllTitles() {
        return titleRepository.findAll();
    }

    public Title getTitleById(String numDom) {
        return titleRepository.findById(numDom)
                .orElseThrow(() -> new IllegalArgumentException("Title not found: " + numDom));
    }
}