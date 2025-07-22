package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/Hii")
	public String publicEndpoint2() {
		return "This is a public endpoint1 – no login required!";
	}

	@PostMapping("/show")
	public String publicEndpoint1() {
		return "This is a public endpoint2 – no login required!";
	}

	@PostMapping("/test")
	public String publicEndpoint3() {
		return "This is a public endpoint3 – no login required!";
	}

}
