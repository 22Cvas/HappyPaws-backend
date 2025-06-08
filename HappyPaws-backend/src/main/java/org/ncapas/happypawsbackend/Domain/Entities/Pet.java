package org.ncapas.happypawsbackend.Domain.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ncapas.happypawsbackend.Domain.Audit.Auditable;
import org.ncapas.happypawsbackend.Domain.Enums.Gender;
import org.ncapas.happypawsbackend.Domain.Enums.PetStatus;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Pet")

public class Pet  extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pet")
    private Integer id_pet;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "weight")
    private int weight;

    @Column(name = "sterilized")
    private boolean sterilized;

    @Column(name = "dewormed")
    private boolean parasiteFree;

    @Column(name = "fully_vaccinated")
    private boolean fullyVaccinated;

    @Column(name = "entry_Date")
    private Date entry_Date;

    @Column(name = "review_Date")
    private Date review_Date;

    @Column(name = "description")
    private String description;

    @Column(name = "history")
    private String history;

    @Column(name = "photoURL")
    private String photoURL;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PetStatus status;

    @ManyToOne
    @JoinColumn(name = "Id_Shelter", nullable = false, foreignKey = @ForeignKey(name = "fk_Shelter_Pet"))
    private Shelter Shelter;

    @ManyToOne
    @JoinColumn(name = "id_species", nullable = false, foreignKey = @ForeignKey(name = "fk_Species_Characteristics"))
    private Species Species;

    @ManyToOne
    @JoinColumn(name = "id_breed", nullable = false, foreignKey = @ForeignKey(name = "fk_Race_Characteristics"))
    private Breed Breed;

    @ManyToOne
    @JoinColumn(name = "id_size", nullable = false, foreignKey = @ForeignKey(name = "fk_Size_Characteristics"))
    private Size Size;

}
