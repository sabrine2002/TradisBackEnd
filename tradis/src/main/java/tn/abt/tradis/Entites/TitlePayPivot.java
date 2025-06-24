package tn.abt.tradis.Entites;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JoinColumn(name = "reglement_id", referencedColumnName = "ID_REGLEMENT")
    private Settlement settlement;

}
