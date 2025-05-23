package org.ncapas.happypawsbackend.services;

import lombok.Builder;
import org.ncapas.happypawsbackend.Domain.Entities.Users;
import org.ncapas.happypawsbackend.Domain.dtos.AuthResponse;
import org.ncapas.happypawsbackend.Domain.dtos.RegisterDto;
import org.ncapas.happypawsbackend.Domain.dtos.TokenDto;
import org.ncapas.happypawsbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    @Autowired
    private static UserRepository userRepository;


    public static AuthResponse login(TokenDto request) {
        return null;
    }

    public static AuthResponse register(RegisterDto request) {
        Users user = new Users();
        user.getName();
        user.getEmail();
        user.getPassword();
        user.getRol();

        userRepository.save(user);

        return null;
    }
}
