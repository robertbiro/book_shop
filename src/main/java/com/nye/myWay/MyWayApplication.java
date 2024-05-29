package com.nye.myWay;

import com.nye.myWay.config.AdminConfig;
import com.nye.myWay.entities.ApplicationUser;
import com.nye.myWay.entities.Role;
import com.nye.myWay.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class MyWayApplication implements CommandLineRunner {
	private final AdminConfig adminConfig;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(MyWayApplication.class, args);
		}

	@Override
	public void run(String... args) {
		String adminUsername = adminConfig.getAdminUsername();
		String adminPassword = adminConfig.getAdminPassword();

		if(!userRepository.findByUsername(adminUsername).isPresent()) {
			ApplicationUser adminUser = new ApplicationUser();
			adminUser.setUsername(adminUsername);
			adminUser.setPassword(passwordEncoder.encode(adminPassword));
			adminUser.setEmail("admin@admin.com");
			adminUser.setRole(Role.ADMIN);
			userRepository.save(adminUser);
		}
	}
}
