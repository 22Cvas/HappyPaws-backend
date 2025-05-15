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
@Table(name = "Ubication")

public class Ubication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_Ubication")
    private int Id_Ubication;

    @Column (name = "entry_Date")
    private Date entry_Date;

    @Column (name = "exit_Date")
    private Date exit_Date;

    @Column (name = "by")
    private int by;

    @Column (name = "state")
    private int state;

    @Column(name = "creation_date")
    private Date creation_date;

    @Column(name = "last_update")
    private Date last_update;

    @ManyToOne
    @JoinColumn(name = "Id_Shelter", nullable = false, foreignKey = @ForeignKey(name = "fk_Shelter_Ubication"))
    private Shelter Shelter;

    @ManyToOne
    @JoinColumn(name = "Id_Users", nullable = false, foreignKey = @ForeignKey(name = "fk_Users_Ubication"))
    private Users users;

}
