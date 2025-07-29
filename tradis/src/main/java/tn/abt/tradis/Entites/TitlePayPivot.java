package tn.abt.tradis.Entites;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "title_pay_pivot")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TitlePayPivot implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idP;

    @ManyToOne
    @JoinColumn(name = "title_num_dom", referencedColumnName = "numDom")
    private Title title;

    @ManyToOne
    @JoinColumn(name = "settlement_id", referencedColumnName = "idSettlement")
    private Settlement settlement;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id_client")
    private Client client;


}