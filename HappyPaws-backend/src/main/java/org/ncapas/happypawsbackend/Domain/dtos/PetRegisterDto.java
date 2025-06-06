package org.ncapas.happypawsbackend.Domain.dtos;

import lombok.Data;
import org.ncapas.happypawsbackend.Domain.Entities.Breed;

import java.time.LocalDate;

@Data
public class PetRegisterDto {
    private String name;
    private String gender;
    private Integer age;
    private boolean sterilized;
    private LocalDate entryDate;
    private String description;
    private String photo;
    private String state;
    private String speciesName;
    private Breed breedName;
    private String sizeName;
}
