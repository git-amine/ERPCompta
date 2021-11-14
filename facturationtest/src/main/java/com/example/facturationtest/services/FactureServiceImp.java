package com.example.facturationtest.services;

import com.example.facturationtest.entities.Facture;
import com.example.facturationtest.entities.LigneFacture;
import org.springframework.stereotype.Service;

@Service
public class FactureServiceImp implements IFactureService{

    @Override
    public double montantHT(LigneFacture ligneFacture) {

        return ligneFacture.getProduit().getPrixUnitaire()*ligneFacture.getQte();
    }

    @Override
    public double montantTVA(LigneFacture ligneFacture) {
        return montantHT(ligneFacture)*ligneFacture.getTva()/100;
    }

    @Override
    public double montantTTC(LigneFacture ligneFacture) {
        return montantHT(ligneFacture)+montantTVA(ligneFacture);
    }

    @Override
    public double totalHT(Facture facture) {
        double totalHT= facture.getLigneFactureList().stream().mapToDouble(LigneFacture::getMontantHT).sum();
        return totalHT;
    }

    @Override
    public double totalTTC(Facture facture) {
        double totalTTC= facture.getLigneFactureList().stream().mapToDouble(LigneFacture::getMontantTTC).sum();
        return totalTTC;
    }

    @Override
    public double totalTVA(Facture facture) {
        double totalTVA= facture.getLigneFactureList().stream().mapToDouble(LigneFacture::getMontantTVA).sum();
        return totalTVA;
    }
}
