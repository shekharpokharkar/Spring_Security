package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.Exception.CustomAuthFailureHandler;
import com.example.demo.JWT.AuthEntryPointJwt;
import com.example.demo.JWT.AuthTokenFilter;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SeurityFilterConfiguration {

	@Autowired
	private CustomAuthFailureHandler customAuthFailureHandler;

	@Autowired
	private AuthEntryPointJwt authEntryPointJwt;

	@Bean
	public PasswordEncoder encoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public SecurityFilterChain setUpSecurityFilterchain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.csrf(csrf -> csrf.disable()).

				authorizeHttpRequests(Customizer ->

				Customizer
						.requestMatchers("/register", "/sigin", "/error", "/show-Welcome", "/WEB-INF/View/**",
								"/custom-login-postman", "/custom-error-page", "/custom-login-form")
						.permitAll().anyRequest().authenticated());

		httpSecurity.formLogin(
				Customizer -> Customizer.loginPage("/custom-login-form").loginProcessingUrl("/custom-login-processing")
						.successForwardUrl("/show-Welcome").failureHandler(customAuthFailureHandler)

		);
		httpSecurity.httpBasic(Customizer.withDefaults());
		httpSecurity.exceptionHandling(exception -> exception.authenticationEntryPoint(authEntryPointJwt) // ✅ Important
																											// for JWT
																											// 401
				.accessDeniedPage("/error"));
		httpSecurity.addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class); // ✅ insert before

		return httpSecurity.build();

	}

	@Bean
	public AuthTokenFilter authFilter() {
		return new AuthTokenFilter();
	}
	/*
	 * @Bean public UserDetailsManager customUserDetailManager() {
	 * MyCustomEmployeeUserDetailsManager manager = new
	 * MyCustomEmployeeUserDetailsManager(); return manager;
	 * 
	 * }
	 */

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

}
