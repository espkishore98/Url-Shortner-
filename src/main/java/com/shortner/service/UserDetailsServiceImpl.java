package com.shortner.service;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jwt.SignedJWT;
import com.shortner.constants.ErrorMessages;
import com.shortner.constants.MessageConstants;
import com.shortner.domain.AuthUser;
import com.shortner.domain.LoginRequest;
import com.shortner.domain.ResponseObject;
import com.shortner.domain.SignUpRequest;
import com.shortner.domain.User;
import com.shortner.repository.UserRepository;
import com.shortner.security.JwtTokenProvider;
import com.shortner.utils.CommonUtil;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsService.class);

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtTokenProvider tokenProvider;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	public UserDetailsServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
			PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.tokenProvider = tokenProvider;
	}

	public boolean emailChecker(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (email == null) {
			return false;
		}
		return pat.matcher(email).matches();
	}

	@Override
	public ResponseObject userRegister(SignUpRequest signUpRequest) {

		System.currentTimeMillis();
		if (CommonUtil.isNull(signUpRequest.getUsername()) && CommonUtil.isNull(signUpRequest.getEmailId())
				&& CommonUtil.isNull(signUpRequest.getPassword())) {
			return new ResponseObject(null, ErrorMessages.DETAILS_REQUIRED, HttpStatus.BAD_REQUEST);
		}
		if (CommonUtil.isNull(signUpRequest.getUsername())) {
			return new ResponseObject(null, ErrorMessages.FIRST_NAME_REQUIRED, HttpStatus.BAD_REQUEST);
		}
		if (CommonUtil.isNull(signUpRequest.getEmailId())) {
			return new ResponseObject(null, ErrorMessages.EMAIL_REQUIRED, HttpStatus.BAD_REQUEST);
		}
		if (CommonUtil.isNull(signUpRequest.getPassword())) {
			return new ResponseObject(null, ErrorMessages.PASSWORD_REQUIRED, HttpStatus.BAD_REQUEST);
		}
		boolean checker = emailChecker(signUpRequest.getEmailId());
		if (checker == false) {
			return new ResponseObject(null, ErrorMessages.CHECK_EMAIL_FORMAT, HttpStatus.BAD_REQUEST);
		}

		LOGGER.info("UserDetailsService : input validations are done");
		// try catch block
		try {
			List<User> users = userRepository.findAll();
//			LOGGER.info("time taken till get Users:" + (System.currentTimeMillis() - startTime));
			for (User user : users) {
				if (user.getEmailId().toLowerCase().equals(signUpRequest.getEmailId().toLowerCase())) {
					return new ResponseObject(null, ErrorMessages.EMAIL_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
				}

			}

			// Create USer
			LOGGER.info("UserDetailsService : create new user initiated");
			User createUser = new User(UUID.randomUUID().toString(), signUpRequest.getUsername(),
					signUpRequest.getEmailId(), passwordEncoder.encode(signUpRequest.getPassword()));
			userRepository.saveAndFlush(createUser);
			LOGGER.info("UserDetailsService : new user details saved in db");

			return new ResponseObject("user Created Successfully", null, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return new ResponseObject(null, ErrorMessages.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);

	}

	public static String getUserExternalId(HttpServletRequest req) {

		String token = resolveToken(req);
		if (token == null) {
			return MessageConstants.UNAUTHORIZED;
		}
		SignedJWT signedJWT;
		ObjectMapper mapper = new ObjectMapper();
		try {
			signedJWT = SignedJWT.parse(token);
			String object = signedJWT.getPayload().toJSONObject().toJSONString();
			AuthUser user = mapper.readValue(object, AuthUser.class);
			if (System.currentTimeMillis() > user.getSub()) {
				return MessageConstants.UNAUTHORIZED;
			} else {
				return user.getJti();
			}
		} catch (ParseException | JsonProcessingException jpe) {
			LOGGER.error(jpe.getMessage(), jpe);

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		return MessageConstants.UNAUTHORIZED;
	}

	public static String getUserExternalId(String authToken) {

		// String token = resolveToken(authToken);
		String token = authToken;
		if (token == null) {
			return MessageConstants.UNAUTHORIZED;
		}
		SignedJWT signedJWT;
		ObjectMapper mapper = new ObjectMapper();
		try {
			signedJWT = SignedJWT.parse(token);
			String object = signedJWT.getPayload().toJSONObject().toJSONString();
			AuthUser user = mapper.readValue(object, AuthUser.class);
			if (System.currentTimeMillis() > user.getSub()) {
				return MessageConstants.UNAUTHORIZED;
			} else {
				return user.getJti();
			}
		} catch (ParseException | JsonProcessingException jpe) {
			LOGGER.info(jpe.getMessage() + " at " + Calendar.getInstance().getTime());
			LOGGER.error(jpe.getMessage(), jpe);

		} catch (Exception e) {
			LOGGER.info(e.getMessage() + " at " + Calendar.getInstance().getTime());
			LOGGER.error(e.getMessage(), e);
		}

		return MessageConstants.UNAUTHORIZED;
	}

	public static String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

	@Override
	public ResponseObject userLogin(LoginRequest login) {
		// TODO Auto-generated method stub

		User user = new User();
		if (CommonUtil.isNull(login.getEmailId()) && CommonUtil.isNull(login.getPassword())) {
			return new ResponseObject(null, ErrorMessages.LOGIN_DETAILS, HttpStatus.BAD_REQUEST);
		}
		LOGGER.info("UserServiceImpl :  userLogin initiated successfully");
		if ((CommonUtil.isNull(login.getEmailId()))) {
			return new ResponseObject(null, ErrorMessages.REGISTERED_EMAIL, HttpStatus.BAD_REQUEST);
		}
		if (CommonUtil.isNull(login.getPassword())) {
			return new ResponseObject(null, ErrorMessages.ENTER_PASSWORD, HttpStatus.BAD_REQUEST);
		}
		if (login.getEmailId().contains("@")) {
			user = userRepository.userLoginWithEmail(login.getEmailId().toLowerCase());
		}
		if (user == null) {
			return new ResponseObject(null, ErrorMessages.NOT_REGISTERED, HttpStatus.BAD_REQUEST);
		}

		if ((CommonUtil.isNull(login.getPassword())) || login.getPassword().length() == 0) {
			return new ResponseObject(null, ErrorMessages.ENTER_PASSWORD, HttpStatus.BAD_REQUEST);
		}
		if ((CommonUtil.isNull(user.getPassword())) || (user.getPassword().length() == 0)) {
			return new ResponseObject(null, ErrorMessages.PASSWORD_NOT_SET, HttpStatus.BAD_REQUEST);
		}
		if (!passwordEncoder.matches(login.getPassword(), user.getPassword())) {
			return new ResponseObject(null, ErrorMessages.INCORRECT_PASSWORD, HttpStatus.BAD_REQUEST);
		}

		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getExternalId(), login.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			String jwt = tokenProvider.generateToken(authentication, user);

			return new ResponseObject(new com.shortner.domain.jwt.JwtAuthenticationResponse(jwt, "Bearer",
					user.getEmailId(), user.getUserName(), user.getExternalId()), null, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseObject(null, "Please check your credentials", HttpStatus.BAD_REQUEST);
		}
	}

}
