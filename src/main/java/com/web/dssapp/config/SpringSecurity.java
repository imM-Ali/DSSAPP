package com.web.dssapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;


@Configuration
@EnableWebSecurity
public class SpringSecurity {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}

	 @Bean
	
	    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		 
		 
	        http.authorizeHttpRequests((authz) -> {
	            try {
                    authz.requestMatchers("/signup", "/login", "/resources/**").permitAll()
                            .requestMatchers("/roles").hasAnyAuthority("admin")
                            .requestMatchers("users/**").hasAnyAuthority("admin", "Manager")
                            .requestMatchers("/editmovie/**").hasAnyAuthority("admin", "Manager","editor")
                            .requestMatchers("/deletemovie/**").hasAuthority("admin")
                            .requestMatchers("/edituser/**").hasAnyAuthority("admin", "Manager")                            
                            .anyRequest().authenticated()
                            .and().exceptionHandling(handling -> handling.accessDeniedPage("/noaccess"))
                            .formLogin(login -> login
                                    .loginPage("/login")
                                    .defaultSuccessUrl("/movies/1", true))
                            .logout(logout -> logout
                                    .invalidateHttpSession(true))
                            .httpBasic(withDefaults());
	            } catch (Exception e) {
	                System.out.println(e.getMessage());
	            }
	        });

	        return http.build();
	    }	
	 
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {		
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
}
