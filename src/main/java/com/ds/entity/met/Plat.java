package com.ds.entity.met;

import com.ds.entity.Ticket;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "plat")
public class Plat extends Met{
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "ticket_plat",joinColumns = @JoinColumn(name = "plat"),inverseJoinColumns = @JoinColumn(name = "ticket"))
    private List<Ticket> tickets;
}
