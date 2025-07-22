package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Employee;
import com.example.demo.entity.LoginDTO;
import com.example.demo.entity.MyEmployeeUserDetails;
import com.example.demo.security.MyCustomEmployeeUserDetailsManager;

@Controller
public class RegistrationController {

	@Autowired
	private MyCustomEmployeeUserDetailsManager manager;

	@ResponseBody
	@PostMapping("/register")
	public String registerEmployee(@RequestBody Employee employee) {
		MyEmployeeUserDetails user = new MyEmployeeUserDetails(employee);
		manager.createUser(user);
		return "Employee Saved successfully";
	}

	@ResponseBody
	@PostMapping("/custom-login-postman")
	public String registerEmployee(@RequestBody LoginDTO login) {

		System.out.println("Inside This custom login page");
		System.out.println(login);
		manager.loadUserByUsername(login.getUserName());
		return "Employee login successfully";
	}
	
	@GetMapping("/custom-login-form")
	public String showLoginPage()
	{
		return "login";
	}
	
	@PostMapping("/custom-error-page")
	public String showExceptionHandlingPage()
	{
		return "Exception";
	}
	

	@ResponseBody
	@PostMapping("/show-Welcome")
	public String showWelcomePage()
	{
		return "You Are Successfully Login";
	}
	
	
	
}
