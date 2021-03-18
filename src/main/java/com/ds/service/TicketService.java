package com.ds.service;

import com.ds.dto.TicketDto;
import com.ds.entity.Ticket;

import java.util.List;

public interface TicketService {
    Ticket addTicket(TicketDto ticketDto) throws Exception;
    List<Ticket> getTickets();
    int deleteTicket(int id);
    Ticket updateTicket(Ticket ticket,int id);
    Ticket getTicket(int id);
}
