package com.codextiger.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codextiger.exception.EmailAlreadyExistsException;
import com.codextiger.model.User;
import com.codextiger.repository.UserRepository;
import com.codextiger.service.EmailService;
import com.codextiger.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private final UserRepository userRepository;
	
	@Autowired
	private EmailService emailService;
	
//	/newlly added for password security
	@Autowired
	private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;
	
//	=============
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	@Override
	public User register(User user) {

		Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

	    if (existingUser.isPresent()) {
	    	throw new EmailAlreadyExistsException("Email already registered");
	    }
		
//		for password security
		String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);
	    
//	    ==============

		return userRepository.save(user);
	}


	@Override
	public List<User> getAllUser() {
		 return userRepository.findAll();
	}


	@Override
	public Optional<User> signin(String email, String password) {
//		before this was
//	return	userRepository.findByEmailAndPassword(email, password);
		
		
//		newlly added for password security
		Optional<User> userOpt = userRepository.findByEmail(email);

	    if(userOpt.isPresent()){
	        User user = userOpt.get();

	        if(passwordEncoder.matches(password, user.getPassword())){
	            return Optional.of(user);
	        }
	    }
	    

	    return Optional.empty();
//	    =================
	}


	@Override
	public void sendOtp(String email) {
		
		 userRepository.findByEmail(email)
         .orElseThrow(() -> new RuntimeException("User not found"));

 String otp = String.valueOf(new Random().nextInt(900000) + 100000);

 LocalDateTime expiry = LocalDateTime.now().plusMinutes(5);

 userRepository.updateOtp(email, otp, expiry);

 // send email
 emailService.sendOtpEmail(email, otp);
	}


	@Override
	public boolean verifyOtp(String email, String otp) {
		User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(user.getOtp()==null)
            return false;

        if(user.getOtp().equals(otp)
                && user.getOtpExpiry().isAfter(LocalDateTime.now())){

        	
        	userRepository.clearOtp(email);
            return true;
        }

        return false;
	}


	@Transactional
	@Override
	public void resetPassword(String email, String password) {
		
//		newlly added for password security
		String encodedPassword = passwordEncoder.encode(password);
//		============
		 int updatedRows = userRepository.updatePassword(email, encodedPassword);
		 
		 if(updatedRows == 0){
		        throw new RuntimeException("User not found or password not updated");
		    }

		
        userRepository.clearOtp(email);
	}

}
