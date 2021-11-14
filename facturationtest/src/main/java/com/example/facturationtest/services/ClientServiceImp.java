package com.example.facturationtest.services;

import com.example.facturationtest.entities.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImp implements IClientService{

    @Override
    public double ventesHT(Client client) {
        return 0;
    }

    @Override
    public double ventesTTC(Client client) {
        return 0;
    }

    @Override
    public double totalTVA(Client client) {
        return 0;
    }
}
