package com.tejait.batch15.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

@RequestMapping("/ctrlTest")
public class TestController {
	
	
	@ResponseBody // WithOut This Annitation we cant use Controller..... 
	
	@GetMapping("getName")
	public String getName() {
		
		return "Controller Test Class";
	}

}
