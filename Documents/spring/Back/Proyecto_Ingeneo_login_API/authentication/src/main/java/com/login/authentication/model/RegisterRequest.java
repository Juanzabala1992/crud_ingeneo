package com.login.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

   @NotBlank(message = "Id no puede estar vacio")
   private String idUser;

   @NotBlank(message = "Nombre no puede estar vacio")
   private String firstname;

   @NotBlank(message = "Apellido no puede estar vacio")
   private String lastName;

   @NotBlank(message = "Correo no puede estar vacio")
   private String email;

   @NotBlank(message = "Contraseña no puede estar vacio")
   private String password;
}
