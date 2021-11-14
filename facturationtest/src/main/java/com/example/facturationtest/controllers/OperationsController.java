package com.example.facturationtest.controllers;


import com.example.facturationtest.entities.Facture;
import com.example.facturationtest.repos.FactureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OperationsController {

    @Autowired
    FactureRepo factureRepo;

    @GetMapping("/operations")
    public String afficherFactures(Model model){

        List<Facture> factures=factureRepo.findAll();

        model.addAttribute("factures",factures);
        return "Operations";
    }
}
