package com.codextiger.service;

import java.util.List;
import java.util.Optional;

import com.codextiger.model.User;

public interface UserService {

	public User register(User user); 
	
	public List<User> getAllUser();
	
	public Optional<User> signin(String email, String password);
	
    void sendOtp(String email);

    boolean verifyOtp(String email, String otp);

    void resetPassword(String email, String newPassword);

}
