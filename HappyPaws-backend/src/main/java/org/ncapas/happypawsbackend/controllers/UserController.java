package org.ncapas.happypawsbackend.controllers;

import jakarta.validation.Valid;
import org.ncapas.happypawsbackend.Domain.Entities.User;
import org.ncapas.happypawsbackend.Domain.dtos.UserDto;
import org.ncapas.happypawsbackend.services.UserService;
import org.ncapas.happypawsbackend.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceImpl userServiceimpl;

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        try {
            System.out.println(">>> LLAMADO A DELETE con ID: " + id);
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable UUID id, @RequestBody UserDto updatedUser) {
        try {
            UserDto userDTO = userService.updateUser(id, updatedUser);
            return ResponseEntity.ok(userDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email")
    public ResponseEntity<?> getUserByEmail(@RequestParam @Valid String email) {
        try {
            return userService.getUserByEmail(email)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());

        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }

    }

}
