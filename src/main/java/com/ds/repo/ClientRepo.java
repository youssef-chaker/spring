package com.ds.repo;

import com.ds.entity.Client;

import java.util.List;

public interface ClientRepo {
    void addClient(Client client);
    int deleteClient(int id);
    Client getClient(int id);
    List<Client> getClients();
    Client updateClient(Client client);
}
