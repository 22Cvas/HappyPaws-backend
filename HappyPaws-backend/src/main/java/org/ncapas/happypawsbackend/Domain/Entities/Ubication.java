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
@Table(name = "Ubication")

public class Ubication extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Ubication")
    private Integer id_Ubication;

    @Column (name = "entry_Date")
    private Date entry_Date;

    @Column (name = "exit_Date")
    private Date exit_Date;

    @ManyToOne
    @JoinColumn(name = "id_shelter", nullable = false, foreignKey = @ForeignKey(name = "fk_Shelter_Ubication"))
    private Shelter Shelter;

    @ManyToOne
    @JoinColumn(name = "id_users", nullable = false, foreignKey = @ForeignKey(name = "fk_Users_Ubication"))
    private User users;

}
