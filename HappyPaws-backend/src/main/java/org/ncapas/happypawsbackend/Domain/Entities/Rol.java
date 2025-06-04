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
@Table(name = "Rol")

public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_Rol")
    private int Id_Rol;

    @Column(name = "name")
    private String name;

    @Column(name = "by")
    private int by;

    @Column(name = "state")
    private int state;

    @Column(name = "creation_date")
    private Date creation_date;

    @Column(name = "last_update")
    private Date last_update;

    @Enumerated(EnumType.STRING)
    private RoleEnum nameRole;

    public enum RoleEnum {
        VISITANTE,
        ADOPTANTE,
        COLABORADOR,
        ADMIN
    }

}
