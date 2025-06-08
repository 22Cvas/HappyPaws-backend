package org.ncapas.happypawsbackend.config;

import org.ncapas.happypawsbackend.Domain.Entities.Rol;
import org.ncapas.happypawsbackend.Domain.Entities.Size;
import org.ncapas.happypawsbackend.Domain.Entities.User;
import org.ncapas.happypawsbackend.Domain.Enums.UserRol;
import org.ncapas.happypawsbackend.repositories.RoleRepository;
import org.ncapas.happypawsbackend.repositories.SizeRepository;
import org.ncapas.happypawsbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args){
        seedRoles();
        seedSizes();
        seedDefaultUsers();
    }

    private void seedRoles(){
        for (UserRol userRol : UserRol.values()) {
            if (roleRepository.findRolByName(userRol).isEmpty()) {
                Rol rol = new Rol();
                rol.setName(userRol);
                roleRepository.save(rol);
            }
        }
    }

    private void seedSizes(){
        createSizeIfNotExists("PEQUEÑO");
        createSizeIfNotExists("MEDIANO");
        createSizeIfNotExists("GRANDE");
    }

    private void createSizeIfNotExists(String name) {
        if (sizeRepository.findByNameIgnoreCase(name).isEmpty()) {
            Size size = new Size();
            size.setName(name);
            sizeRepository.save(size);
        }
    }

    private void seedDefaultUsers() {
        createUserIfNotExists("admin@happypaws.com", "123456", "ADMIN", "12345678-1");
        createUserIfNotExists("colaborador@happypaws.com", "123456", "COLABORADOR", "12345678-2");
        createUserIfNotExists("adoptante@happypaws.com", "123456", "ADOPTANTE", "12345678-3");
    }

    private void createUserIfNotExists(String email, String password, String rolName, String dui) {
        if (userRepository.findUserByEmail(email).isEmpty() &&
                userRepository.findByDUI(dui).isEmpty()) {
            Rol rol = roleRepository.findRolByName(UserRol.valueOf(rolName))
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

            User user = new User();
            user.setName(rolName.toLowerCase());
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            user.setPhone("12345678");
            user.setDUI(dui);
            user.setRol(rol);
            user.setState(1);
            userRepository.save(user);
        }
    }

}
