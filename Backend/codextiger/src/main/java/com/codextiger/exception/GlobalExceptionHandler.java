//package com.codextiger.exception;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import com.codextiger.dto.SignupResponse;

//



package com.codextiger.exception;

	import java.util.HashMap;
	import java.util.Map;

	import org.springframework.dao.DataAccessException;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.MethodArgumentNotValidException;
	import org.springframework.web.bind.annotation.ExceptionHandler;
	import org.springframework.web.bind.annotation.RestControllerAdvice;

	import com.codextiger.dto.ErrorResponse;




@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	
	
	

	

	    // Validation Errors
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String,String>> handleValidation(MethodArgumentNotValidException ex){

	        Map<String,String> errors = new HashMap<>();

	        ex.getBindingResult().getFieldErrors().forEach(error -> {
	            errors.putIfAbsent(error.getField(), error.getDefaultMessage());
	        });

	        return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body(errors);
	    }

	    // Email already exists
	    @ExceptionHandler(EmailAlreadyExistsException.class)
	    public ResponseEntity<ErrorResponse> handleEmailExists(EmailAlreadyExistsException e){

	        ErrorResponse error = new ErrorResponse(
	                HttpStatus.BAD_REQUEST.value(),
	                "Bad Request",
	                e.getMessage()
	        );

	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	    }

	    // Illegal argument
	    @ExceptionHandler(IllegalArgumentException.class)
	    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException e){

	        ErrorResponse error = new ErrorResponse(
	                HttpStatus.BAD_REQUEST.value(),
	                "Bad Request",
	                e.getMessage()
	        );

	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	    }

	    // Database error
	    @ExceptionHandler(DataAccessException.class)
	    public ResponseEntity<ErrorResponse> handleDatabaseError(DataAccessException e){

	        ErrorResponse error = new ErrorResponse(
	                HttpStatus.INTERNAL_SERVER_ERROR.value(),
	                "Database Error",
	                "Database operation failed"
	        );

	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	    }

	    // Resource not found
	    @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException e){

	        ErrorResponse error = new ErrorResponse(
	                HttpStatus.NOT_FOUND.value(),
	                "Not Found",
	                e.getMessage()
	        );

	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	    }

	    // Catch all exceptions (500)
	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception e){

	        ErrorResponse error = new ErrorResponse(
	                HttpStatus.INTERNAL_SERVER_ERROR.value(),
	                "Internal Server Error",
	                "Something went wrong. Please try again later."
	        );

	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
//
//        Map<String, String> errors = new HashMap<>();
//
//        ex.getBindingResult().getFieldErrors().forEach(error -> {
//
//            // THIS LINE PREVENTS RANDOM MESSAGE OVERRIDE
//            errors.putIfAbsent(error.getField(), error.getDefaultMessage());
//
//        });
//
//        return ResponseEntity.badRequest().body(errors);
//    }
//    
//    @ExceptionHandler(EmailAlreadyExistsException.class)
//    public ResponseEntity<SignupResponse> handleEmailExists(EmailAlreadyExistsException e) {
//
//    	SignupResponse response = new SignupResponse("error", e.getMessage());
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//    }
}
