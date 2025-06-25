package tn.abt.tradis.Entites;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "parameters")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pnom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idParam;

    @Column(name = "cmon", nullable = true, unique = true, length = 50)
    private String cnom;
    @Column(name = "cacc", nullable = true, unique = true, length = 50)
    private String cacc;
    private String label1;
    private String label2;
    private String label3;
    private String label4;
    private String label5;
    private String label6;
    private String label7;
    private String label8;

}