package com.uoit.network.supeasy;

import com.uoit.network.supeasy.data.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SupeasyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupeasyApplication.class, args);
	}

}
