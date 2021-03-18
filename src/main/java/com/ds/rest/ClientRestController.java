package com.ds.rest;

import com.ds.entity.Client;
import com.ds.entity.Ticket;
import com.ds.entity.met.Dessert;
import com.ds.entity.met.Entree;
import com.ds.entity.met.Plat;
import com.ds.repo.ClientRepo;
import com.ds.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
public class ClientRestController {

    private final ClientRepo clientService;
    private final TicketService ticketService;

    @Autowired
    public ClientRestController(ClientRepo clientService,TicketService ticketService) {
        this.clientService = clientService;
        this.ticketService = ticketService;
    }

    @PostMapping
    public Client addClient(@RequestBody Client client) {
        clientService.addClient(client);
        return client;
    }

    @DeleteMapping("/{id}")
    public void removeClient(@PathVariable int id) {
        clientService.deleteClient(id);
        //todo exception if returns 0
    }

    @GetMapping
    public List<Client> getClients() {
        return clientService.getClients();
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable int id) {
        //todo exception
        return clientService.getClient(id);
    }

    @PutMapping("/{id}")
    public Client updateClient(@RequestBody Client client,@PathVariable int id) {
        client.setId(id);
        return clientService.updateClient(client);
    }

    @GetMapping("/top")
    public Client topClient() {
        return ticketService.getTickets().stream()
                .map(Ticket::getClient)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))//converts into map with Plat as key and the number of occurences as value
                .entrySet() //entrySet so i can convert it into a stream
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .get();
    }

    @GetMapping("/{id}/topJour")
    public String topJour(@PathVariable int id) {
        return ticketService.getTickets().stream()
                .filter(ticket -> ticket.getClient().getId() == id)
                .map(ticket -> ticket.getDate().getDayOfWeek().toString())
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .get();
    }


    @GetMapping("/revenu")
    public float revenu(LocalDate start , LocalDate end) {
        return ticketService.getTickets().stream()
                .filter(ticket -> ticket.getDate().compareTo(start) > 0 && ticket.getDate().compareTo(end) < 0)
                .map(ticket -> {
                    float prixTable = 0,prixDesserts,prixEntrees,prixPlats;
                    if(ticket.getTable().getType().equals("grande terasse")) prixTable=ticket.getTable().getSupplement()*2;
                    else {
                        prixTable=ticket.getTable().getSupplement();
                    }
                    prixEntrees = ticket.getEntrees().stream().map(Entree::getPrix).reduce(0f, Float::sum);
                    prixPlats = ticket.getPlats().stream().map(Plat::getPrix).reduce(0f, Float::sum);
                    prixDesserts = ticket.getDesserts().stream().map(Dessert::getPrix).reduce(0f, Float::sum);
                    return prixTable+prixDesserts+prixPlats+prixEntrees;
                }).reduce(0f,Float::sum);
    }
}
