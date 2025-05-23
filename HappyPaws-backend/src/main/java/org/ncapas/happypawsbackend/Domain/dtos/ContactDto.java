package org.ncapas.happypawsbackend.Domain.dtos;

import lombok.Data;

@Data
public class ContactDto {
    private String name;
    private String dui;
    private String phone;
    private String email;
}
