package com.shortner.service;

import org.springframework.stereotype.Service;

import com.shortner.domain.LoginRequest;
import com.shortner.domain.ResponseObject;
import com.shortner.domain.SignUpRequest;

@Service
public interface UserDetailsService {

	public ResponseObject userRegister(SignUpRequest signUpRequest);

	public ResponseObject userLogin(LoginRequest login);

}
