package com.ds.rest;

import com.ds.entity.met.Met;
import com.ds.entity.met.Plat;
import com.ds.repo.MetRepo;
import com.ds.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/mets")
public class MetRestController {
    private final MetRepo metService;
    private final TicketService ticketService;

    @Autowired
    public MetRestController(MetRepo metService,TicketService ticketService) {
        this.metService = metService;
        this.ticketService = ticketService;
    }

    @PostMapping("/{type}")
    public Met addMet(@PathVariable String type,@RequestBody Met met) {
        metService.addMet(type,met);
        return met;
    }

    @DeleteMapping("/{type}/{nom}")
    public void deleteMet(@PathVariable String type , @PathVariable String nom) {
        metService.deleteMet(type,nom);
    }

    @GetMapping("/{type}/{nom}")
    public Met getMet(@PathVariable String type,@PathVariable String nom) {
        return metService.getMet(type,nom);
    }
    @GetMapping
    public List<Met> getMets() {
        return metService.getMets();
    }

    @GetMapping("/plat/top")
    public Plat topPlat() {
        return ticketService.getTickets().stream()
                .filter(ticket -> Objects.nonNull(ticket.getPlats()))
                .flatMap(ticket -> ticket.getPlats().stream())
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))//converts into map with Plat as key and the number of occurences as value
                .entrySet() //entrySet so i can convert it into a stream
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .get();
    }
}
