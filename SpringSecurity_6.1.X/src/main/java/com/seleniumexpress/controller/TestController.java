package com.seleniumexpress.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	
	@GetMapping("/show")
	public String showHomePage() {
		return "Hello";
	}
	
	@GetMapping("/Hii")
	public String showHiiPage() {
		return "Hii";
	}
	
	@GetMapping("/bye")
	public String showByePage() {
		return "bye";
	}
}
