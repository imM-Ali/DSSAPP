package com.web.dssapp.config;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.web.dssapp.model.User;


public class CustomUserDetails implements UserDetails {
	
	
	private User user;
	Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(User user,Collection<? extends GrantedAuthority> authorities) {
		super();
		this.user = user;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
       
	
	
	public User getContext() {
	return this.user;
	}

	@Override
	public String getPassword() {
		
	return user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
