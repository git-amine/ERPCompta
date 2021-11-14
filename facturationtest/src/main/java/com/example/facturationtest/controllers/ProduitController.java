package com.example.facturationtest.controllers;

import com.example.facturationtest.entities.Client;
import com.example.facturationtest.entities.Produit;
import com.example.facturationtest.repos.ProduitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProduitController {

    @Autowired
    ProduitRepo produitRepo;

    @GetMapping("/formProduits")
    public String formProduit(){
        return "ProduitsForm";
    }

    @PostMapping("/formProduits")
    public String creerProduit(Produit produit){

        produitRepo.save(produit);
        System.out.println("*******************************");
        System.out.println(produit.toString());
        return "ProduitsForm";
    }

    @GetMapping("/ventes/produits")
    public String afficherProduits(Model model){

        List<Produit> produits=produitRepo.findAll();

        model.addAttribute("produits",produits);
        return "ProduitsVente";
    }

    @GetMapping("/deleteProduit")
    @Transactional
    public String supprimerClient(@RequestParam("id") Long id){

        produitRepo.deleteById(id);
        return "redirect:/ventes/produits";
    }



}
