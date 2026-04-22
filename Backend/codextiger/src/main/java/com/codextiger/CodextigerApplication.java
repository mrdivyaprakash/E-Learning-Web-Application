package com.codextiger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// Author Divya Prakash Kumar
//Developed By Divya Prakash KUmar
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CodextigerApplication {

	public static void main(String[] args) { 
		SpringApplication.run(CodextigerApplication.class, args);
	}

}
