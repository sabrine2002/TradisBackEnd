package tn.abt.tradis.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.abt.tradis.Config.SettlementCreationRequest;
import tn.abt.tradis.Entites.Pnom;
import tn.abt.tradis.Entites.Settlement;
import tn.abt.tradis.Entites.Title;
import tn.abt.tradis.Repository.ParameterRepository;
import tn.abt.tradis.Repository.SettlementRepository;
import tn.abt.tradis.Repository.TitleRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SettlementService {
    private static final Logger logger = LoggerFactory.getLogger(SettlementService.class);
    @Autowired
    private SettlementRepository settlementRepository;
    @Autowired
    private TitleRepository titleRepository;
    @Autowired
    private ParameterRepository paramRepository;

    @Transactional
    public Settlement createSettlement(SettlementCreationRequest request) {
        logger.info("Creating settlement with request: {}", request);

        Title title = titleRepository.findById(request.getTitleId())
                .orElseThrow(() -> new IllegalArgumentException("Title not found: " + request.getTitleId()));

        String countryCode = request.getSettlementCountryCode() != null ? request.getSettlementCountryCode().trim() : null;
        Pnom countryParam = paramRepository.findByCnomAndCacc("013", countryCode)
                .orElseThrow(() -> {
                    logger.error("Country code not found: cnom='013', cacc='{}'", countryCode);
                    return new IllegalArgumentException("Country code invalid: " + countryCode);
                });

        String currencyCode = request.getSettlementCurrencyCode() != null ? request.getSettlementCurrencyCode().trim() : null;
        Pnom currencyParam = paramRepository.findByCnomAndLabel6("013", currencyCode)
                .orElseThrow(() -> {
                    logger.error("Currency code not found: cnom='013', label6='{}'", currencyCode);
                    return new IllegalArgumentException("Currency code invalid: " + currencyCode);
                });

        String productCode = request.getProductCode() != null ? request.getProductCode().trim() : null;
        Pnom productParam = paramRepository.findByCnomAndCacc("011", productCode)
                .orElseThrow(() -> {
                    logger.error("Product code not found: cnom='011', cacc='{}'", productCode);
                    return new IllegalArgumentException("Product code invalid: " + productCode);
                });

        validateCurrency(title, currencyCode);
        validateAmounts(title, request.getSettlementAmountLocalCurrency());

        Settlement settlement = new Settlement();
        settlement.setTitle(title);
        settlement.setSettlementDate(LocalDate.now());
        settlement.setSettlementAmountLC(request.getSettlementAmountLocalCurrency());
        settlement.setSettlementAmountFC(request.getSettlementAmountForeignCurrency());
        settlement.setSettlementCountry(countryParam);
        settlement.setCurrencySettlement(currencyParam);
        settlement.setSettlementProduct(productParam);

        settlementRepository.save(settlement);
        logger.info("Settlement saved for title: {}", request.getTitleId());

        updateTitleAmounts(title, request.getSettlementAmountLocalCurrency());

        return settlement;
    }

    private void validateCurrency(Title title, String settlementCurrencyCode) {
        String titleCurrencyCode = (title.getCurrencyTitle() != null && title.getCurrencyTitle().getLabel6() != null)
                ? title.getCurrencyTitle().getLabel6().trim()
                : null;
        if (titleCurrencyCode == null || !titleCurrencyCode.equals(settlementCurrencyCode)) {
            logger.error("Currency mismatch: titleCurrency='{}', settlementCurrencyCode='{}'", titleCurrencyCode, settlementCurrencyCode);
            throw new IllegalArgumentException("Currency mismatch: title=" + titleCurrencyCode + ", settlement=" + settlementCurrencyCode);
        }
    }

    private void validateAmounts(Title title, BigDecimal settlementAmount) {
        if (settlementAmount == null || settlementAmount.compareTo(BigDecimal.ZERO) <= 0) {
            logger.error("Invalid settlement amount: {}", settlementAmount);
            throw new IllegalArgumentException("Settlement amount must be positive");
        }
        if (settlementAmount.compareTo(title.getRemainingAmountCurr()) > 0) {
            logger.error("Settlement amount exceeds available amount. Available: {}, Requested: {}",
                    title.getRemainingAmountCurr(), settlementAmount);
            throw new IllegalArgumentException("Settlement amount exceeds available amount. Available: " + title.getRemainingAmountCurr());
        }
    }

    private void updateTitleAmounts(Title title, BigDecimal settlementAmount) {
        BigDecimal newUsed = title.getUsedAmountCurr().add(settlementAmount);
        BigDecimal newRemaining = title.getRemainingAmountCurr().subtract(settlementAmount);

        title.setUsedAmountCurr(newUsed);
        title.setRemainingAmountCurr(newRemaining);

        if (newRemaining.compareTo(BigDecimal.ZERO) == 0) {
            Pnom apureStatus = paramRepository.findByCnomAndCacc("008", "APURE")
                    .orElseThrow(() -> {
                        logger.error("APURE status not found: cnom='008', cacc='APURE'");
                        return new IllegalArgumentException("'APURE' status not found");
                    });
            title.setTitleStatus(apureStatus);
            title.setClearanceDate(LocalDate.now());
            logger.info("Title status updated to APURE for title: {}", title.getNumDom());
        }

        titleRepository.save(title);
        logger.info("Title amounts updated: used={}, remaining={}", newUsed, newRemaining);
    }

    public List<Settlement> getAllSettlements() {
        return settlementRepository.findAll();
    }

    public Settlement getSettlementById(Long id) {
        return settlementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Règlement non trouvé: " + id));
    }
}