package com.example.facturationtest.controllers;

import com.example.facturationtest.entities.Client;
import com.example.facturationtest.entities.Facture;
import com.example.facturationtest.entities.LigneFacture;
import com.example.facturationtest.entities.Produit;
import com.example.facturationtest.repos.ClientRepo;
import com.example.facturationtest.repos.FactureRepo;
import com.example.facturationtest.repos.LigneFactureRepo;
import com.example.facturationtest.repos.ProduitRepo;
import com.example.facturationtest.services.IFactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class FactureController {

    @Autowired
    ProduitRepo produitRepo;
    @Autowired
    ClientRepo clientRepo;
    @Autowired
    FactureRepo factureRepo;
    @Autowired
    LigneFactureRepo ligneFactureRepo;

    @Autowired
    IFactureService factureService;

    @GetMapping("/formFacture")
    public String FactureForm() {
        return "factureForm";
    }

    @GetMapping("/produit")
    public String PrduitForm() {
        return "produitForm";
    }


    @PostMapping("/formFacture")

    public String creeFacture(Facture facture, Model model) {


        Client client=new Client();
        client= clientRepo.findClientByNom(facture.getNomClient());
        //System.out.println(client.toString());
        facture.setClient(client);
        facture.setEtat("Non-payé");
        Date date=new Date();
        SimpleDateFormat dtf=new SimpleDateFormat("dd/MM/yyyy");
        facture.setDateCreation(dtf.format(date));
        List<LigneFacture> ligneFactureList=new ArrayList<LigneFacture>();
        facture.setLigneFactureList(ligneFactureList);
        factureRepo.save(facture);

        model.addAttribute("factureid", facture.getId());
        return "produitForm";
    }

    @PostMapping("/formProduit")

    public String creeLigneFacture(LigneFacture lignefacture, Model model) {

        Produit produit=new Produit();
        produit=produitRepo.findProduitByNom(lignefacture.getNomProduit());

        lignefacture.setProduit(produit);

        //Facture facture=new Facture();
        //facture=factureRepo.findFactureById(lignefacture.getFactureID());
        //System.out.println(facture.getDateCreation());
        //factureRepo.save(facture);
        //lignefacture.setFacture(facture);
        ligneFactureRepo.save(lignefacture);
        model.addAttribute("factureid", lignefacture.getFactureID());
        System.out.println(lignefacture.toString());

        return "produitForm";
    }

    @GetMapping("/facture")

    public String facture(@RequestParam("id")Long id,Model model) {

        String ref ="FA/00000"+id;
        List<LigneFacture> ligneFactureList=new ArrayList<LigneFacture>();
        ligneFactureList=ligneFactureRepo.findLigneFactureByFactureID(id);

        ligneFactureList.forEach(ligneFacture -> {
            //double montantHT=factureService.montantHT(ligneFacture.getProduit().getPrixUnitaire(),ligneFacture.getQte());
            //double montantTVA=factureService.montantTVA(montantHT,ligneFacture.getTva());
            //double montantTTC=factureService.montantTTC(montantHT,montantTVA);

            double montantHT=factureService.montantHT(ligneFacture);
            double montantTVA=factureService.montantTVA(ligneFacture);
            double montantTTC=factureService.montantTTC(ligneFacture);

            ligneFacture.setMontantHT(montantHT);
            ligneFacture.setMontantTVA(montantTVA);
            ligneFacture.setMontantTTC(montantTTC);
        });


        Facture facture=new Facture();
        facture=factureRepo.findFactureById(id);
        facture.setRef(ref);
        facture.setLigneFactureList(ligneFactureList);
        facture.setTotalHT(factureService.totalHT(facture));
        facture.setTotalTVA(factureService.totalTVA(facture));
        facture.setTotalTTC(factureService.totalTTC(facture));
        factureRepo.save(facture);
        System.out.println("************************* MERSERN MAROC ************************");
        System.out.println("*************************   FACTURE N°: FA/00000"+facture.getId()+"   ************************");
        System.out.println("Client:"+facture.getClient().getNom());
        System.out.println("Date de création:"+facture.getDateCreation());
        System.out.println("************************* Lignes facture ************************");
        System.out.println(facture.getLigneFactureList());
        System.out.println(facture.getCommentaire());
        System.out.println("***********************************************");

        model.addAttribute("factureid",facture.getId());
        model.addAttribute("ref",facture.getRef());
        model.addAttribute("client",facture.getClient().getNom());
        model.addAttribute("dateCreation",facture.getDateCreation());
        model.addAttribute("echeance",facture.getEcheance());
        model.addAttribute("lignes",ligneFactureList);
        model.addAttribute("commentaire",facture.getCommentaire());

        model.addAttribute("totalHT",facture.getTotalHT());
        model.addAttribute("totalTVA",facture.getTotalTVA());
        model.addAttribute("totalTTC",facture.getTotalTTC());
        return "facture";
    }


    @PostMapping("/confirm")
    public String confirmLigne(LigneFacture ligneFacture,Model model) {

        Produit produit=new Produit();
        //double prixUnitaire,qte,tva,montantHT,montantTVA,montantTTC;
        produit=produitRepo.findProduitByNom(ligneFacture.getNomProduit());

        ligneFacture.setProduit(produit);

        //prixUnitaire=produit.getPrixUnitaire();
        //qte=ligneFacture.getQte();
        //tva=ligneFacture.getTva();
        //String unite=produit.getUnite();
        //montantHT=factureService.montantHT(prixUnitaire,qte);
        //montantTVA=factureService.montantTVA(montantHT,tva);
        //montantTTC=factureService.montantTTC(montantHT,montantTVA);
        //montantHT=factureService.montantHT(ligneFacture);
        //montantTVA=factureService.montantTVA(ligneFacture);
        //montantTTC=factureService.montantTTC(ligneFacture);

        model.addAttribute("factureid",ligneFacture.getFactureID());
        model.addAttribute("nomProduit",ligneFacture.getNomProduit());
        model.addAttribute("prixUnitaire",ligneFacture.getProduit().getPrixUnitaire());
        model.addAttribute("qte",ligneFacture.getQte());
        model.addAttribute("tva",ligneFacture.getTva());
        model.addAttribute("unite",ligneFacture.getProduit().getUnite());


        model.addAttribute("montantHT",factureService.montantHT(ligneFacture));
        model.addAttribute("montantTVA",factureService.montantTVA(ligneFacture));
        model.addAttribute("montantTTC",factureService.montantTTC(ligneFacture));

        return "confirmLigne";
    }

    @GetMapping("/factureView")
    public String factureView(){
        return "facture";

    }

    @PostMapping("/enregistrer")
    public String enregistrer(@RequestParam("id") Long id){

        Facture facture=factureRepo.findFactureById(id);
        Client client=clientRepo.findClientByNom(facture.getNomClient());
        System.out.println("Ventes TTC avant:    "+client.getVentesTTC());

        client.getFactureList().add(facture);
        client.setVentesHT(client.getVentesHT()+factureService.totalHT(facture));
        client.setTotalTVA(client.getTotalTVA()+factureService.totalTVA(facture));
        client.setVentesTTC(client.getVentesTTC()+factureService.totalTTC(facture));
        clientRepo.save(client);

        System.out.println("Ventes TTC aprés:    "+client.getVentesTTC());
        /*
        List<Facture> list=factureRepo.findAll();
        list.forEach(fact -> {
            System.out.println(fact.getId());
            System.out.println(fact.getNomClient());

        });
        System.out.println(factureRepo.findAll().size());*/

        return "redirect:/ventes/factures";
    }

    @GetMapping("/ventes/factures")
    public String afficherFactures(Model model){

        List<Facture> factures=factureRepo.findAll();

        model.addAttribute("factures",factures);
        return "FacturesClient";
    }

    @GetMapping("/delete")
    @Transactional
    public String supprimerFacture(@RequestParam("id") Long id){
        factureRepo.deleteById(id);
        return "redirect:/operations";
    }
}