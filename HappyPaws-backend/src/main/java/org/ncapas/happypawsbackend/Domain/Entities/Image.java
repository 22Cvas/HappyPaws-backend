package org.ncapas.happypawsbackend.Domain.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;
import org.ncapas.happypawsbackend.Domain.Audit.Auditable;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Image")
public class Image extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String imgURL;

    @NotBlank
    private String imageId;

    public Image(String name, String imgURL, String imageId) {
        this.name = name;
        this.imgURL = imgURL;
        this.imageId = imageId;
    }

}
