package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
// Admin Controller in order to add Role Based Security

// This has been set to AnyRole For Testing Purpose only
@PreAuthorize("hasAnyRole")
@RestController
@RequestMapping("/Admin")
public class AdminController {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder; 
	
	@PostMapping ("/add")
	public String addUser(@RequestBody User user) {
		String password = user.getPassword();
		String encodepass = passwordEncoder.encode(password);
		user.setPassword(encodepass);
		userRepository.save(user);
		return "user added successfully";
	}
}
