package tn.abt.tradis.Entites;

import jakarta.persistence.*;
import lombok.*;


import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TitlePayPivot implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idP;

    @ManyToOne
    @JoinColumn(name = "title_id", referencedColumnName = "NumDom")
    private Title title;

    @ManyToOne
    @JoinColumn(name = "reglement_id", referencedColumnName = "IdSettlement")
    private Settlement settlement;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id_client")
    private Client client;

}
