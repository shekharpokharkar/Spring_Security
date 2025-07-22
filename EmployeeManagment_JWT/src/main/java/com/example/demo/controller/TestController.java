package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Secured("ROLE_Admin")
	@GetMapping("/show")
	public ResponseEntity<String> showHello(Authentication authentication) {
		return new ResponseEntity<>("Hello Good Morning => Hello" + authentication.getName(), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('USER')")
	@PostMapping("/bye")
	public ResponseEntity<String> sayBye(Authentication authentication) {
		return new ResponseEntity<>("Hello Good Afternoon =>Bye" + authentication.getName(), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('USER','trainer','Admin')")
	@DeleteMapping("/nice")
	public ResponseEntity<String> showNice(Authentication authentication) {
		return new ResponseEntity<>("Hello Good Evening => Nice to meet you" + authentication.getName(), HttpStatus.OK);
	}

}
