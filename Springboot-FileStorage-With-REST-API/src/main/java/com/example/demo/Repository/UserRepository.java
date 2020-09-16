package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByUserName(String userName);
//	User findByUser  (Integer user_id);

}
