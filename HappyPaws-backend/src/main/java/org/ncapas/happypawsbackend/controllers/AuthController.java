package org.ncapas.happypawsbackend.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.ncapas.happypawsbackend.Domain.Entities.AccessToken;
import org.ncapas.happypawsbackend.Domain.Entities.RefreshToken;
import org.ncapas.happypawsbackend.Domain.Entities.Rol;
import org.ncapas.happypawsbackend.Domain.Entities.User;
import org.ncapas.happypawsbackend.Domain.dtos.*;
import org.ncapas.happypawsbackend.repositories.AccessTokenRepository;
import org.ncapas.happypawsbackend.repositories.RoleRepository;
import org.ncapas.happypawsbackend.repositories.UserRepository;
import org.ncapas.happypawsbackend.services.AuthService;
import org.ncapas.happypawsbackend.services.RefreshTokenService;
import org.ncapas.happypawsbackend.services.UserService;
import org.ncapas.happypawsbackend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
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

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private AccessTokenRepository accessTokenRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDto request, HttpServletResponse response) {

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
        return ResponseEntity.ok(Map.of("message", "Usuario registrado con exito"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto request, HttpServletResponse response) {
        try {
            String jwt = authService.loginAndSaveToken(request);

            User user = authService.getValidUser(request);
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

            ResponseCookie cookie = ResponseCookie.from("refresh_token", refreshToken.getToken())
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(7 * 24 * 60 * 60)
                    .build();

            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

            return ResponseEntity.ok(Map.of("token", jwt));
        } catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Credenciales incorrectas"));
        }
    }


    @GetMapping("/refresh")
    public ResponseEntity<?> refresh(@CookieValue(name = "refresh_token", required = false)
                                     String refreshTokenCookie) {
        if (refreshTokenCookie == null || refreshTokenCookie.isBlank()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No se encontro refresh token");
        }

        Optional<RefreshToken> storedToken = refreshTokenService.getByToken(refreshTokenCookie);
        if (storedToken.isEmpty() || storedToken.get().isRevoked()
                || refreshTokenService.isTokenExpired(storedToken.get())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token invalido o expirado");
        }

        User user = storedToken.get().getUser();
        String newAccessToken = jwtUtil.generateToken(user);
        return ResponseEntity.ok(Map.of("token", newAccessToken));
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> "refresh_token".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);

        if (refreshToken != null) {
            refreshTokenService.revokeToken(refreshToken);
        }

        ResponseCookie deleteCookie = ResponseCookie.from("refresh_token", "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)
                .sameSite("Strict")
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, deleteCookie.toString())
                .body(Map.of("message", "Sesion cerrada exitosamente"));
    }
    
}


