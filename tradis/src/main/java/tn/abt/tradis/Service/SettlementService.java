package tn.abt.tradis.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.abt.tradis.Config.SettlementCreationRequest;
import tn.abt.tradis.Config.SettlementUpdateRequest;
import tn.abt.tradis.Entites.*;
import tn.abt.tradis.Enum.SettlementStatus;
import tn.abt.tradis.Repository.ParameterRepository;
import tn.abt.tradis.Repository.SettlementRepository;
import tn.abt.tradis.Repository.TitlePayPivotRepository;
import tn.abt.tradis.Repository.TitleRepository;
import tn.abt.tradis.Config.SettlementWithLabelsDTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private TitlePayPivotRepository pivotRepository;

    @Transactional
    public Settlement createSettlement(SettlementCreationRequest request) {
        logger.info("Creating settlement with request: {}", request);

        Title title = titleRepository.findByNumDom(request.getTitleId())
                .orElseThrow(() -> {
                    logger.error("Title not found: {}", request.getTitleId());
                    return new IllegalArgumentException("Title not found: " + request.getTitleId());
                });

        String countryCode = request.getSettlementCountryCode() != null ? request.getSettlementCountryCode().trim() : null;
        Pnom countryParam = paramRepository.findByCnomAndCacc("013", countryCode)
                .orElseThrow(() -> {
                    logger.error("Country code not found: cnom='013', cacc='{}'", countryCode);
                    return new IllegalArgumentException("Country code invalid: " + countryCode);
                });

        String currencyCode = request.getSettlementCurrencyCode() != null ? request.getSettlementCurrencyCode().trim() : null;
        Pnom currencyParam = paramRepository.findByCnomAndLabel4("014", currencyCode)
                .orElseThrow(() -> {
                    logger.error("Currency code not found: cnom='014', label4='{}'", currencyCode);
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
        settlement.setSettlementStatus(SettlementStatus.VALIDATED);
        settlementRepository.save(settlement);
        logger.info("Settlement saved for title: {}", request.getTitleId());

        Client client = title.getClient();
        TitlePayPivot pivot = new TitlePayPivot();
        pivot.setTitle(title);
        pivot.setSettlement(settlement);
        pivot.setClient(client);
        pivotRepository.save(pivot);

        logger.info("Pivot entry saved for title {} and settlement {}", title.getNumDom(), settlement.getIdSettlement());

        updateTitleAmounts(title, request.getSettlementAmountLocalCurrency());

        return settlement;
    }

    @Transactional
    public Settlement updateSettlement(Long idSettlement, SettlementUpdateRequest request) {
        logger.info("Updating settlement with id: {}, request: {}", idSettlement, request);

        Settlement settlement = settlementRepository.findById(idSettlement)
                .orElseThrow(() -> {
                    logger.error("Settlement not found: {}", idSettlement);
                    return new IllegalArgumentException("Settlement not found: " + idSettlement);
                });

        Title title = titleRepository.findByNumDom(request.getTitleId())
                .orElseThrow(() -> {
                    logger.error("Title not found: NumDom={}", request.getTitleId());
                    return new IllegalArgumentException("Title not found: " + request.getTitleId());
                });

        String countryCode = request.getSettlementCountryCode() != null ? request.getSettlementCountryCode().trim() : null;
        Pnom countryParam = paramRepository.findByCnomAndCacc("013", countryCode)
                .orElseThrow(() -> {
                    logger.error("Country code not found: cnom='013', cacc='{}'", countryCode);
                    return new IllegalArgumentException("Country code invalid: " + countryCode);
                });

        String currencyCode = request.getSettlementCurrencyCode() != null ? request.getSettlementCurrencyCode().trim() : null;
        Pnom currencyParam = paramRepository.findByCnomAndLabel4("014", currencyCode)
                .orElseThrow(() -> {
                    logger.error("Currency code not found: cnom='014', label4='{}'", currencyCode);
                    return new IllegalArgumentException("Currency code invalid: " + currencyCode);
                });

        String productCode = request.getProductCode() != null ? request.getProductCode().trim() : null;
        Pnom productParam = paramRepository.findByCnomAndCacc("011", productCode)
                .orElseThrow(() -> {
                    logger.error("Product code not found: cnom='011', cacc='{}'", productCode);
                    return new IllegalArgumentException("Product code invalid: " + productCode);
                });

        validateCurrency(title, currencyCode);
        BigDecimal oldSettlementAmount = settlement.getSettlementAmountLC();
        validateAmounts(title, request.getSettlementAmountLocalCurrency(), oldSettlementAmount);

        settlement.setTitle(title);
        settlement.setSettlementDate(request.getSettlementDate() != null ? request.getSettlementDate() : LocalDate.now());
        settlement.setSettlementAmountLC(request.getSettlementAmountLocalCurrency());
        settlement.setSettlementAmountFC(request.getSettlementAmountForeignCurrency());
        settlement.setSettlementCountry(countryParam);
        settlement.setCurrencySettlement(currencyParam);
        settlement.setSettlementProduct(productParam);

        // Gestion du status (si présent dans la requête)
        if (request.getStatus() != null) {
            try {
                SettlementStatus newStatus = request.getStatus();
                settlement.setSettlementStatus(newStatus);
            } catch (IllegalArgumentException e) {
                logger.error("Invalid settlement status: {}", request.getStatus());
                throw new IllegalArgumentException("Invalid settlement status: " + request.getStatus());
            }
        }

        // Set the last updated date to the current timestamp
        settlement.setLastUpdatedDate(LocalDateTime.now());

        settlementRepository.save(settlement);
        logger.info("Settlement updated for id: {}, title: {}", idSettlement, request.getTitleId());

        TitlePayPivot pivot = pivotRepository.findBySettlementIdSettlement(settlement.getIdSettlement())
                .orElse(new TitlePayPivot());
        pivot.setTitle(title);
        pivot.setSettlement(settlement);
        pivot.setClient(title.getClient());
        pivotRepository.save(pivot);
        logger.info("Pivot entry updated for title {} and settlement {}", title.getNumDom(), idSettlement);

        updateTitleAmounts(title, request.getSettlementAmountLocalCurrency(), oldSettlementAmount);

        return settlement;
    }

    private void validateCurrency(Title title, String settlementCurrencyCode) {
        String titleCurrencyCode = (title.getCurrencyTitle() != null && title.getCurrencyTitle().getLabel3() != null)
                ? title.getCurrencyTitle().getLabel4().trim()
                : null;
        if (titleCurrencyCode == null || !titleCurrencyCode.equals(settlementCurrencyCode)) {
            logger.error("Currency mismatch: titleCurrency='{}', settlementCurrencyCode='{}'", titleCurrencyCode, settlementCurrencyCode);
            throw new IllegalArgumentException("Currency mismatch: title=" + titleCurrencyCode + ", settlement=" + settlementCurrencyCode);
        }
    }

    private void validateAmounts(Title title, BigDecimal newSettlementAmount) {
        if (newSettlementAmount == null || newSettlementAmount.compareTo(BigDecimal.ZERO) <= 0) {
            logger.error("Invalid settlement amount: {}", newSettlementAmount);
            throw new IllegalArgumentException("Settlement amount must be positive");
        }
        if (newSettlementAmount.compareTo(title.getRemainingAmountCurr()) > 0) {
            logger.error("Settlement amount exceeds available amount. Available: {}, Requested: {}",
                    title.getRemainingAmountCurr(), newSettlementAmount);
            throw new IllegalArgumentException("Settlement amount exceeds available amount. Available: " + title.getRemainingAmountCurr());
        }
    }

    private void validateAmounts(Title title, BigDecimal newSettlementAmount, BigDecimal oldSettlementAmount) {
        if (newSettlementAmount == null || newSettlementAmount.compareTo(BigDecimal.ZERO) <= 0) {
            logger.error("Invalid settlement amount: {}", newSettlementAmount);
            throw new IllegalArgumentException("Settlement amount must be positive");
        }

        BigDecimal adjustedRemaining = title.getRemainingAmountCurr()
                .add(oldSettlementAmount)
                .subtract(newSettlementAmount);

        if (adjustedRemaining.compareTo(BigDecimal.ZERO) < 0) {
            logger.error("Settlement amount exceeds available amount. Available: {}, Requested: {}, Old: {}",
                    title.getRemainingAmountCurr(), newSettlementAmount, oldSettlementAmount);
            throw new IllegalArgumentException("Settlement amount exceeds available amount. Available: " + title.getRemainingAmountCurr());
        }
    }

    private void updateTitleAmounts(Title title, BigDecimal newSettlementAmount) {
        BigDecimal newUsed = title.getUsedAmountCurr().add(newSettlementAmount);
        BigDecimal newRemaining = title.getRemainingAmountCurr().subtract(newSettlementAmount);

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

    private void updateTitleAmounts(Title title, BigDecimal newSettlementAmount, BigDecimal oldSettlementAmount) {
        BigDecimal amountDifference = newSettlementAmount.subtract(oldSettlementAmount);
        BigDecimal newUsed = title.getUsedAmountCurr().add(amountDifference);
        BigDecimal newRemaining = title.getRemainingAmountCurr().subtract(amountDifference);

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

    public SettlementWithLabelsDTO getSettlementById(Long id) {
        Settlement settlement = settlementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Règlement non trouvé: " + id));
        return new SettlementWithLabelsDTO(settlement);
    }

    public List<SettlementWithLabelsDTO> getAllSettlementsWithLabels() {
        List<Settlement> settlements = settlementRepository.findAll();
        return settlements.stream()
                .map(SettlementWithLabelsDTO::new)
                .collect(Collectors.toList());
    }



}