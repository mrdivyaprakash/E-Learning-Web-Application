package com.codextiger.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class PwResetRequest {

	  @Column(unique = true, nullable = false)
	    @NotBlank(message = "Email cannot be empty")
	    @Pattern(
	    	    regexp = "^$|[a-z0-9]+([._%+-][a-z0-9]+)*@[a-z0-9-]+(\\.[a-z]{2,})+$",
	    	    message = "Invalid email format",
	    	    flags = Pattern.Flag.CASE_INSENSITIVE
	    	)
	private String email; 
	  
	  
	  @NotBlank(message = "Password cannot be empty")
	    @Pattern(
	        regexp = "^$|[A-Z](?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&*!]).{7,}$",
	        message = "Password must start with uppercase letter and contain letters, numbers, special characters (min 8 chars)")
	    private String password;	
	
	public PwResetRequest(String email, String password) {
		this.email = email;
		this.password = password;
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
	
	
}
