package com.app.NE;

import com.app.NE.enums.ERole;
import com.app.NE.models.Role;
import com.app.NE.repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {
	@Autowired
	private IRoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner init() {
		return args -> {
			if(roleRepository.findByRole(ERole.ROLE_ADMIN).isEmpty()){
				Role adminRole = new Role();
				adminRole.setRole(ERole.ROLE_ADMIN);
				roleRepository.save(adminRole);
			}
			if(roleRepository.findByRole(ERole.ROLE_CUSTOMER).isEmpty()){
				Role customerRole = new Role();
				customerRole.setRole(ERole.ROLE_CUSTOMER);
				roleRepository.save(customerRole);
			}
		};
	}

}
