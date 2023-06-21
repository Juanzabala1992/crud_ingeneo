package com.login.authentication.controller;

import com.login.authentication.model.AuthenticationResponse;
import com.login.authentication.model.AuthenticatonRequest;
import com.login.authentication.model.RegisterRequest;
import com.login.authentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
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
}
