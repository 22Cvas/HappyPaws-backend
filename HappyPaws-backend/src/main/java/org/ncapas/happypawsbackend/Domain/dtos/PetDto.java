package org.ncapas.happypawsbackend.Domain.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PetDto {
    private String gender;
    private int age;
    private boolean sterilized;
    private LocalDate entryDate;
    private String description;
    private String photo;
    private String state;
    private String speciesName;
    private String breedName;
    private String sizeName;
}
