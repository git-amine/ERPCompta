package com.example.facturationtest.repos;

import com.example.facturationtest.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepo extends JpaRepository<Facture,Long> {

    public Facture findFactureById(Long id);
    public void deleteById(Long id);
}
