package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityFilterChain {

	@Bean
	public DefaultSecurityFilterChain setUpSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity
		.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(customizer -> {

			customizer.requestMatchers("/register","/studentLogin/").permitAll().anyRequest().authenticated();

		});
		httpSecurity.formLogin(Customizer.withDefaults());
		httpSecurity.httpBasic(Customizer.withDefaults());

		return httpSecurity.build();
	}

	@Bean
	public MyCustomUserDetailsManager myCustomUserDetailsManager() {
		return new MyCustomUserDetailsManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
