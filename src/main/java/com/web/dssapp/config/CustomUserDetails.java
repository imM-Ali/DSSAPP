package com.web.dssapp.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.web.dssapp.model.Role;
import com.web.dssapp.model.User;
import com.web.dssapp.repository.RoleRepository;


public class CustomUserDetails implements UserDetails {
	
	
	private User user;
	private RoleRepository rs;
	
	public CustomUserDetails(User user) {
		super();
		this.user = user;
	}
	
	public CustomUserDetails(User user, RoleRepository rs) {
		super();
		this.user = user;
		this.rs = rs;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		
		Optional<Role> a = rs.findById(user.getRole_id());
		
		
		//	list.add(new SimpleGrantedAuthority(rs.findById(user.getRole_id()).get()));
			 return list;
		}
       
	
	
	public User getContext() {
	return this.user;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
