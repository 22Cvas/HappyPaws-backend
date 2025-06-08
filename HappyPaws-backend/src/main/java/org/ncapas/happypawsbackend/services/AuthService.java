package org.ncapas.happypawsbackend.services;

import org.ncapas.happypawsbackend.Domain.Entities.Rol;
import org.ncapas.happypawsbackend.Domain.Entities.Token;
import org.ncapas.happypawsbackend.Domain.Entities.User;
import org.ncapas.happypawsbackend.Domain.Enums.UserRol;
import org.ncapas.happypawsbackend.Domain.dtos.LoginDto;
import org.ncapas.happypawsbackend.Domain.dtos.RegisterDto;
import org.ncapas.happypawsbackend.repositories.RoleRepository;
import org.ncapas.happypawsbackend.repositories.TokenRepository;
import org.ncapas.happypawsbackend.repositories.UserRepository;
import org.ncapas.happypawsbackend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    public void register(RegisterDto request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(String.valueOf(request.getPhone()));
        user.setDUI(String.valueOf(request.getDui()));
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Rol rolUser = roleRepository.findRolByName(UserRol.ADOPTANTE)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        user.setRol(rolUser);
        user.setState(1);
        userRepository.save(user);
    }
        /*
        var jwtToken = jwtUtils.generateToken(user);

        Token token = new Token();
        token.setToken(jwtToken);
        token.setUser(user);
        token.setRevoked(false);
        token.setExpired(false);
        tokenRepository.save(token);

      //Falta guardar el token

    }

*/
      public String login(LoginDto request) {
      User user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Contraseña incorrecta");
        }

        String jwtToken =  jwtUtils.generateToken(user);


          // Guardar token en BD
          Token token = new Token();
          token.setToken(jwtToken);
          token.setUser(user);
          token.setExpired(false);
          token.setRevoked(false);
          tokenRepository.save(token);

          return jwtToken;
    }

}


