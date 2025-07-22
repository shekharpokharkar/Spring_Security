package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginDTO;
import com.example.demo.security.MyCustomUserDetailsManager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/studentLogin")
public class LoginController {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private MyCustomUserDetailsManager manager;

	@PostMapping("/")
	public String login(@RequestBody LoginDTO dto) {

		UserDetails userByUsername = manager.loadUserByUsername(dto.getStudentUserName());

		if (encoder.matches(dto.getStudentPassword(), userByUsername.getPassword())) {

			Authentication authentication = new UsernamePasswordAuthenticationToken(userByUsername.getUsername(), null,
					userByUsername.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authentication);

			return "You are successfully login";
		} else {
			return "wrong credntials";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {

		// Invalidate session
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		// Clear authentication
		SecurityContextHolder.clearContext();

		return "✅ You are successfully logged out!";
	}

}
