package com.codextiger.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codextiger.model.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Integer>{
 
//	User findByEmail(String email);
	Optional<User> findByEmail(String email);
//	Optional<User> findByEmailAndPassword(String email, String password);
	
	// Update OTP only
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.otp=:otp, u.otpExpiry=:expiry WHERE u.email=:email")
    void updateOtp(@Param("email") String email,
                   @Param("otp") String otp,
                   @Param("expiry") LocalDateTime expiry);
    
    // Update password only
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password=:password WHERE u.email=:email")
    int updatePassword(@Param("email") String email,
                        @Param("password") String password);
    
    // Clear OTP
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.otp=null, u.otpExpiry=null WHERE u.email=:email")
    void clearOtp(@Param("email") String email);
}
