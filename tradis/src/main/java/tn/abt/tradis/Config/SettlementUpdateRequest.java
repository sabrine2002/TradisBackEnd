package tn.abt.tradis.Config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.abt.tradis.Enum.SettlementStatus;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SettlementUpdateRequest {
    private String titleId;
    private BigDecimal settlementAmountLocalCurrency;
    private BigDecimal settlementAmountForeignCurrency;
    private LocalDate settlementDate;
    private String settlementCurrencyCode;
    private String settlementCountryCode;
    private String productCode;
    private SettlementStatus status;

}
