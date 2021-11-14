package com.example.facturationtest;

import com.example.facturationtest.entities.Client;
import com.example.facturationtest.entities.Produit;
import com.example.facturationtest.repos.ClientRepo;
import com.example.facturationtest.repos.ProduitRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class FacturationtestApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacturationtestApplication.class, args); }

    @Bean
    CommandLineRunner start(ProduitRepo articleRepo,ClientRepo clientRepo, RepositoryRestConfiguration repositoryRestConfiguration) {

        repositoryRestConfiguration.exposeIdsFor(Produit.class,Client.class);

        return args -> {

            articleRepo.save(new Produit(null,"Beton","BS-XF3",8000,500,"T",null));
            articleRepo.save(new Produit(null,"Beton Armé","BA-XC30",4000,700,"T",null));
            articleRepo.save(new Produit(null,"Acier","AC-XWT1",6000,1500,"T",null));
            articleRepo.save(new Produit(null,"Verre","VS-LVXF5",500,2500,"T",null));
            articleRepo.save(new Produit(null,"Cuivre","C-DWXF8",2000,10500,"T",null));

            clientRepo.save(new Client(null,"OCP","email","adresse",0,0,0,null,null));
            clientRepo.save(new Client(null,"OCP2","email","adresse",0,0,0,null,null));
            clientRepo.save(new Client(null,"OCP3","email","adresse",0,0,0,null,null));

            articleRepo.findAll().forEach(produit -> {

                System.out.println(produit.toString());
            });

            clientRepo.findAll().forEach(client -> {
                System.out.println(client.toString());
            });


        };

    }

}
