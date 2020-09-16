package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;

@RestController
@RequestMapping("")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	
	
//  No need for this bean here there is already a Bean for BCryptPasswordEncoder in the Service just 
//  just Autowire that 
//	@Bean
//	public BCryptPasswordEncoder encodePWD() {
//		return new BCryptPasswordEncoder();
//	}
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello World";
	}
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder; 
	
	
	//for the purpose of adding users to the database with User privilege strictly to be used for testing purpose
	
	@RequestMapping ("/user/blob")
	public String login() {
		return "login successful";
		
	}
	
	@PostMapping ("/add")
	public String addUser(@RequestBody User user) {
		String password = user.getPassword();
		String encodepass = passwordEncoder.encode(password);
		user.setPassword(encodepass);
		userRepository.save(user);
		return "user added successfully";
	}

}
