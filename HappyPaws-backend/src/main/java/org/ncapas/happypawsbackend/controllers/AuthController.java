package org.ncapas.happypawsbackend.controllers;

import jakarta.validation.Valid;
import org.ncapas.happypawsbackend.Domain.Entities.Rol;
import org.ncapas.happypawsbackend.Domain.dtos.*;
import org.ncapas.happypawsbackend.repositories.RoleRepository;
import org.ncapas.happypawsbackend.repositories.UserRepository;
import org.ncapas.happypawsbackend.services.AuthService;
import org.ncapas.happypawsbackend.services.UserService;
import org.ncapas.happypawsbackend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    UserDetailsService userDetailsService;

    private Pattern DUI_REGEX = Pattern.compile("^\\d{8}-\\d{1}$");
    private  Pattern EMAIL_REGEX = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private  Pattern PHONE_REGEX = Pattern.compile("^\\d{8}$");

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDto request) {

        if (request.getDui() == null || !DUI_REGEX.matcher(request.getDui()).matches()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("EL DUI es invalido");
        }
        if (userService.existByDui(request.getDui())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El DUI ya está registrado");
        }

        if (userService.existByEmail(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El Email ya está registrado");
        }

        if (request.getEmail() == null || !EMAIL_REGEX.matcher(request.getEmail()).matches()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("EL email es invalido");
        }
        if (request.getPhone() == null || !PHONE_REGEX.matcher(request.getPhone()).matches()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("EL telefono es invalido");
        }


        authService.register(request);
            return ResponseEntity.ok("Registro exitoso");

        }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto request) {
        try {
            String token = authService.login(request);
            Map<String, String> response = Map.of("token", token);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Credenciales inválidas"));
        }
    }


}
