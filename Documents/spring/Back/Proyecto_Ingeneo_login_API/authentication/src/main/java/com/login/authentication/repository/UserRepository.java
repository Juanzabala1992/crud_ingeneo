package com.login.authentication.repository;

import com.login.authentication.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

    Optional<User> findByIdUser(String idUser);
}
