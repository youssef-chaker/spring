package com.ds.repo;

import com.ds.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class TicketRepoImpl implements TicketRepo{

    private final EntityManager em;

    @Autowired
    public TicketRepoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void addTicket(Ticket ticket) {
        em.persist(ticket);
    }

    @Override
    @Transactional
    public int deleteTicket(int id) {
        Query query = em.createQuery("delete from Ticket where num=:id");
        query.setParameter("id",id);
        return query.executeUpdate();
    }

    @Override
    @Transactional
    public Ticket getTicket(int id) {
        return em.find(Ticket.class,id);
    }

    @Override
    @Transactional
    public List<Ticket> getTickets() {
        return em.createQuery("from Ticket").getResultList();
    }

    @Override
    @Transactional
    public Ticket updateTicket(Ticket ticket,int id) {
        ticket.setNum(id);
        return em.merge(ticket);
    }
}
