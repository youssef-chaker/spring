package com.ds.entity.met;

import com.ds.entity.Ticket;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "entree")
public class Entree extends Met{
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "ticket_entree",joinColumns = @JoinColumn(name = "entree"),inverseJoinColumns = @JoinColumn(name = "ticket"))
    private List<Ticket> tickets;
}
