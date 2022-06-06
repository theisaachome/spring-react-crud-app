package com.royal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 *@author Isaachome
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)// method level security
public class APISecurityConfig {
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	 @Bean
	 protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 http
		 	.csrf().disable()
		 	.authorizeHttpRequests((authorize)->{
		 		authorize.antMatchers("/api/auth/**").permitAll()
		 		.anyRequest()
		 		.authenticated();
		 	});
		 return http.build();
	 }

	
}
