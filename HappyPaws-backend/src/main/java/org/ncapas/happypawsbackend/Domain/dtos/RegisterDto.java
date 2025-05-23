package org.ncapas.happypawsbackend.Domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Data
public class RegisterDto {
    private String roleName;

    @NotBlank
    @NotNull
    private String name;
    @NotBlank
    @NotNull
    private String email;

    @NotBlank
    @NotNull
    private String password;
}
