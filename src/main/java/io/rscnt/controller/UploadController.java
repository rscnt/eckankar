package io.rscnt.controller;

import io.rscnt.model.Album;
import io.rscnt.model.Artista;
import io.rscnt.model.Cancion;
import io.rscnt.model.Genero;
import io.rscnt.utils.ExifSongInfo;
import io.rscnt.utils.ExifToolWrapper;
import io.rscnt.utils.Utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	
//	@RequestMapping(value = "upload", method = RequestMethod.GET)
//	public String upload() {
//		return "upload";
//	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody
	Cancion nuevaCancion(@RequestParam("file") MultipartFile file,
			@RequestParam("name") String name) {
		// TODO: Check mime.types on server.
		// String[] contentTypes = {"audio/mpeg ", "application/ogg",
		// "audio/ogg", "audio/mp4"};
		String baseDir = System.getenv("RSCNT_DATA_MEDIA") != null ? System
				.getenv("RSCNT_DATA_MEDIA") + "songs/"
				: "/home/_r/media/songs/";
		ExifToolWrapper exifWrapper = new ExifToolWrapper("exiftool");

		if (!file.isEmpty()) {

			if (file.getContentType().startsWith("audio")) {
				try {

					File audio;

					byte[] bytes = file.getBytes();
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(audio = new File(baseDir
									+ Utils.stringClearSpaces(name))));
					stream.write(bytes);
					stream.close();
					ExifSongInfo exifSongInfo = exifWrapper.getSongInfo(audio
							.getAbsolutePath());

					// ------------
					Artista artista = new Artista(null,
							exifSongInfo.getArtist(), null, null);
					Genero genero = new Genero(null, exifSongInfo.getGenre(),
							null, null);
					Album album = new Album(null, exifSongInfo.getAlbum(),
							exifSongInfo.getPicture(), null, genero, artista);
					Cancion cancion = new Cancion(null, audio.getName(),
							exifSongInfo.getTitle(), null, null, artista,
							genero, album, null);
					// ---------------

					return cancion;

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}

		return null;
	}

}
