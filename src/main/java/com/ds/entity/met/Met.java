package com.ds.entity.met;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
public class Met {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "met_generator")
//    @SequenceGenerator(name = "met_generator",sequenceName = "met_sequence")
//    private int id ;
    @Id
    @NotBlank(message = "nom est required")
    @Size(min = 2,max = 255,message = "Met doit avoir un nom")
    private String nom;
    @Min(value = 0,message = "prix doit etre superieur a 0")
    @NotBlank(message = "prix est required")
    private float prix;
}
