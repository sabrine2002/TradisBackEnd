package tn.abt.tradis.Entites;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "productCode", referencedColumnName = "code_param")
    private Pnom productCode;
    private String productLabel;

    @ManyToOne
    @JoinColumn(name = "productType", referencedColumnName = "code_param")
    private Pnom productType;
}
