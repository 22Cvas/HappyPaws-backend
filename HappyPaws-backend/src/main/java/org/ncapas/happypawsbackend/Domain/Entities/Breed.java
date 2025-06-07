package org.ncapas.happypawsbackend.Domain.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Breed")

public class Breed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_breed")
    private Integer id_breed;

    @Column(name = "name")
    private String name;

    @Column(name = "by")
    private Integer by;

    @Column(name = "state")
    private Integer state;

    @Column(name = "creation_date")
    private Date creation_date;

    @Column(name = "last_update")
    private Date last_update;

}
