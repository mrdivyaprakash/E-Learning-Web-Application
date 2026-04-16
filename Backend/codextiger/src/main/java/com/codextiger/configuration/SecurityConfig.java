package com.codextiger.configuration;

import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
public class SecurityConfig {

	 
	 
	 // 🔥 CORS CONFIG HERE
	    @Bean
	    public CorsConfigurationSource corsConfigurationSource() {

	        CorsConfiguration config = new CorsConfiguration();

	        config.setAllowedOrigins(List.of("http://127.0.0.1:5500"));
	        config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
	        config.setAllowedHeaders(List.of("*"));
	        config.setAllowCredentials(true);

	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", config);

	        return source;
	    }
	 
	 
//	  PasswordEncoder
	@Bean
	public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
	
//	
	 @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

	        http
	            .csrf(csrf -> csrf.disable()) // for APIs
	            .cors(cors ->  cors.configurationSource(corsConfigurationSource()))
	            
	                 
// enable CORS
	            
	            .authorizeHttpRequests(auth -> auth
//	            		.requestMatchers("/**").permitAll()
	                .requestMatchers(
	                		// public APIs
	                    "/user/signup",
	                    "/user/getAllUser",
	                    "/user/login",
	                    "/user/send-otp",
	                    "/user/verify-otp",
	                    "/user/reset-password",
	                    "/api/tutorial/{slug}",
	                    "/api/topics/{tutorialId}",
	                    "/api/topic/{id}",
	                    "/api/quiz/topic/{topicId}",
	                    "/api/admin/topic/{tutorialId}"
	                    
	                   
	                ).permitAll() 

	                .anyRequest().authenticated() // everything else protected
	            );

	        return http.build();
	    }
	 
	 
//	 Rate Limit
	 
	 @Bean
	 public FilterRegistrationBean<RateLimitFilter> rateLimitFilter() {

	     FilterRegistrationBean<RateLimitFilter> registrationBean = new FilterRegistrationBean<>();

	     registrationBean.setFilter(new RateLimitFilter());

	     registrationBean.addUrlPatterns(
	             "/user/login",
	             "/user/signup",
	             "/user/send-otp"
	     );

	     return registrationBean;
	 }
	 
	// Author Divya Prakash Kumar
	//Developed By Divya Prakash KUmar
	 
}
