package com.cap.exs.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cap.exs.entities.LoginDetails;
import com.cap.exs.repos.ILoginRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	ILoginRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LoginDetails user = userRepository.findByUserName(username);
			
		if(user == null)
		throw new UsernameNotFoundException("User Not Found with username: " + username);

		return UserDetailsImpl.build(user);
	}

}
