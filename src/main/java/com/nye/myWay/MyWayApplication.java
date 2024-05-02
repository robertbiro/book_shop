package com.nye.myWay;

import com.nye.myWay.entities.ApplicationUser;
import com.nye.myWay.entities.Role;
import com.nye.myWay.repositories.ApplicationUserRepository;
import com.nye.myWay.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class MyWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyWayApplication.class, args);

	}
		@Bean
		CommandLineRunner runner (RoleRepository roleRepository, ApplicationUserRepository applicationUserRepository,
				PasswordEncoder passwordEncoder) {
			return args -> {
				if (roleRepository.findByAuthority("ADMIN").isPresent()) return;
				Role adminRole = roleRepository.save(new Role("ADMIN"));
				roleRepository.save(new Role("USER"));

				Set<Role> roleSet = new HashSet<>();
				roleSet.add(adminRole);
				ApplicationUser admin = new ApplicationUser(1L, "admin",
						passwordEncoder.encode("password"), roleSet);
				applicationUserRepository.save(admin);

			};
		}

}
