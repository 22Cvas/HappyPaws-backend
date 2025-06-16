package org.ncapas.happypawsbackend.services;

import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import org.ncapas.happypawsbackend.Domain.Entities.RefreshToken;
import org.ncapas.happypawsbackend.Domain.Entities.User;
import org.ncapas.happypawsbackend.Domain.dtos.UserDto;
import org.ncapas.happypawsbackend.repositories.RefreshTokenRepository;
import org.ncapas.happypawsbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@NoArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public Optional<User> findUserByEmail(String email) {

        return userRepository.findUserByEmail(email);
    }

    public boolean existByDui(String dui) {
        return userRepository.existsByDUI(dui);
    }

    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> request = new ArrayList<>();
        for (User u : users) {
            UserDto user = new UserDto();
            user.setId_user(u.getId());
            user.setName(u.getName());
            user.setEmail(u.getEmail());
            user.setDUI(u.getDUI());
            user.setPhone(u.getPhone());
            user.setRol(u.getRol().getName().name());
            request.add(user);
        }
        return request;
    }

    public Optional<UserDto> getUserById(UUID id) {
        return userRepository.findById(id)
                .map(user -> {
                    UserDto dto = new UserDto();
                    dto.setId_user(user.getId());
                    dto.setName(user.getName());
                    dto.setEmail(user.getEmail());
                    dto.setDUI(user.getDUI());
                    dto.setPhone(user.getPhone());
                    dto.setRol(user.getRol().getName().name());
                    return dto;
                });
    }


    @Transactional
    public void deleteUser(UUID id) {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

            System.out.println(">>> Usuario encontrado: " + user.getEmail());

            refreshTokenRepository.deleteByUser(user);
            System.out.println(">>> Tokens eliminados");

            userRepository.delete(user);
            System.out.println(">>> Usuario eliminado");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Fallo al eliminar usuario");
        }
    }


    public UserDto updateUser(UUID id, UserDto updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPhone(updatedUser.getPhone());
        userRepository.save(user);

        UserDto dto = new UserDto();
        dto.setId_user(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setDUI(user.getDUI());
        dto.setPhone(user.getPhone());
        dto.setRol(user.getRol().getName().name());

        return dto;
    }

    public Optional<UserDto> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .map(user -> {
                    UserDto dto = new UserDto();
                    dto.setId_user(user.getId());
                    dto.setName(user.getName());
                    dto.setEmail(user.getEmail());
                    dto.setDUI(user.getDUI());
                    dto.setPhone(user.getPhone());
                    dto.setRol(user.getRol().getName().name());
                    return dto;
                });

    }
}

