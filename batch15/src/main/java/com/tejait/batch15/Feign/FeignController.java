package com.tejait.batch15.Feign;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@RequestMapping("feign")

public class FeignController {
	
	TypieFeign typieFeign;

	
	@GetMapping("getComments")
	public ResponseEntity<List<CommentsDto>> getComments(){
		List<CommentsDto> comments=typieFeign.getComments();
		
		return new ResponseEntity<>(comments, HttpStatus.OK);
		
	
	}
	
	

}
