package com.codextiger.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codextiger.dto.ForgotPWResponse;
import com.codextiger.dto.LoginResponse;
import com.codextiger.dto.PwResetRequest;
import com.codextiger.dto.Response;
import com.codextiger.dto.SignupRequest;
import com.codextiger.model.User;
import com.codextiger.service.UserService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/user")
//@CrossOrigin(origins = "*")
//@CrossOrigin(origins ="http://127.0.0.1:5500")

public class UserController {

	@Autowired
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	
//	 Signup feature to be participate on the web Application and get the access 
	@PostMapping("/signup")
	public ResponseEntity<Response> signup(@Valid @RequestBody SignupRequest request) {

		User user = new User();
	    user.setName(request.getName());
	    user.setEmail(request.getEmail());
	    user.setPassword(request.getPassword());
	    userService.register(user);

	    return ResponseEntity.ok(new Response("Success", null));
	}



	 
//	Login feature to get the access of some important features on the Web Application 
	
	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestBody User user) {
		Optional<User> optUser=userService.signin(user.getEmail(), user.getPassword());
		
		if(optUser.isPresent()) {
			User savedUser=optUser.get();
			LoginResponse lr =new LoginResponse(savedUser.getName(), savedUser.getEmail());
		
			Response response=new Response("Success", lr);
			return ResponseEntity.ok(response);
		}
		else {
			Response response=new Response("Invalid email or password", null);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}
		
	}
	
//	Get All Users to see how many users are registered 
	
	@GetMapping("/getAllUser")
	public ResponseEntity<List<User>> getAllUser() {
		return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
	}
	
	
	
//	Forgot Password 
	
	 // Send OTP
    @PostMapping("/send-otp")
    public ResponseEntity<ForgotPWResponse<?>> sendOtp(@RequestBody Map<String,String> body){

    	try {
    		 String email = body.get("email");

    	        userService.sendOtp(email);
    	        
    	        return ResponseEntity.ok(
                        new ForgotPWResponse<>("success","OTP sent successfully",null)
                );
    	}
    	catch(Exception e){
            return ResponseEntity.badRequest().body(
                    new ForgotPWResponse<>("error", e.getMessage(), null)
            );
    	}
        
    }

    // Verify OTP
    @PostMapping("/verify-otp")
    public ResponseEntity <ForgotPWResponse<Boolean>> verifyOtp(@RequestBody Map<String,String> body){

    	try {
    		
    		 String email = body.get("email");
    	        String otp = body.get("otp");

    	        
    	        boolean result = userService.verifyOtp(email,otp);
    	        
    	        if(!result){
                    return ResponseEntity.badRequest().body(
                            new ForgotPWResponse<>("error","Invalid OTP",false)
                    );
                }

                return ResponseEntity.ok(
                        new ForgotPWResponse<>("success","OTP verified",true)
                );
                
    	}catch(Exception e){
            return ResponseEntity.badRequest().body(
                    new ForgotPWResponse<>("error", e.getMessage(), false)
            );
        }
    }
    	
       
    

 // Reset Password
    @PostMapping("/reset-password")
    public ResponseEntity<ForgotPWResponse<?>> resetPassword( @Valid @RequestBody PwResetRequest resetRequest){

    	try {
    		String email = resetRequest.getEmail();
            String password = resetRequest.getPassword();
            
            if(email == null || password == null){
            	
                throw new RuntimeException("Invalid input");
            }

            userService.resetPassword(email,password);

            return ResponseEntity.ok(
                    new ForgotPWResponse<>("success","Password updated successfully",null)
            );

        }catch(Exception e){
            return ResponseEntity.badRequest().body(
                    new ForgotPWResponse<>("error", e.getMessage(), null)
            );
        }
    }
	
	
	
	
	
	
	
}
