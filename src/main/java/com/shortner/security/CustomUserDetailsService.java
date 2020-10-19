package com.shortner.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shortner.domain.User;
import com.shortner.repository.UserRepository;

import javassist.NotFoundException;

@Service("UserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String externalId) {

		User user = userRepository.getUserByExternalId(externalId);
		if (user == null) {
			return (UserDetails) new NotFoundException("User not found [id: " + externalId + "]");
		}

		return UserPrincipal.create(user);
	}

	@Transactional
	public UserDetails loadUserById(Long id) throws NotFoundException {

		User user = userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("User not found [id: " + id + "]"));

		return UserPrincipal.create(user);
	}

}
