package com.shortner.service;

import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shortner.domain.AllUrlsResponse;
import com.shortner.domain.ResponseObject;
import com.shortner.domain.Shorter;
import com.shortner.domain.UpdateRequest;
import com.shortner.domain.User;
import com.shortner.domain.UserSpecificShort;
import com.shortner.domain.UpdateRequest;
import com.shortner.repository.ShorterRepository;
import com.shortner.repository.UserRepository;

@Service
public class ShortnerServiceImpl implements ShortnerService {
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ShorterRepository shortRepo;

	@Override
	public ResponseObject generateRandomString(String LongUrl, String auth) {
		String authToken2 = auth.substring(7);
		String externalId2 = UserDetailsServiceImpl.getUserExternalId(authToken2);
		User user = userRepository.getUserByExternalId(externalId2);
		String shortName=shortRepo.urlCheck(user.getId(),LongUrl)!=null?shortRepo.urlCheck(user.getId(),LongUrl).getShortname():null;
		if(shortName!=null) {
			URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/" + shortName).buildAndExpand()
					.toUri();
			return new ResponseObject(location, null, HttpStatus.OK);

		}
		else {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 5;
		Random random = new Random();
		String randomString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		Shorter Short = new Shorter(UUID.randomUUID().toString(), LongUrl, randomString, user);

		shortRepo.saveAndFlush(Short);
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/" + randomString).buildAndExpand()
				.toUri();

		return new ResponseObject(location, null, HttpStatus.OK);
		}
		}

	@Override
	public ResponseObject userSpecificName(UserSpecificShort specificShort, String auth) {
		String authToken2 = auth.substring(7);
		String externalId2 = userDetailsServiceImpl.getUserExternalId(authToken2);
		User user = userRepository.getUserByExternalId(externalId2);
		Shorter shortUrlDetails=shortRepo.urlCheck(user.getId(),specificShort.getLongUrl())!=null?shortRepo.urlCheck(user.getId(),specificShort.getLongUrl()):null;
		Shorter Short=new Shorter();
		Shorter ShortDetails= shortRepo.shortUrlCheck(user.getId(),specificShort.getShortName())!=null?shortRepo.shortUrlCheck(user.getId(),specificShort.getShortName()):null;
		if(ShortDetails==null) {
		if(shortUrlDetails!=null) {
			shortUrlDetails.setId(shortUrlDetails.getId());
			shortUrlDetails.setShortname(specificShort.getShortName());
			shortRepo.saveAndFlush(shortUrlDetails);
			URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/" + specificShort.getShortName()).buildAndExpand()
					.toUri();
			return new ResponseObject(location, null, HttpStatus.OK);
		}else {
			Short = new Shorter(UUID.randomUUID().toString(), specificShort.getLongUrl(),
					specificShort.getShortName(), user);
			shortRepo.saveAndFlush(Short);

			URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/" + specificShort.getShortName())
					.buildAndExpand().toUri();

			return new ResponseObject(location, null, HttpStatus.OK);
		}}
		else {
			return new ResponseObject("Please specify different Name",null,HttpStatus.BAD_REQUEST);
		}
	}
	@Override
	public ResponseObject getAllUrlDetails(@RequestHeader("Authorization") String auth) {
		String authToken2 = auth.substring(7);
		String externalId2 = UserDetailsServiceImpl.getUserExternalId(authToken2);
		User user = userRepository.getUserByExternalId(externalId2);
		Long userId=user.getId();
		List<Shorter> urlDetails = shortRepo.getUrlsById(userId);
		List<AllUrlsResponse> listOfResponses=new ArrayList<>();
		if(!urlDetails.isEmpty()) {
			for(Shorter urlDetail:urlDetails) {
				AllUrlsResponse response= new AllUrlsResponse();
				response.setUrlId(urlDetail.getId());
				response.setUserId(urlDetail.getUser().getId());
				response.setShortUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/" + urlDetail.getShortname()).buildAndExpand()
						.toUri().toString());
				response.setLongUrl(urlDetail.getLongUrl());
				listOfResponses.add(response);
			}
			return new ResponseObject(listOfResponses,null,HttpStatus.OK);
		}else {
			return new ResponseObject("No Record Found",null,HttpStatus.OK);
		}
	}
	
	
	@Override
	public ResponseObject editShortUrl(@RequestBody UpdateRequest shortUrl, @RequestHeader("Authorization") String auth) {
		String authToken2 = auth.substring(7);
		String externalId2 = UserDetailsServiceImpl.getUserExternalId(authToken2);
		User user = userRepository.getUserByExternalId(externalId2);
		Shorter shortUrlDetails=shortRepo.shortUrlDetails(user.getId(),shortUrl.getId(),shortUrl.getOldName())!=null?shortRepo.shortUrlDetails(user.getId(),shortUrl.getId(),shortUrl.getOldName()):null;
		Shorter shortDetails= shortRepo.shortUrlCheck(user.getId(),shortUrl.getOldName())!=null?shortRepo.shortUrlCheck(user.getId(),shortUrl.getOldName()):null;
		if(shortDetails==null) {
		if(shortUrlDetails!=null) {
			shortUrlDetails.setId(shortUrlDetails.getId());
			shortUrlDetails.setShortname(shortUrl.getNewName());
			shortRepo.saveAndFlush(shortUrlDetails);
			URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/" + shortUrl.getNewName()).buildAndExpand()
					.toUri();
			return new ResponseObject(location, null, HttpStatus.OK);
		}else {
			
			return new ResponseObject("Please check details", null, HttpStatus.OK);
		}}else {
			return new ResponseObject("Please specify new name", null, HttpStatus.OK);

		}

	}

}
