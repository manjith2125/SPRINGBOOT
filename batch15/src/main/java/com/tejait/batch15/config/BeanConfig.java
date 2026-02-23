package com.tejait.batch15.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BeanConfig {
	
	@Bean
	public RestTemplate restTemplate() {
		
		return new RestTemplate();
	}

@Bean	
	public WebClient webClient() { // webClient is a interface....
		return WebClient.builder()
				.baseUrl("http://localhost:8081") // This URL is your wish u can give baseUrl here, or you can give in controller class...
					.build();
			
		
	}
	
	
	
	
	
	
}
