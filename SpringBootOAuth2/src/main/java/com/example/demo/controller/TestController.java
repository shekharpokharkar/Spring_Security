package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	
	@GetMapping("/show")
	public ResponseEntity<String> showHomePage() {
		return new ResponseEntity<String>("Hello Good Morning", HttpStatus.OK);
	}

}
