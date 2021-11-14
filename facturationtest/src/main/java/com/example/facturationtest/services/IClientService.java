package com.example.facturationtest.services;

import com.example.facturationtest.entities.Client;

public interface IClientService {

    public double ventesHT(Client client);
    public double ventesTTC(Client client);
    public double totalTVA(Client client);
}
