package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityFilterSetUp {

	@Bean
	public SecurityFilterChain setUpSecurityFilterchain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity
		.authorizeHttpRequests(Customizer -> Customizer.anyRequest().authenticated())
		.oauth2Login(oauth2->oauth2.defaultSuccessUrl("/show"));
		
		
		
		// httpSecurity.formLogin(Customizer.withDefaults());
	
		return httpSecurity.build();
	}

}
