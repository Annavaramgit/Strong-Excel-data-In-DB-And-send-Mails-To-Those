package com.mailsending;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MailSendingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailSendingApplication.class, args);
	}

	@Bean
	//@LoadBalanced
	public RestTemplate restTemplate() {
	return new RestTemplate();
	}
}
