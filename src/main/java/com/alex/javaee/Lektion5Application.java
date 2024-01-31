package com.alex.javaee;

import com.alex.javaee.dto.UserEntityDTORecord;
import com.alex.javaee.models.user.Roles;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Lektion5Application {

	public static void main(String[] args) {
		SpringApplication.run(Lektion5Application.class, args);

		String password = "123";
		String encodedPassword ;
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder ();
		encodedPassword = passwordEncoder .encode(password);
		System.out.println("Encoded password = " + encodedPassword );
		System.out.println("Does the password match: " + passwordEncoder .matches(password, encodedPassword)
		);










	}

}
