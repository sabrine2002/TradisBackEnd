package tn.abt.tradis.Entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class Produit implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String codeProduit;
    private String libelleProduit;

    @ManyToOne
    private Parametre typeProduit;
}
