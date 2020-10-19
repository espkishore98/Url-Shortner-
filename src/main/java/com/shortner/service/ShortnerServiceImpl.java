package com.shortner.service;

import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shortner.domain.ResponseObject;
import com.shortner.domain.Shorter;
import com.shortner.domain.User;
import com.shortner.domain.UserSpecificShort;
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
		String externalId2 = userDetailsServiceImpl.getUserExternalId(authToken2);
		User user = userRepository.getUserByExternalId(externalId2);

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

	@Override
	public ResponseObject userSpecificName(UserSpecificShort specificShort, String auth) {
		String authToken2 = auth.substring(7);
		String externalId2 = userDetailsServiceImpl.getUserExternalId(authToken2);
		User user = userRepository.getUserByExternalId(externalId2);
		Shorter Short = new Shorter(UUID.randomUUID().toString(), specificShort.getLongUrl(),
				specificShort.getShortName(), user);
		shortRepo.saveAndFlush(Short);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/" + specificShort.getShortName())
				.buildAndExpand().toUri();

		return new ResponseObject(location, null, HttpStatus.OK);

	}

	@Override
	public String readByColumnName(MultipartFile file, String auth) {
		try {
			String authToken2 = auth.substring(7);
			String externalId2 = userDetailsServiceImpl.getUserExternalId(authToken2);
			User user = userRepository.getUserByExternalId(externalId2);
			InputStream inputStream = file.getInputStream();
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = workbook.getSheet("Sheet1");
			List<Shorter> samples = new ArrayList<Shorter>();
//			SampleDomain sampleDomain;
			Shorter shorter;
			for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum() + 1; rowIndex++) {
				Row row = sheet.getRow(rowIndex);
				if (row != null) {
					Cell longUrl = row.getCell(0);
					Cell shortName = row.getCell(1);
					String longUrlString;
					String shortNameString;
					if (longUrl == null || longUrl.getCellType() == CellType.BLANK) {
						continue;
					} else {
						longUrlString = row.getCell(0).getStringCellValue();
					}
					if (shortName == null || shortName.getCellType() == CellType.BLANK) {
						continue;
					} else {
						shortNameString = row.getCell(1).getStringCellValue();
					}

					shorter = new Shorter(UUID.randomUUID().toString(), longUrlString, shortNameString, user);
					samples.add(shorter);
					shortRepo.saveAndFlush(shorter);
				}

			}
			workbook.close();
			return "Data pushed to Db";

		} catch (Exception e) {
			// TODO: handle exception

			System.out.println(e.getMessage());
			return "error";
		}

	}

}
