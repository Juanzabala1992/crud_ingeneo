package com.login.authentication.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Integer id;

    @NotEmpty
    @NotBlank(message = "Id no puede estar vacio")
    @NotNull
    @Column(nullable = false, unique = true)
    private String idUser;

    @NotEmpty
    @NotBlank(message = "Nombre no puede estar vacio")
    private String firstname;

    @NotEmpty
    @NotBlank(message = "Apellido no puede estar vacio")
    private String lastName;

    @NotEmpty
    @NotNull
    @NotBlank(message = "Correo no puede estar vacio")
    @Column(nullable = false, unique = true)
    private String email;

    @NotEmpty
    @NotNull
    @NotBlank(message = "Contraseña no puede estar vacio")
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  List.of( new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Rol getRole() {
        return role;
    }

    public void setRole(Rol role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
