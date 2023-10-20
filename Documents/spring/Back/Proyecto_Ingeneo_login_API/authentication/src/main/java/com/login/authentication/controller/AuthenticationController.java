package com.login.authentication.controller;

import com.login.authentication.model.AuthenticationResponse;
import com.login.authentication.model.AuthenticatonRequest;
import com.login.authentication.model.PasswordModel;
import com.login.authentication.model.RegisterRequest;
import com.login.authentication.repository.UserRepository;
import com.login.authentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://operations-managements-frontend-bucket.s3-website-eu-west-1.amazonaws.com/")
public class AuthenticationController {

    private final AuthenticationService service;
    private final UserRepository userRepository;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody RegisterRequest request , BindingResult result
    ){
        return ResponseEntity.ok(service.register(request, result));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @RequestBody AuthenticatonRequest request, BindingResult result
    ){
        return ResponseEntity.ok(service.authenticate(request, result));
    }

    @PostMapping("/password")
    public ResponseEntity<ResponseEntity<String>> updatePassword(
            @Valid @RequestBody PasswordModel data,
            BindingResult result
    ){
        return ResponseEntity.ok().body(service.updatePassword(data, result));
    }
}
