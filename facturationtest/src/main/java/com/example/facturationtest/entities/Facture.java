package com.example.facturationtest.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Facture implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ref;
    private String dateCreation;
    private String echeance;
    private String commentaire;
    private String etat;


    @OneToMany
    List<LigneFacture> ligneFactureList;
    @ManyToOne
    Client client;
    private String nomClient;

    private double totalHT;
    private double totalTVA;
    private double totalTTC;


}
