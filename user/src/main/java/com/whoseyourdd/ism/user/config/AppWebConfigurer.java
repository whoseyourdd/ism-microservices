package com.whoseyourdd.ism.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.whoseyourdd.ism.user.service.impl.UserDetailServiceImpl;

import jakarta.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class AppWebConfigurer {

    @Resource
    UserDetailServiceImpl service;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(authorize -> authorize
					// .requestMatchers("/api/public/users/**").authenticated()
					.requestMatchers(HttpMethod.PUT, "/api/public/users/**").permitAll()
					.requestMatchers(HttpMethod.DELETE, "/api/public/users/**").permitAll()
					.anyRequest().permitAll()
				)
				// .formLogin(form -> form
				// 		.loginPage("/login")
				// 		.permitAll()
				// )
				.httpBasic(httpBasic -> {})
				.authenticationProvider(authProvider());
			return http.build();
    }
	
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(service);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(authProvider());
    }
}

