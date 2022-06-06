package com.royal.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.royal.entity.Role;
import com.royal.entity.User;
import com.royal.repo.RoleRepo;
import com.royal.repo.UserRepo;

/**
 *
 * @author Isaachome
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {
	private UserRepo userRepo;
	private RoleRepo roleRepo;

	public CustomUserDetailsService(UserRepo userRepo, RoleRepo roleRepo) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsernameOrEmail(username, username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with  username or email" + username));
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthority(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthority(Set<Role> set) {
		return set.stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
