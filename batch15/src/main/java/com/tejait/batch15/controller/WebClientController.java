package com.tejait.batch15.controller;

import org.jspecify.annotations.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;
@AllArgsConstructor
@RestController
@RequestMapping("webClient")
public class WebClientController {
	
	WebClient webClient;
	@GetMapping("asynchName")
	public String asynchronousType() {
		String name="Teja IT";
		Mono<String> tagLine=webClient.get()
			.uri("/test/tagline")
			.retrieve()
			.bodyToMono(String.class);
		return name.concat("  "+tagLine.block()); // Asynchoronous type ...Block
			
		
	}
	
	@GetMapping("synchName")
	public String synchronousType() {
		String name="Teja IT";
		String tagLine=webClient.get()
			.uri("/test/tagline")
			.retrieve()
			.bodyToMono(String.class)
			.block();    // Synchronous type
		return name.concat("  "+tagLine);
			
		
	}
	
	
	

}
