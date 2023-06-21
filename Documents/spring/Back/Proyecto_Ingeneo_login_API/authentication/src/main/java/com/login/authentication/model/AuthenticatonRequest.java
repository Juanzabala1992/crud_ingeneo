package com.login.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticatonRequest {

    @NotEmpty
    @NotBlank(message = "Campo requerido")
    private String email;

    @NotBlank(message = "Campo requerido")
    @NotEmpty
    private String password;
}
