package com.code81.library_management;

import com.code81.library_management.data.entity.Role;
import com.code81.library_management.data.entity.SystemUser;
import com.code81.library_management.data.repository.RoleRepository;
import com.code81.library_management.data.repository.SystemUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication
public class LibraryManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner updatePassword(SystemUserRepository userRepo, PasswordEncoder encoder) {
//		return args -> {
//			Optional<SystemUser> optionalUser = userRepo.findByUsername("staff1");
//			if (optionalUser.isPresent()) {
//				SystemUser user = optionalUser.get();
//				user.setPasswordHash(encoder.encode("admin123"));
//				userRepo.save(user);
//				System.out.println("Password updated successfully!");
//			} else {
//				System.out.println("User not found!");
//			}
//		};
//	}
}
