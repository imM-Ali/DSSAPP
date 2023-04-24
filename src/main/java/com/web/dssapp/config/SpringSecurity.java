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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
     static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    AuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    	provider.setUserDetailsService(userDetailsService);
    	provider.setPasswordEncoder(new BCryptPasswordEncoder());
    	return provider;
    }
    
     
	  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	  http.csrf().disable() 
	  .authorizeHttpRequests((request) ->
	  request.requestMatchers("/register/**").permitAll()
	  .requestMatchers("/").permitAll()
	  .requestMatchers("/users").hasRole("ADMIN")
	  .requestMatchers("/moviespage").hasRole("USER")
			  ).formLogin( form -> form
	  .loginPage("/login") .loginProcessingUrl("/login") .defaultSuccessUrl("/movies")
	  .permitAll() ).logout( logout -> logout .logoutRequestMatcher(new
	  AntPathRequestMatcher("/logout")) .permitAll() ); return http.build(); }
	 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
