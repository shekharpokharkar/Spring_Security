package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.LoginRequest;
import com.example.demo.DTO.LoginResponse;
import com.example.demo.JWT.JwtUtils;

@RestController
public class GeneratingSignUp {

	@Autowired
	private JwtUtils utils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/sigin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest request) {
		Authentication auth = null;
		if (request.getUserName() == null || request.getPassword() == null) {
			return ResponseEntity.badRequest().body(Map.of("error", "Username or Password is missing"));
		}
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getUserName(),
					request.getPassword());
			auth = authenticationManager.authenticate(token);

		} catch (AuthenticationException exception) {
			Map<String, Object> map = new HashMap<>();
			map.put("message", "Bad Credentials");
			map.put("status", false);
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}

		SecurityContextHolder.getContext().setAuthentication(auth);
		UserDetails details = (UserDetails) auth.getPrincipal();
		String token = utils.genrateTokenFromUserName(details);
		List<String> collect = details.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		LoginResponse loginResponse = LoginResponse.builder().userName(details.getUsername()).token(token)
				.roles(collect).build();
		return new ResponseEntity<>(loginResponse, HttpStatus.OK);

	}
}
