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

    public Long getIdP() {
        return idP;
    }

    public void setIdP(Long idP) {
        this.idP = idP;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Settlement getSettlement() {
        return settlement;
    }

    public void setSettlement(Settlement settlement) {
        this.settlement = settlement;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
