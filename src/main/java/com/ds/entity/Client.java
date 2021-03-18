package com.ds.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "nom est required")
    @Size(min = 2,max = 255,message = "nom doit etre entre 2 et 255 characteres")
    private String nom;
    @NotBlank(message = "nom est required")
    @Size(min = 2,max = 255,message = "prenom doit etre entre 2 et 255 characteres")
    private String prenom;
    private Date dob;
    private String address;
    private String telephone;
    @JsonIgnore
    @OneToMany(mappedBy = "client",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Ticket> tickets;

    public void addTicket(Ticket ticket) {
        if(tickets == null) {
            tickets = new ArrayList<>();
        }
        tickets.add(ticket);
        ticket.setClient(this);
    }
}
