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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pet_attribute")
    private Integer id_pet_attribute;

    @Column(name = "attribute")
    private Integer attribute;

    @Column(name = "attribute_Value")
    private Integer attribute_Value;

    @ManyToOne
    @JoinColumn(name = "id_pet", nullable = false, foreignKey = @ForeignKey(name = "fk_Pet_Characteristics"))
    private Pet Pet;
}
