package org.ncapas.happypawsbackend.Domain.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class LoginDto {

    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
