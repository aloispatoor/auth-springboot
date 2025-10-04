package com.auth.config;

import com.auth.entity.UserEntity;
import com.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {

            UserEntity admin = new UserEntity();
            admin.setUsername("Amiya");
            admin.setPassword(passwordEncoder.encode("123"));
            admin.setRoles(Set.of("ADMIN", "USER"));
            admin.setEnabled(true);

            UserEntity user = new UserEntity();
            user.setUsername("Ejaz");
            user.setPassword(passwordEncoder.encode("123"));
            user.setRoles(Set.of("USER"));
            user.setEnabled(true);

            userRepository.saveAll(List.of(admin, user));

            System.out.println("Users successfully added!");
        } else {
            System.out.println("User is already in the database");
        }
    }
}
