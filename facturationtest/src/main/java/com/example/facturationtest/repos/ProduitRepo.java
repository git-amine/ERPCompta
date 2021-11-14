package com.example.facturationtest.repos;

import com.example.facturationtest.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepo extends JpaRepository<Produit,Long> {

    public Produit findProduitByNom(String nom);
    public void deleteById(Long id);

}
