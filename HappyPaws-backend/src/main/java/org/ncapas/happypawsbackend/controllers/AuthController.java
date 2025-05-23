package org.ncapas.happypawsbackend.controllers;

import org.ncapas.happypawsbackend.Domain.dtos.AuthResponse;
import org.ncapas.happypawsbackend.Domain.dtos.RegisterDto;
import org.ncapas.happypawsbackend.Domain.dtos.TokenDto;
import org.ncapas.happypawsbackend.services.AuthService;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

 @PostMapping(value = "/login")
 public ResponseEntity<AuthResponse> login(@RequestBody TokenDto request) {

  return ResponseEntity.ok(AuthService.login(request));
 }

 @PostMapping(value = "/register")
 public ResponseEntity<AuthResponse> register(@RequestBody RegisterDto request) {

  return ResponseEntity.ok(AuthService.register(request));
 }
}
