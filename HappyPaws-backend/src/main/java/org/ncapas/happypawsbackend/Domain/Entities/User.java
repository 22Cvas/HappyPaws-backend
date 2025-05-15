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
@Table(name = "Users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_User")
    private int Id_User;

    @Column(name = "password")
    private String password;

    @Column(name = "by")
    private int by;

    @Column(name = "state")
    private int state;

    @Column(name = "creation_date")
    private Date creation_date;

    @Column(name = "last_update")
    private Date last_update;

    @ManyToOne
    @JoinColumn(name = "Id_Rol", nullable = false, foreignKey = @ForeignKey(name = "fk_Rol_User"))
    private Rol Rol;

    @OneToOne
    @JoinColumn(name = "Id_Contact", nullable = false, foreignKey = @ForeignKey(name = "fk_Contact_User"))
    private Contact Contact;

}
