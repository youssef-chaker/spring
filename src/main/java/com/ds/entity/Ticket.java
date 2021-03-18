package com.ds.entity;

import com.ds.entity.met.Dessert;
import com.ds.entity.met.Entree;
import com.ds.entity.met.Plat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
@javax.persistence.Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;
    private LocalDate date;
    @Column(name = "nb_couvert")
    private int nbCouvert;
    private int addition;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "ttable")
    private Table table;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "client")
    private Client client;
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "ticket_plat",joinColumns = @JoinColumn(name = "ticket"),inverseJoinColumns = @JoinColumn(name = "plat"))
    private List<Plat> plats;
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "ticket_entree",joinColumns = @JoinColumn(name = "ticket"),inverseJoinColumns = @JoinColumn(name = "entree"))
    private List<Entree> entrees;
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "ticket_dessert",joinColumns = @JoinColumn(name = "ticket"),inverseJoinColumns = @JoinColumn(name = "dessert"))
    private List<Dessert> desserts;

}
