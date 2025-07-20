package com.seleniumexpress.securityconfiguration;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import static org.springframework.util.AntPathMatcher.*;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

/*
 * it triggers Spring to look for beans like SecurityFilterChain 
 * or (older style) classes that extend WebSecurityConfigurerAdapter.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private HttpSecurity httpSecurity;

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
	public SecurityFilterChain setUpSecurityFilterChain() throws Exception {

		
		httpSecurity
		.authorizeHttpRequests().requestMatchers(AntPathRequestMatcher.antMatcher("/Hii"),AntPathRequestMatcher.antMatcher("/hello1")).permitAll()
		.requestMatchers(AntPathRequestMatcher.antMatcher("/show")).denyAll()
		.anyRequest().authenticated();

		httpSecurity.formLogin().and().httpBasic();
		return httpSecurity.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean(name = "mvcHandlerMappingIntrospector")
	public HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
		return new HandlerMappingIntrospector();
	}

}
