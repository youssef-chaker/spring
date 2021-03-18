package com.ds.repo;

import com.ds.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ClientRepoImpl implements ClientRepo{

    private final EntityManager em;

    @Autowired
    public ClientRepoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void addClient(Client client) {
        em.persist(client);
    }

    @Override
    public List<Client> getClients() {
        Query query = em.createQuery("from Client");
        return query.getResultList();
    }

    @Override
    @Transactional
    public int deleteClient(int id) {
        Query query = em.createQuery("delete from Client where id=:id");
        query.setParameter("id",id);
        return query.executeUpdate();
    }

    @Override
    @Transactional
    public Client getClient(int id) {
        return em.find(Client.class,id);
    }

    @Override
    @Transactional
    public Client updateClient(Client client) {
        return em.merge(client);
    }
}
