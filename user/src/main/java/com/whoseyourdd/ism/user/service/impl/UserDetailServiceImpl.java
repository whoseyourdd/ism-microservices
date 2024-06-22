package com.whoseyourdd.ism.user.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.whoseyourdd.ism.user.model.User;
import com.whoseyourdd.ism.user.repository.UserRepository;


/**
 * UserServiceImpl
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = repo.findByUsername(username);
		if(!user.isPresent()){
			throw new UsernameNotFoundException("User not found");
		}
		List<SimpleGrantedAuthority> authorities = Arrays.
			asList(new SimpleGrantedAuthority(user.get().getRole().name()));
		return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), authorities);

	}
}
