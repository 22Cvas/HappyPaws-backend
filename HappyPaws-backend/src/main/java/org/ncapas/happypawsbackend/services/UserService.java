package org.ncapas.happypawsbackend.services;

import lombok.NoArgsConstructor;
import org.ncapas.happypawsbackend.Domain.Entities.User;
import org.ncapas.happypawsbackend.Domain.dtos.UserDto;
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
            user.setId_user(u.getId_user());
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
                    dto.setId_user(user.getId_user());
                    dto.setName(user.getName());
                    dto.setEmail(user.getEmail());
                    dto.setDUI(user.getDUI());
                    dto.setPhone(user.getPhone());
                    dto.setRol(user.getRol().getName().name());
                    return dto;
                });
    }
    public void deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
        userRepository.deleteById(id);
    }

    public UserDto updateUser(UUID id, UserDto updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPhone(updatedUser.getPhone());
        userRepository.save(user);

        UserDto dto = new UserDto();
        dto.setId_user(user.getId_user());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setDUI(user.getDUI());
        dto.setPhone(user.getPhone());
        dto.setRol(user.getRol().getName().name());

        return dto;
    }


}

