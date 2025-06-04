package org.ncapas.happypawsbackend.Domain.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {

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