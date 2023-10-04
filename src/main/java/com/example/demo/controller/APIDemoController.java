package com.example.demo.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserRequest;
import com.example.demo.model.UserResponse;

/**
 * 
 * @author jack
 *
 */
@RestController
public class APIDemoController {

	
	@GetMapping("/users/{name}")
	UserResponse one(@PathVariable String name) {
		UserResponse userResponse = new UserResponse();
		userResponse.setName("Hi! "+name);
		
		Integer.parseInt("1");
		return userResponse;
	}
	
	
//	@PostMapping("/users")
//	UserResponse one(@RequestParam(name = "id") Long id ,@RequestParam(name = "name") String name) {
//		UserRequest userRequest = new UserRequest();
//		userRequest.setId(id);
//		userRequest.setName(name);
//		UserResponse userResponse = new UserResponse();
//		userResponse.setMsg("The user's name:  "+ userRequest.getName()+", the user's id:"+userRequest.getId());
//		return userResponse;
//	}
	
	@PostMapping("/users")
	UserResponse one(@RequestBody UserRequest userRequest) {
		UserResponse userResponse = new UserResponse();
		userResponse.setMsg("The user's name:  "+ userRequest.getName()+", the user's id:"+userRequest.getId());
		return userResponse;
	}
	
	
	
}
