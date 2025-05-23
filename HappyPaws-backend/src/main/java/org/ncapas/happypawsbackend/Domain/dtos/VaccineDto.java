package org.ncapas.happypawsbackend.Domain.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VaccineDto {
    private String petName;
    private String vaccineName;
    private LocalDate date;
    private String detail;
    private String effectTime;
}
