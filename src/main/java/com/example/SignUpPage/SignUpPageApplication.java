package com.example.SignUpPage;
import com.example.SignUpPage.Model.ApplicationUser;
import com.example.SignUpPage.Model.RoleModel;
import com.example.SignUpPage.Repository.ApplicationRepository;
import com.example.SignUpPage.Repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SignUpPageApplication {
	public static void main(String[] args) {
		SpringApplication.run(SignUpPageApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, ApplicationRepository userRepository, PasswordEncoder passwordEncoder){
		return args -> {
			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
			RoleModel adminRole = roleRepository.save(new RoleModel("ADMIN"));
			roleRepository.save(new RoleModel("USER"));
			Set<RoleModel> roles = new HashSet<>();
			ApplicationUser admin = new ApplicationUser(1L, "admin", passwordEncoder.encode("password"), roles);
			userRepository.save(admin);
		};
	}
}
