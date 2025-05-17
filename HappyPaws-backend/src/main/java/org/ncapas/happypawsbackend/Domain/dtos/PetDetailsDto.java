package org.ncapas.happypawsbackend.Domain.dtos;

import org.ncapas.happypawsbackend.Domain.Entities.Vaccine;

import java.util.List;

public class PetDetailsDto {
    private String gender;
    private int age;
    private boolean sterilized;
    private String description;
    private String history;
    private String state;
    private String species;
    private String race;
    private String size;
    private String shelterName;
    private String shelterPhone;
    private List<Vaccine> vaccines;
}
