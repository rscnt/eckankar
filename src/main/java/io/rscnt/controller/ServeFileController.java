package io.rscnt.controller;

import java.io.File;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ServeFileController {

	public ServeFileController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(value = "/covers/{cover_name}", method = RequestMethod.GET)
	@ResponseBody
	public HttpEntity<FileSystemResource> getCover(
			@PathVariable("cover_name") String fileName) {

		String baseDir = System.getenv("RSCNT_DATA_MEDIA") != null ? System
				.getenv("RSCNT_DATA_MEDIA") + "covers/"
				: "/home/_r/media/covers/";

		File f = new File(baseDir + fileName + ".jpg");

		if (f.exists() && f.length() != 0) {

			HttpHeaders httpHeaders = new HttpHeaders();

			httpHeaders.setContentType(MediaType.IMAGE_JPEG);

			FileSystemResource fsr = new FileSystemResource(baseDir + fileName
					+ ".jpg");

			return new ResponseEntity<FileSystemResource>(fsr, httpHeaders,
					HttpStatus.OK);

		} else {

			HttpHeaders httpHeaders = new HttpHeaders();

			httpHeaders.setContentType(MediaType.IMAGE_JPEG);

			FileSystemResource fsr = new FileSystemResource(baseDir
					+ "default.jpg");

			return new ResponseEntity<FileSystemResource>(fsr, httpHeaders,
					HttpStatus.OK);

		}

	}

	@RequestMapping(value = "/songs/{song_name}", method = RequestMethod.GET)
	@ResponseBody
	public HttpEntity<FileSystemResource> getSong(
			@PathVariable("song_name") String fileName) {

		String baseDir = System.getenv("RSCNT_DATA_MEDIA") != null ? System
				.getenv("RSCNT_DATA_MEDIA") + "songs/"
				: "/home/_r/media/songs/";

		FileSystemResource fsr = new FileSystemResource(baseDir + fileName
				+ ".mp3");

		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		return new ResponseEntity<FileSystemResource>(fsr, httpHeaders,
				HttpStatus.OK);

	}
}
