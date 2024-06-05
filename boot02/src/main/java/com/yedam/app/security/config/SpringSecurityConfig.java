package com.yedam.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// 메모리상 인증정보 등록 => 테스트 전용 방식
	@Bean
	InMemoryUserDetailsManager InMemoryUserDetailsService() {
		UserDetails user = User.builder()
				.username("user1")
				.password(passwordEncoder().encode("1234"))
				.roles("USER") // ROLE_USER
				.authorities("ROLE_USER")
				.build();
		UserDetails admin = User.builder()
				.username("admin1")
				.password(passwordEncoder().encode("1234"))
//				.roles("USER") // ROLE_USER
				.authorities("ROLE_ADMIN", "ROLE_USER")
				.build();
		return new InMemoryUserDetailsManager(user, admin);
	}
	// 인증 및 인가 설정
	@Bean
	SecurityFilterChain filterChin(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
			.antMatchers("/", "/all").permitAll()
			.antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
			.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
			.anyRequest().authenticated()
			.and().formLogin()
				.defaultSuccessUrl("/all")
			.and().logout()
				.logoutSuccessUrl("/all");
		return http.build();
			
	}
}
