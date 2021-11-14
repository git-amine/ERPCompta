package com.example.facturationtest.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String email;
    private String adresse;
    private double ventesHT=0;
    private double ventesTTC=0;
    private double totalTVA=0;

    @OneToMany(mappedBy = "client",fetch = FetchType.EAGER)
    List<Facture> factureList=new ArrayList<Facture>();
    @OneToOne
    Compte compte;

}
