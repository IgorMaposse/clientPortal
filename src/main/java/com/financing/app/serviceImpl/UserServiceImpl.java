package com.financing.app.serviceImpl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

import com.financing.app.exceptions.ResourceNotFoundException;
import com.financing.app.models.User;
import com.financing.app.models.UserModel;
import com.financing.app.repository.UserRepository;
import com.financing.app.service.UserService;
import com.financing.app.Enum.Role;



@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public User createUser(UserModel uModel) {
		// TODO Auto-generated method stub
		User user= new User();
		BeanUtils.copyProperties(uModel, user);
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	@Override
	public List<User> userList() {
		return userRepository.findAll();
	}
	@Override
	public User readUser(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}

	@Override
	public User update(UserModel user, Long id) {
		// TODO Auto-generated method stub
		User existingUser= readUser(id);
		
		existingUser.setName(user.getName());
		existingUser.setAge(user.getAge());
		existingUser.setEmail(user.getEmail());
		existingUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		existingUser.setRole(user.getRole());
		//existingUser.setCreated_at(user.getCreated_at());
		//existingUser.setUpdated_at(user.getUpdated_at());
		
		userRepository.save(existingUser);
		return existingUser;
	
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		User user = readUser(id);
		userRepository.delete(user);
		
		
	}
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User getLoggedInUser() {
	    // Retrieve the authentication object from the security context
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
	        throw new UsernameNotFoundException("No authenticated user found.");
	    }

	    // Extract the email from the authentication object
	    String email = authentication.getName();

	    // Fetch the user from the database
	    return userRepository.findByEmail(email)
	            .orElseThrow(() -> new UsernameNotFoundException("User not found for the email: " + email));
	}

}
