package com.financing.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.financing.app.models.AuthModel;
import com.financing.app.models.User;
import com.financing.app.models.UserModel;
import com.financing.app.service.UserService;
import jakarta.validation.Valid;

import java.util.Optional;

@RestController
public class AuthController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
//	@PostMapping("/login")
//	public ResponseEntity<HttpStatus> login(@RequestBody AuthModel authmodel){
//
//		Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authmodel.getEmail(), authmodel.getPassword()));
//
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
//
//	}
//
@PostMapping("/login")
public HttpEntity<Optional<User>> login(@RequestBody AuthModel authmodel) {
	try {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authmodel.getEmail(), authmodel.getPassword())
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		Optional<User> user = userService.findByEmail(authmodel.getEmail());

		return new ResponseEntity<>(user, HttpStatus.OK);
	} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
}

	@PostMapping("/register")
	public ResponseEntity<User> save(@Valid @RequestBody UserModel user){
		
		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
	}

}
