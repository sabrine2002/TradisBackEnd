package tn.abt.tradis.Entites;

import jakarta.persistence.*;
import lombok.*;
import tn.abt.tradis.Enum.SettlementStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "settlement")
public class Settlement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSettlement;

    private LocalDate SettlementDate;

    @Column(precision = 19, scale = 4)
    private BigDecimal SettlementAmountLC;

    @Column(precision = 19, scale = 4)
    private BigDecimal SettlementAmountFC;

    @ManyToOne
    @JoinColumn(name = "settlement_country_id_param", referencedColumnName = "id_param")
    private Pnom SettlementCountry;

    @ManyToOne
    @JoinColumn(name = "code_devise", referencedColumnName = "id_param")
    private Pnom CurrencySettlement;

    @ManyToOne
    @JoinColumn(name = "settlement_product_id_param", referencedColumnName = "id_param")
    private Pnom SettlementProduct;

    @ManyToOne
    @JoinColumn(name = "title_num_dom", referencedColumnName = "numDom")
    private Title title;

    @Enumerated(EnumType.STRING)
    @Column(name = "settlement_status")
    private SettlementStatus settlementStatus = SettlementStatus.VALIDATED;

    @Column(name = "last_updated_date", nullable = true)
    private LocalDateTime lastUpdatedDate;
}
