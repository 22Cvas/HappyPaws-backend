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
@Table(name = "Pet")



public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id_Pet;
    /*
    @Column(name = "name")
    private String name;
    */
    @Column(name = "age")
    private int age;

    @Column(name = "weight")
    private int weight;

    @Column (name = "Sterilized")
    private int Sterilized;

    @Column (name = "entry_Date")
    private Date entry_Date;

    @Column (name = "review_Date")
    private Date review_Date;

    @Column (name = "description")
    private String description;

    @Column (name = "history")
    private String history;

    @Column (name = "photoURL")
    private String photoURL;

    @Column (name = "by")
    private int by;

    @Column (name = "state")
    private int state;

    @Column(name = "creation_date")
    private Date creation_date;

    @Column(name = "last_update")
    private Date last_update;

    @ManyToOne
    @JoinColumn(name = "Id_Shelter", nullable = false, foreignKey = @ForeignKey(name = "fk_Shelter_Pet"))
    private Shelter Shelter;


}
