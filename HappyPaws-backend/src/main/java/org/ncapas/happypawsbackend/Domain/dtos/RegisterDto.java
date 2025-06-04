package org.ncapas.happypawsbackend.Domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank
    private String dui;

    @NotBlank
    private String phone;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;

    private Rol role;
}
