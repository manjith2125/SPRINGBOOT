package com.tejait.batch15.Feign;

import lombok.Data;

@Data
public class CommentsDto {

	private int postId;
	private int id;
	private String name;
	private String email;
	private String body;
	
	
	
}
