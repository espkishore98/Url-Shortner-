package com.shortner.service;

import org.springframework.web.multipart.MultipartFile;

import com.shortner.domain.ResponseObject;
import com.shortner.domain.UserSpecificShort;

public interface ShortnerService {

	ResponseObject generateRandomString(String LongUrl, String auth);

	ResponseObject userSpecificName(UserSpecificShort specificShort, String auth);

	String readByColumnName(MultipartFile file, String auth);

}
