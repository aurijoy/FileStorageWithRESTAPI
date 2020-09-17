package com.example.demo.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
//@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
	@Id
	@GeneratedValue
	private int user_id;
	private String userName;
	private String password;
	private String email;
	
// We are changing the way roles are stored from a Set of Role Set<Role> to 
	//   a ',' delineated string of roles this will help with DBInit()
	
//	@OneToMany(cascade=CascadeType.ALL)
//	@JoinTable(name="user_role", joinColumns=@JoinColumn(name="user_id"),inverseJoinColumns= @JoinColumn(name="role_id"))
//	private Set<Role> roles;

	private String roles="";
	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

//	public Role getRoles() {
//		return role;
//	}
//
//	public void setRoles(Role role) {
//		this.role = role;
//	}
	
	
//	@OneToMany (targetEntity = File.class, cascade = CascadeType.ALL)
//	@JoinColumn(name= "user_file", referencedColumnName = "user-id")
//	private List<File> files;

}
