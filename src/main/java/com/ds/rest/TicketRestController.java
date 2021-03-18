package com.ds.rest;

import com.ds.dto.TicketDto;
import com.ds.entity.Ticket;
import com.ds.repo.TicketRepo;
import com.ds.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketRestController {
    private final TicketService ticketService;

    @Autowired
    public TicketRestController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> getTickets() {
        return ticketService.getTickets();
    }

    @PostMapping
    public Ticket addTicket(@RequestBody TicketDto ticketDto) throws Exception {
        return ticketService.addTicket(ticketDto);
    }

}
