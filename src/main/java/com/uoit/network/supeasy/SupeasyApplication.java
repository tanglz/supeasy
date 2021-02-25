package com.uoit.network.supeasy;

import com.uoit.network.supeasy.filter.JwtFilter;
import com.uoit.network.supeasy.service.StorageProperties;
import com.uoit.network.supeasy.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class SupeasyApplication {
	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		List<String> urlPatterns = new ArrayList<>();
		urlPatterns.add("/api/image/upload");
		urlPatterns.add("/api/product/add");
		urlPatterns.add("/api/product/all");
		registrationBean.addUrlPatterns(urlPatterns.toArray(new String[urlPatterns.size()]));
		return registrationBean;
	}
	public static void main(String[] args) {
		SpringApplication.run(SupeasyApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.init();
		};
	}

}
