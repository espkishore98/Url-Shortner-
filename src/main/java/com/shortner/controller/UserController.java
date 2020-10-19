package com.shortner.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shortner.domain.LoginRequest;
import com.shortner.domain.ResponseObject;
import com.shortner.domain.SignUpRequest;
import com.shortner.service.UserDetailsService;

@RestController
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserDetailsService userDetailsService;

	@PostMapping("/signup")
	public ResponseObject RegisterUser(@RequestBody SignUpRequest signUpRequest) {
		return userDetailsService.userRegister(signUpRequest);
	}

	@GetMapping("/login")
	public ResponseObject UserLogin(@RequestBody LoginRequest loginRequest) {
		return userDetailsService.userLogin(loginRequest);
	}

}
