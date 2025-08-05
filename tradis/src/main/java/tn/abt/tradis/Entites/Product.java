package tn.abt.tradis.Entites;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    @ManyToOne
    @JoinColumn(name = "productCode", referencedColumnName = "id_param")
    private Pnom productCode;

    @ManyToOne
    @JoinColumn(name = "productType", referencedColumnName = "id_param")
    private Pnom productType;

    private String productLabel;
}
