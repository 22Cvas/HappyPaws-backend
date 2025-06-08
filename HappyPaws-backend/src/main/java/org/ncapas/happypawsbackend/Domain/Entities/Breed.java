package org.ncapas.happypawsbackend.Domain.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ncapas.happypawsbackend.Domain.Audit.Auditable;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Breed")

public class Breed  extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_breed")
    private Integer id_breed;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_species", nullable = false, foreignKey = @ForeignKey(name = "fk_species_breed"))
    private Species species;

}
