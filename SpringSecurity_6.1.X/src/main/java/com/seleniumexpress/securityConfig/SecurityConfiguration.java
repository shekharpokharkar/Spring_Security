package com.seleniumexpress.securityConfig;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
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
import org.springframework.util.AntPathMatcher;

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

		httpSecurity.authorizeHttpRequests(customizer -> {

			customizer.requestMatchers(AntPathRequestMatcher.antMatcher("/Hii")).permitAll();
			customizer.anyRequest().authenticated();
		});

		httpSecurity.formLogin(Customizer.withDefaults());
		httpSecurity.httpBasic(Customizer.withDefaults());
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
