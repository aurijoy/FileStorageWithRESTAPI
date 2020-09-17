package com.example.demo.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
	
	/*Adding  serial Versioning*/
	private static final long serialVersionUID = 2647760021484317266L;
	private User user;
	
	// Using a Decorator Pattern
	
	public void setUser(User user) {
		this.user = user;
		
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		
//      this implementation was for sets but for DBInit() simplification we are going to 
//		change it for a purely string based ',' delineated list of Role's.
//		return user.getRoles().stream().
//		map(role-> new SimpleGrantedAuthority("ROLE_"+role)).
//		collect(Collectors.toList());
//		
		
//    method to obtain Roles when stored as a string first create a list of granted authorities
		 List<GrantedAuthority> authorities = new ArrayList<>();
		
		// Extract list of roles with format (ROLE_name)
        this.user.getRoleList().forEach(r -> {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + r);
            authorities.add(authority);
        });
		
        return authorities;
	}

	@Override
	public String getPassword() {
		
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.user.getUserName();
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

	

}
