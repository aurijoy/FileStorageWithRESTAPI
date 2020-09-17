package com.example.demo.Entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString


@AllArgsConstructor
public class Role {
	@Id
	@GeneratedValue
	private int role_id;
	private String role = "USER";

	
	
	// Single arg constructor for testing with DBInit() whether it generates value at initialization
	public Role(String role) {
		super();
		this.role = role;
	}
	
	//ALl arg constructor for testing with DBInit() whether it initializes
	public Role(int id, String role) {
		super();
		this.role_id=id;
		this.role = role;
	}
	

	
//		public Role(String string) {
//			this.role= string;
//		}
}
