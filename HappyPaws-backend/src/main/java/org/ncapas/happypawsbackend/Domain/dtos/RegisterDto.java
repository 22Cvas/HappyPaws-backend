package org.ncapas.happypawsbackend.Domain.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.ncapas.happypawsbackend.Domain.Entities.Rol;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Data
public class RegisterDto {


    @NotBlank
    private String name;

    @NotBlank(message = "El DUI no puede estar vacío")
    @Pattern(regexp = "^\\d{8}-\\d{1}$", message = "El DUI debe tener el formato 12345678-9")
    private String dui;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(regexp = "^[0-9]{8}$", message = "El teléfono debe tener 8 dígitos numéricos")
    private String phone;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;



}
