package com.ds.rest;

import com.ds.entity.Client;
import com.ds.entity.Table;
import com.ds.entity.Ticket;
import com.ds.repo.TableRepo;
import com.ds.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tables")
public class TableRestController {
    private final TableRepo tableService;
    private final TicketService ticketService;

    @Autowired
    public TableRestController(TableRepo tableService,TicketService ticketService) {
        this.tableService = tableService;
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Table> getTables() {
        return tableService.getTables();
    }

    @PostMapping
    public Table addTable(@RequestBody Table table ) {
        tableService.addTable(table);
        return table;
    }

    @GetMapping("/{id}")
    public Table getTable(@PathVariable int id) {
        return tableService.getTable(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTable(@PathVariable int id) {
        tableService.deleteTable(id);
    }

    @PutMapping("/{id}")
    public Table updateTable(@RequestBody Table table ,@PathVariable int id) {
        return tableService.updateTable(table,id);
    }

    @GetMapping("/top")
    public Table topTable() {
        return ticketService.getTickets().stream()
                .map(Ticket::getTable)
                .filter(Objects::nonNull)
                //todo group by table by something
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))//converts into map with Plat as key and the number of occurences as value
                .entrySet() //entrySet so i can convert it into a stream
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .get();
    }
}
