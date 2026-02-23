package com.tejait.batch15.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@RequestMapping("/restTemplate")
public class RestTemplateController {
	
	
	RestTemplate restTemplate; // Here for this we need to create BEAN manually .,, see beanconfig in config...
	
	@GetMapping("getName")
	public String getName() {
		String name="Teja IT";
		String tagline=restTemplate.getForObject("http://localhost:8081/test/tagline", String.class);
		return name.concat(" "+tagline);
		
		
		
		
	}

}
