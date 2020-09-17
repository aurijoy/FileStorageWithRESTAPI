//package com.example.demo.Repository;
//
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import com.example.demo.Entity.Role;
//import com.example.demo.Entity.User;
//
//
//
//
////private BCryptPasswordEncoder passwordEncoder;
//
//@Component
//public class DBInit implements ApplicationRunner {
//
//	private UserRepository userRepository;
//    private BCryptPasswordEncoder passwordEncoder;
//	
//    @Autowired
//    public DataLoader(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//    }
//    
//    @Override
//	public void run(ApplicationArguments args) throws Exception {
//		// Create Users
////    	int role_id=123;
////    	Role user = new Role ("USER");
////    	Set<Role> set = new Set<Role>();
//    	
//    	
//    	User user1 = new User("user1",passwordEncoder.encode("user1"),"USER","");
//        User admin = new User("admin",passwordEncoder.encode("admin"),"ADMIN","ACCESS_TEST1,ACCESS_TEST2");
//        User user2 = new User("user2",passwordEncoder.encode("user2"),"MANAGER","ACCESS_TEST1");
//		
//	}
//    
////    public DbInit(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
////        this.userRepository = userRepository;
////        this.passwordEncoder = passwordEncoder;
////    }
//    
////	@Override
////	public void run(String... args) throws Exception {
////		// TODO Auto-generated method stub
////		
//	}
//
//}
