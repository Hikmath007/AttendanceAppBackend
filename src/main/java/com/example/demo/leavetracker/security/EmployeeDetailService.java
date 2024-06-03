package com.example.demo.leavetracker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.leavetracker.exceptions.EmployeeNotFoundException;
import com.example.demo.leavetracker.repository.EmployeeRepo;


@Service
public class EmployeeDetailService implements UserDetailsService {
	
	@Autowired
	 private EmployeeRepo employeeRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return employeeRepo.findByEmail(username).orElseThrow(()->new EmployeeNotFoundException("EmployeeEntity","email:",username));

	}
	

}
