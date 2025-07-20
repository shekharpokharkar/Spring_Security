package com.seleniumexpress.securityconfiguration;

import java.util.ArrayList;

import org.apache.commons.logging.impl.NoOpLog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

/*
 * it triggers Spring to look for beans like SecurityFilterChain 
 * or (older style) classes that extend WebSecurityConfigurerAdapter.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	public UserDetailsManager setUpUser() {
		SimpleGrantedAuthority user = new SimpleGrantedAuthority("User");
		SimpleGrantedAuthority admin = new SimpleGrantedAuthority("Admin");
		SimpleGrantedAuthority trainer = new SimpleGrantedAuthority("Trainer");

		ArrayList<GrantedAuthority> role = new ArrayList<>();
		role.add(trainer);
		role.add(user);
		role.add(admin);

		UserDetails userDetails = new User("shekhar", "shekhar123", role);

		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(userDetails);
		return manager;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}

}
