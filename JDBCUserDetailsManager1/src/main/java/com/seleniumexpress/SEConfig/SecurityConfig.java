package com.seleniumexpress.SEConfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

	@Autowired
	private HttpSecurity httpSecurity;

	@Bean
	public SecurityFilterChain setUpSecurityFilterChain() throws Exception {

		httpSecurity.authorizeHttpRequests(customizer -> {
			customizer.requestMatchers(AntPathRequestMatcher.antMatcher("/process-register"),
					AntPathRequestMatcher.antMatcher("/login"), AntPathRequestMatcher.antMatcher("/WEB-INF/view/**"),
					AntPathRequestMatcher.antMatcher("/Register")

			).permitAll().anyRequest().authenticated();

		});

		httpSecurity.formLogin(Customizer.withDefaults());
		httpSecurity.httpBasic(Customizer.withDefaults());
		httpSecurity.csrf(Customizer.withDefaults());
		return httpSecurity.build();
	}

	@Bean
	public JdbcUserDetailsManager userDetailsManager() {

		JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource());

		return userDetailsManager;

	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/spring_security?user=root");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		return dataSource;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
		return new HandlerMappingIntrospector();
	}

}
