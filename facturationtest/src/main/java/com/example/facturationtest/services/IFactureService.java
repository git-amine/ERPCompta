package com.example.facturationtest.services;

import com.example.facturationtest.entities.Facture;
import com.example.facturationtest.entities.LigneFacture;
import org.springframework.stereotype.Service;

public interface IFactureService {

    public double montantHT(LigneFacture ligneFacture);
    public double montantTVA(LigneFacture ligneFacture);
    public double montantTTC(LigneFacture ligneFacture);

    public double totalHT(Facture facture);
    public double totalTTC(Facture facture);
    public double totalTVA(Facture facture);


}
