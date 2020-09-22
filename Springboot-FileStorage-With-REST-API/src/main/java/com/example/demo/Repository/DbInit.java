package com.example.demo.Repository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.demo.Entity.User;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {
    
	private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public DbInit(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Delete all
        this.userRepository.deleteAll();

        // Create users
        User user1 = new User("user1",passwordEncoder.encode("user1"),"user1@mail.com","USER");
        User user2 = new User("user2",passwordEncoder.encode("user1"),"user2@mail.com","USER");
        

        List<User> users = Arrays.asList(user1,user2);

        // Save to db
        this.userRepository.saveAll(users);
    }
}
