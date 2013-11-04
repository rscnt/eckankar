package io.rscnt.controller;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
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

	@RequestMapping(produces = MediaType.IMAGE_JPEG_VALUE, value = "/covers/{cover_name}", method = RequestMethod.GET)
	@ResponseBody
	public FileSystemResource getCover(
			@PathVariable("cover_name") String fileName,
			HttpServletResponse response) {
		response.setHeader("Content-Type", "image/jpg");
		String baseDir = System.getenv("RSCNT_DATA_MEDIA") != null ? System
				.getenv("RSCNT_DATA_MEDIA") + "covers/"
				: "/home/_r/media/covers/";
		File f = new File(baseDir + fileName + ".jpg");
		if (f.exists() && f.length() != 0) {
			return new FileSystemResource(baseDir + fileName + ".jpg");
		} else {
			return new FileSystemResource(baseDir + "default.jpg");
		}

	}

	@RequestMapping(value = "/songs/{song_name}", method = RequestMethod.GET)
	@ResponseBody
	public FileSystemResource getSong(@PathVariable("song_name") String fileName) {
		String baseDir = System.getenv("RSCNT_DATA_MEDIA") != null ? System
				.getenv("RSCNT_DATA_MEDIA") + "songs/"
				: "/home/_r/media/songs/";
		return new FileSystemResource(baseDir + fileName + ".mp3");
	}
}
