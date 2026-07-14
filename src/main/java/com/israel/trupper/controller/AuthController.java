package com.israel.trupper.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.israel.trupper.security.JwtService;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthController(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/login")
    public ResponseToken login(@RequestBody RequestToken request) {
        
        Authentication auth;
        try {
            auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"usuario o contraseña equivocados");
        }
        UserDetails userDetails = (UserDetails)auth.getPrincipal();
        String token = jwtService.generarToken(userDetails);
        return new ResponseToken(token);
    }

    public record RequestToken(String username, String password) {
    }
    public record ResponseToken(String token) {
    }
    
}
