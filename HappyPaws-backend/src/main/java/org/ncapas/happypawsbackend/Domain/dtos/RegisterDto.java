package org.ncapas.happypawsbackend.Domain.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class RegisterDto {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @NotBlank(message = "El DUI no puede estar vacío")
    @Pattern(regexp = "^\\d{8}-\\d{1}$", message = "El DUI debe tener el formato 12345678-9")
    private String dui;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(regexp = "^[0-9]{8}$", message = "El teléfono debe tener 8 dígitos numéricos")
    private String phone;

    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "El correo debe tener un formato válido")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&\\-_])[A-Za-z\\d@$!%*?&\\-_]{8,}$", message = "La contraseña debe tener al menos 8 caracteres, incluyendo mayúscula, minúscula, número y símbolo")
    private String password;
}
