package com.shonali.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shonali.model.User;
import com.shonali.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/save")
	public ResponseEntity<?> saveUser(@RequestBody User user){
		
		User saveUser = userService.saveUser(user);
		
		if(ObjectUtils.isEmpty(saveUser)) {
			return new ResponseEntity<>("user not saved",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
	}
	
	@GetMapping("/getUsers")
	public ResponseEntity<?> getAllUser(){
		List<User> allUsers = userService.getAllUser();
		return new ResponseEntity<>(allUsers,HttpStatus.OK);
		
	}
	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody User user){
		
		User saveUser = userService.saveUser(user);
		
		if(ObjectUtils.isEmpty(saveUser)) {
			return new ResponseEntity<>("user not updated",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer id){
		userService.deleteUser(id);
		return new ResponseEntity<>("Delete Successfully!",HttpStatus.OK);
	}
	
	@GetMapping("/getUser/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Integer id){
		User userById = userService.getUserById(id);
		if(ObjectUtils.isEmpty(userById)) {
			return new ResponseEntity<>("user not found",HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<>(userById,HttpStatus.OK);
	}
	
	
	

}
