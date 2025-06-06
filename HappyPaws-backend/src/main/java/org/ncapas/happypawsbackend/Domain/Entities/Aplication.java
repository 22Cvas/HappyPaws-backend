package org.ncapas.happypawsbackend.Domain.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Aplication")

public class Aplication {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_aplication")
    private UUID id_aplication;

    @Column(name = "aplication_Date")
    private Date aplication_Date;

    @Column(name = "Completion_Date")
    private Date Completion_Date;

    @Column(name = "other_Pets")
    private int other_Pets;

    @Column(name = "reason_adoption")
    private String reason_adoption;

    @Column(name = "enough_space")
    private int enough_space;

    @Column(name = "enought_time")
    private String enought_time;

    @Column(name = "by")
    private int by;

    @Column(name = "state")
    private int state;

    @Column(name = "creation_date")
    private Date creation_date;

    @Column(name = "last_update")
    private Date last_update;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "fk_Users_Aplication"))
    private User Users;

    @OneToOne
    @JoinColumn(name = "id_pet", nullable = false, foreignKey = @ForeignKey(name = "fk_Pet_Aplication"))
    private Pet Pet;



}
