package com.financing.app.service;

import java.util.Optional;

import java.util.List;

import com.financing.app.models.User;
import com.financing.app.models.UserModel;


public interface UserService {
	
	User createUser(UserModel user) ;
	
	User readUser(Long id);
	
	User update(UserModel user, Long id);
	
	void delete(Long id);
	
	User getLoggedInUser();
	Optional<User> findByEmail(String email);
	public List<User> userList();
}
