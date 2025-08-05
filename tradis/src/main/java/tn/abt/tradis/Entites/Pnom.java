package tn.abt.tradis.Entites;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parameters")
public class Pnom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_param")
    private Long idParam;


    @Column(name = "cnom")
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

    // Getters
    public Long getIdParam() {
        return idParam;
    }

    public String getCnom() {
        return cnom;
    }

    public String getCacc() {
        return cacc;
    }

    public String getLabel1() {
        return label1;
    }

    public String getLabel2() {
        return label2;
    }

    public String getLabel3() {
        return label3;
    }

    public String getLabel4() {
        return label4;
    }

    public String getLabel5() {
        return label5;
    }

    public String getLabel6() {
        return label6;
    }

    public String getLabel7() {
        return label7;
    }

    public String getLabel8() {
        return label8;
    }

    // Setters
    public void setIdParam(Long idParam) {
        this.idParam = idParam;
    }

    public void setCnom(String cnom) {
        this.cnom = cnom;
    }

    public void setCacc(String cacc) {
        this.cacc = cacc;
    }

    public void setLabel1(String label1) {
        this.label1 = label1;
    }

    public void setLabel2(String label2) {
        this.label2 = label2;
    }

    public void setLabel3(String label3) {
        this.label3 = label3;
    }

    public void setLabel4(String label4) {
        this.label4 = label4;
    }

    public void setLabel5(String label5) {
        this.label5 = label5;
    }

    public void setLabel6(String label6) {
        this.label6 = label6;
    }

    public void setLabel7(String label7) {
        this.label7 = label7;
    }

    public void setLabel8(String label8) {
        this.label8 = label8;
    }
}