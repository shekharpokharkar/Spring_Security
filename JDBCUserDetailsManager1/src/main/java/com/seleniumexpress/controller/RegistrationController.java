package com.seleniumexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seleniumexpress.DTO.RegistrationDTO;

@Controller
public class RegistrationController {
	
	@Autowired
	@Lazy
	private JdbcUserDetailsManager userDetailsManager;
	
	@GetMapping(value = "/Register")
	public String showRegistrationPage(@ModelAttribute("REG")RegistrationDTO registration)
	{
		return "Register";
	}

	@ResponseBody
	@PostMapping(value= "/process-register")
	public String processRegistrationPage(RegistrationDTO registration)
	{
		System.out.println(registration);
		
		UserDetails userDetails = User.withUsername(registration.getUsername()).password(registration.getPassword()).roles("Admin","User").build();
		
		userDetailsManager.createUser(userDetails);
		
		return "Successfully Register";
	}
}
