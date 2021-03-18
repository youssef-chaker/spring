package com.ds.service;

import com.ds.dto.ErrorDto;
import com.ds.dto.TicketDto;
import com.ds.entity.Client;
import com.ds.entity.Table;
import com.ds.entity.Ticket;
import com.ds.repo.ClientRepo;
import com.ds.repo.TableRepo;
import com.ds.repo.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService{

    private final TicketRepo ticketRepo;
    private final ClientRepo clientRepo;
    private final TableRepo tableRepo;

    @Autowired
    public TicketServiceImpl(TicketRepo ticketRepo, ClientRepo clientRepo, TableRepo tableRepo) {
        this.ticketRepo = ticketRepo;
        this.clientRepo = clientRepo;
        this.tableRepo = tableRepo;
    }

    @Override
    public Ticket addTicket(TicketDto ticketDto) throws Exception {
//        throw new NullPointerException("dsfmlksdf"); test
        Client client = clientRepo.getClient(ticketDto.getUserId());
        Table table = tableRepo.getTable(ticketDto.getTableNum());
        Ticket ticket = new Ticket();
        ticket.setDate(ticketDto.getDate());
        ticket.setAddition(ticketDto.getAddition());
        ticket.setNbCouvert(ticketDto.getNbCouvert());
        client.addTicket(ticket);
        table.addTicket(ticket);
        ticketRepo.addTicket(ticket);
        return ticket;
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> exception(Exception e) {
        var error = new ErrorDto();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage("error");
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @Override
    public List<Ticket> getTickets() {
        return ticketRepo.getTickets();
    }

    @Override
    public int deleteTicket(int id) {
        return ticketRepo.deleteTicket(id);
    }

    @Override
    public Ticket updateTicket(Ticket ticket , int id) {
        return ticketRepo.updateTicket(ticket,id);
    }

    @Override
    public Ticket getTicket(int id) {
        return ticketRepo.getTicket(id);
    }
}
