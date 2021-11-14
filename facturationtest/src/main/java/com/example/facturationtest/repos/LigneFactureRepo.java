package com.example.facturationtest.repos;

import com.example.facturationtest.entities.LigneFacture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneFactureRepo extends JpaRepository<LigneFacture,Long> {

    public List<LigneFacture> findLigneFactureByFactureID(Long id);
}
