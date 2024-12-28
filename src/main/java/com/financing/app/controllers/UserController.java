package com.financing.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.financing.app.exceptions.ResourceNotFoundException;
import com.financing.app.models.User;
import com.financing.app.models.UserModel;
import com.financing.app.service.UserService;
import jakarta.validation.Valid;
import java.util.List;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/regist")
	public ResponseEntity<User> save(@Valid @RequestBody UserModel user){
		
		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
	}
	@GetMapping("/users")
	public List<User> userList(){
		return userService.userList();
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> readUser(@PathVariable Long id) {
		return new ResponseEntity<User>(userService.readUser(id),HttpStatus.OK);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> update(@RequestBody UserModel user,@PathVariable Long id){
		
		return new ResponseEntity<User>(userService.update(user, id), HttpStatus.OK);
	}
	@DeleteMapping("/users/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) throws ResourceNotFoundException{
		
		userService.delete(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);	
	}

}
