package com.codextiger.configuration;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class RateLimitFilter implements Filter {



		

		    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

		    private Bucket createBucket() {

		    	Bandwidth limit = Bandwidth.builder()
		    	        .capacity(5)
		    	        .refillIntervally(3, Duration.ofMinutes(1))
		    	        .build();

		        return Bucket.builder()
		                .addLimit(limit)
		                .build();
		    }

		    private Bucket resolveBucket(String ip) {

		        return cache.computeIfAbsent(ip, k -> createBucket());
		    }

		    @Override
		    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		            throws IOException, ServletException {

		        HttpServletRequest httpRequest = (HttpServletRequest) request;
		        HttpServletResponse httpResponse = (HttpServletResponse) response;

		        String ip = httpRequest.getHeader("X-Forwarded-For");
		        if (ip == null) {
		            ip = httpRequest.getRemoteAddr();
		        }

		        Bucket bucket = resolveBucket(ip);

		        if (bucket.tryConsume(1)) {

		            chain.doFilter(request, response);

		        } else {
		        	httpResponse.setStatus(429);
		            httpResponse.setContentType("application/json");

		            httpResponse.getWriter().write("""
		            {
		                "status":"error",
		                "message":"Too many requests. Please try again later."
		            }
		            """);
		            return;
		        }
		    }
		}

//Author Divya Prakash Kumar
//Developed By Divya Prakash KUmar

	

