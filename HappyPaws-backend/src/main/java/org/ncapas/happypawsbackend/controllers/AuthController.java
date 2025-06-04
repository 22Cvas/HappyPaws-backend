package org.ncapas.happypawsbackend.controllers;

import jakarta.validation.Valid;
import org.ncapas.happypawsbackend.Domain.dtos.*;
import org.ncapas.happypawsbackend.repositories.TokenRepository;
import org.ncapas.happypawsbackend.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class AuthController {


}
