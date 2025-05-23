package org.ncapas.happypawsbackend.Domain.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Users")

public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_Users")
    private int Id_Users;

    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

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
