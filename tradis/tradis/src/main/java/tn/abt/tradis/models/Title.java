package tn.abt.tradis.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TITRE")
public class Title {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String lib;
    private LocalDateTime creationDate;
}
