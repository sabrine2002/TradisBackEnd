package tn.abt.tradis.Entites;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Entity
@Table(name = "params")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pnom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idParam;

    @Column(name = "cmon")
    private String cnom;

    @Column(name = "cacc")
    private String cacc;

// les codes parametrables et leurs libelles
    private String label1;
    private String label2;
    private String label3;
    private String label4;
    private String label5;
    private String label6;
    private String label7;
    private String label8;

}