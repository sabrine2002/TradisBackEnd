package tn.abt.tradis.Entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class Pays implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String codePays;

    private String codeDevise;
    private String libelle;

}
