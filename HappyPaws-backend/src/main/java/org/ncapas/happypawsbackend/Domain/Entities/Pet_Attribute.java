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
@Table(name = "Pet_Attribute")

public class Pet_Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id_Pet_Attribute")
    private UUID Id_Pet_Attribute;

    @Column(name = "attribute")
    private int attribute;

    @Column(name = "attribute_Value")
    private int attribute_Value;

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
}
