package com.example.demo.Service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.Entity.Role;
import com.example.demo.Entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CustomUserDetails implements UserDetails {

	/**
	 * 
	 */
	
	/*This is a break point*/
	private static final long serialVersionUID = 2647760021484317266L;
	private User user;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return user.getRoles().stream().
		map(role-> new SimpleGrantedAuthority("ROLE_"+role)).
		collect(Collectors.toList());
		
		
		
	}

	@Override
	public String getPassword() {
		
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

	public void setUser(User user) {
		this.user = user;
		
	}

}
