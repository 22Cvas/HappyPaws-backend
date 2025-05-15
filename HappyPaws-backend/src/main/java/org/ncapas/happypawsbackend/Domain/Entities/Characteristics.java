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
@Table(name = "Characteristics")

public class Characteristics {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id_Characteristics")
    private UUID Id_Characteristics;

    @Column(name = "by")
    private int by;

    @Column(name = "state")
    private int state;

    @Column(name = "creation_date")
    private Date creation_date;

    @Column(name = "last_update")
    private Date last_update;

    @ManyToOne
    @JoinColumn(name = "Id_Pet", nullable = false, foreignKey = @ForeignKey(name = "fk_Pet_Characteristics"))
    private Pet Pet;

    @ManyToOne
    @JoinColumn(name = "Id_Species", nullable = false, foreignKey = @ForeignKey(name = "fk_Species_Characteristics"))
    private Species Species;

    @ManyToOne
    @JoinColumn(name = "Id_Race", nullable = false, foreignKey = @ForeignKey(name = "fk_Race_Characteristics"))
    private Race Race;

    @OneToOne
    @JoinColumn(name = "Id_Size", nullable = false, foreignKey = @ForeignKey(name = "fk_Size_Characteristics"))
    private Size Size;
}
