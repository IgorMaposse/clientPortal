package com.financing.app.models;


import com.financing.app.Enum.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserModel {
	
	@NotBlank(message="Please enter name")
	private String name;
	
	@jakarta.validation.constraints.NotNull(message="Please Enter Email")
	@Email(message="please enter a valid email")
	private String email;
	
	@jakarta.validation.constraints.NotNull(message="Please enter the password")
	@Size(min=5, message="Password Should be at least 5 characters")
	 private String password;
	
	 private Long age=0L;
	 @lombok.Getter
	    @Enumerated(EnumType.STRING)
	private Role role;

	public String getName() {
		return name;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "UserModel [name=" + name + ", email=" + email + ", password=" + password + ", age=" + age +", role=" + role +"]";
	}



}
