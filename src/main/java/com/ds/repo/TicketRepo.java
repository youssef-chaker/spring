package com.ds.repo;

import com.ds.entity.Ticket;

import java.util.List;

public interface TicketRepo {
    void addTicket(Ticket ticket);
    int deleteTicket(int id );
    Ticket getTicket(int id);
    List<Ticket> getTickets();
    Ticket updateTicket(Ticket ticket,int id);
}
