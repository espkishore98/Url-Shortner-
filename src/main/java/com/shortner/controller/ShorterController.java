package com.shortner.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shortner.domain.ResponseObject;
import com.shortner.domain.UpdateRequest;
import com.shortner.domain.UserSpecificShort;
import com.shortner.repository.ShorterRepository;
import com.shortner.service.ShortnerService;

@RestController
public class ShorterController {
	@Autowired
	ShortnerService shortnerService;
	@Autowired
	ShorterRepository shortRepo;

	@GetMapping("/shorter")
	public ResponseObject randomShort(@RequestParam String LongUrl, @RequestHeader("Authorization") String auth) {
		return shortnerService.generateRandomString(LongUrl, auth);
	}

	@PostMapping("/SpecificShorter")
	public ResponseObject specificShort(@RequestBody UserSpecificShort shorter,
			@RequestHeader("Authorization") String auth) {
		return shortnerService.userSpecificName(shorter, auth);
	}

	@GetMapping("/{randomString}")
	public void RedirectToLong(HttpServletResponse response, @PathVariable String randomString) throws IOException {
		response.sendRedirect(shortRepo.getuserBasedOnRandom(randomString));
		// return "redirect:" + shortRepo.getuserBasedOnRandom(randomString);
	}

	@GetMapping("/getAllUserUrls")
	public ResponseObject getAllUrls(@RequestHeader("Authorization") String auth) {
		return shortnerService.getAllUrlDetails(auth);
	}

	@PostMapping("/editShortUrl")
	public ResponseObject editShortUrl(@RequestHeader("Authorization") String auth,
			@RequestBody UpdateRequest updateRequest) {
		return shortnerService.editShortUrl(updateRequest, auth);

	}
}
