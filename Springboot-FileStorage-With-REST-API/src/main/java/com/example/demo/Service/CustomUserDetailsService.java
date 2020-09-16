package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUserName(username);
		CustomUserDetails userDetails = new CustomUserDetails();
		userDetails.setUser(user);
//		if (user!=null) {
//			userDetails= new CustomUserDetails();
//			userDetails.setUser(user);
//		}else {
//			throw new UsernameNotFoundException("User with given username does not exist:"+username);
//		}
		
		return userDetails;
	}
	
	
	
	
	

}
