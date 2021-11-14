package com.example.facturationtest.controllers;


import com.example.facturationtest.entities.Client;
import com.example.facturationtest.repos.ClientRepo;
import com.example.facturationtest.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ClientController {

    @Autowired
    ClientRepo clientRepo;

    @Autowired
    ClientService clientService;

    @GetMapping("/formClient")
    public String formClient(){
        return "ClientForm";
    }

    @PostMapping("/formClient")
    public String creerClient(Client client){
        client.setVentesHT(0);
        client.setVentesTTC(0);
        client.setTotalTVA(0);
        clientRepo.save(client);
        System.out.println("*******************************");
        System.out.println(client.toString());
        return "ClientForm";
    }
    @GetMapping("/ventes/clients")
    public String afficherClients(Model model){

        List<Client> clients=clientRepo.findAll();

        model.addAttribute("clients",clients);
        return "Clients";
    }

    @GetMapping("/deleteClient")
    @Transactional
    public String supprimerClient(@RequestParam("id") Long id){

        System.out.println("************ Suppression client  "+id);
        Client client=clientRepo.findClientById(id);
        client.getFactureList().forEach(facture -> {
            facture.setClient(null);
        });
        clientRepo.deleteById(id);
        return "redirect:/ventes/clients";
    }
}
