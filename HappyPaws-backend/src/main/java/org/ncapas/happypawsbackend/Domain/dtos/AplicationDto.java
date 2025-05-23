package org.ncapas.happypawsbackend.Domain.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AplicationDto {
    private Long id;
    private Long userId;
    private Long petId;
    private String state;
    private LocalDate applicationDate;
    private LocalDate completionDate;
    private String reasonAdoption;
}
