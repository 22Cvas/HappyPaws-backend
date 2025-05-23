package org.ncapas.happypawsbackend.services.implem;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ncapas.happypawsbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtTokenFilter {

    @Autowired
    UserService userService;

    @Autowired
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;


        filterChain.doFilter(request, response);
    }

}
