
package com.web.dssapp.config;

import com.web.dssapp.model.*;
import com.web.dssapp.repository.RoleRepository;
import com.web.dssapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
    private RoleRepository roleRepository;

    public CustomUserDetailsService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findUserByusername(name);

        if (user != null) {
        	Collection<? extends GrantedAuthority> authorities = mapRolesToAuthorities(roleRepository.findById(user.getRole_id()).get());
            return new CustomUserDetails(user, authorities); // pass roleRepository to constructor
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Role role) {
	    Collection<GrantedAuthority> authorities = new ArrayList<>();
	    authorities.add(new SimpleGrantedAuthority(role.getName()));
	    return authorities;
	}
}
