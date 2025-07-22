package com.example.demo.Exception;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		// Attach custom error message
		if (exception instanceof BadCredentialsException) {
			request.setAttribute("error", "Invalid username or password");
		} else {
			request.setAttribute("error", "Login failed: " + exception.getMessage());
		}

		// Forward to JSP-based error page
		request.getRequestDispatcher("/custom-error-page").forward(request, response);
	}
}
