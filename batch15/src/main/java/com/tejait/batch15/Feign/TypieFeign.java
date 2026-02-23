package com.tejait.batch15.Feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="TypiCode", url = "https://jsonplaceholder.typicode.com/")
public interface TypieFeign {
	
	
@GetMapping("comments")
	public List<CommentsDto> getComments(); // list of Comments....
	
	
}
