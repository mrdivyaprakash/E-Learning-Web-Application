package com.codextiger.model;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="users")
public class User {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

// @NotBlank(message = " Name cannot be empty")
// 
//// Name will take only alphabets and space (no numbers, no special characters)
// @Pattern(regexp = "^$|[A-Za-z ]+$", message = "Name must contain only alphabets",
//		 flags = Pattern.Flag.CASE_INSENSITIVE
// )
 
    private String name;
 
//    @Column(unique = true, nullable = false)
//    @NotBlank(message = "Email cannot be empty")
//    @Pattern(
//    	    regexp = "^$|[a-z0-9]+([._%+-][a-z0-9]+)*@[a-z0-9-]+(\\.[a-z]{2,})+$",
//    	    message = "Invalid email format",
//    	    flags = Pattern.Flag.CASE_INSENSITIVE
//    	)
    private String email;

//    @NotBlank(message = "Password cannot be empty")
//    @Pattern(
//        regexp = "^$|[A-Z](?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&*!]).{7,}$",
//        message = "Password must start with uppercase letter and contain letters, numbers, special characters (min 8 chars)")
    private String password;
    
    private String otp;
    private LocalDateTime otpExpiry;
    
 // getters & setters

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
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
	
	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public LocalDateTime getOtpExpiry() {
		return otpExpiry;
	}

	public void setOtpExpiry(LocalDateTime otpExpiry) {
		this.otpExpiry = otpExpiry;
	}
	

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
    
	
    
}
