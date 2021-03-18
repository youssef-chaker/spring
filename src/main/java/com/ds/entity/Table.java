package com.ds.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@javax.persistence.Table(name = "ttable")
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;
    //todo validation validate that type is one of given choices
    private String type;
    @Column(name = "nb_couvert")
    private int nbCouvert;
    private float supplement;
    @JsonIgnore
    @OneToMany(mappedBy = "table",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Ticket> tickets;
    public void addTicket(Ticket ticket) {
        if(tickets == null) {
            tickets = new ArrayList<>();
        }
        tickets.add(ticket);
        ticket.setTable(this);
    }
}
