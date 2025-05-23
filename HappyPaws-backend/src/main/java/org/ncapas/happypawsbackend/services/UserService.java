package org.ncapas.happypawsbackend.services;

import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.ncapas.happypawsbackend.Domain.Entities.Users;
import org.ncapas.happypawsbackend.Domain.dtos.LoginDto;
import org.ncapas.happypawsbackend.Domain.dtos.RegisterDto;
import org.ncapas.happypawsbackend.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class UserService {
    private UserRepository userRepository;
    public void createUsers(RegisterDto userInfo){
        Users users = new Users();
        users.getPassword();
        users.getName();
        users.getEmail();
        userRepository.save(users);
    }

}
