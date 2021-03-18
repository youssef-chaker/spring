package com.ds.entity.met;

import com.ds.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "dessert")
public class Dessert extends Met{
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "ticket_dessert",joinColumns = @JoinColumn(name = "dessert"),inverseJoinColumns = @JoinColumn(name = "ticket"))
    private List<Ticket> tickets;
}
