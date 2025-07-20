package com.seleniumExpress.SEConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

	@Autowired
	private HttpSecurity httpSecurity;

	@Bean
	public SecurityFilterChain setUpSecurityFilterChain() throws Exception {

		httpSecurity.authorizeHttpRequests(customizer -> {
			customizer.anyRequest().authenticated();

		});

		httpSecurity.formLogin(Customizer.withDefaults());
		httpSecurity.httpBasic(Customizer.withDefaults());
		return httpSecurity.build();
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {

		UserDetails userDetails = User.withUsername("shekhar").password("shekhar123").roles("admin").build();

		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
		userDetailsManager.createUser(userDetails);
		return userDetailsManager;

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
