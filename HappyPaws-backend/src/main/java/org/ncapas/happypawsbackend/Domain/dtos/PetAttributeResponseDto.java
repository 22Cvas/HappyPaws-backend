package org.ncapas.happypawsbackend.Domain.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PetAttributeResponseDto {
    private Integer id;
    private String attributeName;
    private String attributeValue;
    private String petName;
}