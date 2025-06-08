package org.ncapas.happypawsbackend.services;

import lombok.NoArgsConstructor;
import org.ncapas.happypawsbackend.Domain.Entities.User;
import org.ncapas.happypawsbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public boolean existByDui(String dui){
        return userRepository.existsByDUI(dui);
    }

    public boolean existByEmail(String email){
        return userRepository.existsByEmail(email);
    }

}

