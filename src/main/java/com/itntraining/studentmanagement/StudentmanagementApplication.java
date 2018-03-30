package com.itntraining.studentmanagement;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@SpringBootApplication
public class StudentmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentmanagementApplication.class, args);
	}
	@Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository userRepository) throws Exception{
        System.out.println("hello ");
        if(userRepository.count()==0){
      
           userRepository.save(new User( "admin", "admin", Arrays.asList(new Role("USER"),new Role("Actuator")) ));
           System.out.println("saved");
       }
        builder.userDetailsService((username)-> 
            new CustomUserDetailsService(userRepository.findByUsername(username))
        );
	}
}
