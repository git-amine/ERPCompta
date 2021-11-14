package com.example.facturationtest.repos;

import com.example.facturationtest.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client,Long> {

    public Client findClientById(Long id);
    public Client findClientByNom(String nom);
    public void deleteById(Long id);
}
