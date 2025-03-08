package com.siblingscup.coffee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class CoffeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeeApplication.class, args);
	}


}
