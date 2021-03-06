package io.rscnt.controller;

import io.rscnt.model.Album;
import io.rscnt.model.Artista;
import io.rscnt.model.Cancion;
import io.rscnt.model.Genero;
import io.rscnt.utils.ExifSongInfo;
import io.rscnt.utils.ExifToolWrapper;
import io.rscnt.utils.Utils;
import io.rscnt.service.AlbumService;
import io.rscnt.service.ArtistaService;
import io.rscnt.service.CancionService;
import io.rscnt.service.GeneroService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

	@Autowired
	private GeneroService generoService;
	@Autowired
	private ArtistaService artistaService;
	@Autowired
	private AlbumService albumService;
	@Autowired
	private CancionService cancionService;

	private Genero genero;
	private Artista artista;
	private Album album;
	private Cancion cancion;

	 @RequestMapping(value = "/upload", method = RequestMethod.GET)
	 public String upload() {
	 return "base";
	 }

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

					genero = generoService
							.findByNombre(exifSongInfo.getGenre());

					if (genero == null) {
						genero = new Genero(null, exifSongInfo.getGenre(), "",
								"");
						generoService.create(genero);
					}

					artista = artistaService.findByNombre(exifSongInfo
							.getArtist());

					if (artista == null) {
						artista = new Artista(null, exifSongInfo.getArtist(),
								"", "");
						artistaService.create(artista);
					}

					album = albumService.findByNombre(exifSongInfo.getAlbum());

					if (album == null) {
						album = new Album(null, exifSongInfo.getAlbum(),
								exifSongInfo.getPicture(), 0.0, genero, artista);
						albumService.create(album);
					}

					cancion = cancionService.findByNombre(exifSongInfo
							.getTitle());
					if (cancion == null) {
						cancion = new Cancion(null, exifSongInfo.getFileName(),
								exifSongInfo.getTitle(), 0, 0, artista, genero,
								album);

						cancionService.create(cancion);
					}
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
