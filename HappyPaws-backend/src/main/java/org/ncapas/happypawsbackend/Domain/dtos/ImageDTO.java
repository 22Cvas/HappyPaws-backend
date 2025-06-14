package org.ncapas.happypawsbackend.Domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ImageDTO {

    private String name;
    private String imgURL;
    private String imageId;
}
