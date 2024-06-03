package com.example.demo.leavetracker.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.leavetracker.security.EmployeeDetailService;
import com.example.demo.leavetracker.security.JWTUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JWTAuthFilter extends  OncePerRequestFilter {
	
	@Autowired
	private JWTUtils jwtUtils;
	
	
	@Autowired	
	private EmployeeDetailService employeeDetailService;

   //This method is overridden to implement custom filtering logic. 
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authHeader =request.getHeader("Authorization");// contains JWT Token
		final String jwtToken;
		final String employeeEmail;
		
		if (authHeader==null|| authHeader.isBlank()){
			filterChain.doFilter(request,response);
			return;
		}	
		jwtToken =authHeader.substring(7);
		employeeEmail=jwtUtils.extractUsername(jwtToken);
		
		if (employeeEmail !=null && SecurityContextHolder.getContext().getAuthentication()==null){
			UserDetails userDetails=employeeDetailService.loadUserByUsername(employeeEmail);
			
			
			if (jwtUtils.isTokenValid(jwtToken, userDetails)) {
				SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
				UsernamePasswordAuthenticationToken token= new UsernamePasswordAuthenticationToken(userDetails, null,
						userDetails.getAuthorities()
						);
				token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				securityContext.setAuthentication(token);
				SecurityContextHolder.setContext(securityContext);
				}
		}
		filterChain.doFilter(request, response);
	}

}
