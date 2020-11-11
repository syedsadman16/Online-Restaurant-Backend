package com.cs322.ors.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cs322.ors.model.User;
import com.cs322.ors.service.AccountStatusService;
import com.cs322.ors.db.UserRepository;

@Service
public class UserPrincipalService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountStatusService accountStatusService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		accountStatusService.updateStatus(user);

		UserPrincipal userPrincipal = new UserPrincipal(user);
		return userPrincipal;
	}

}
