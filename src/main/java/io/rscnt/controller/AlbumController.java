package io.rscnt.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import io.rscnt.service.AlbumService;
import io.rscnt.service.CancionService;
import io.rscnt.utils.Utils;
import io.rscnt.model.Album;
import io.rscnt.model.Cancion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/albums", produces = MediaType.APPLICATION_JSON_VALUE)
public class AlbumController {

	@Autowired
	private AlbumService albumService;
	@Autowired
	private CancionService cancionService;

	public AlbumController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Album> getAlbums() {
		return albumService.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{codigo}")
	@ResponseBody
	public Album getAlbumByCodigo(@PathVariable int codigo) {
		return albumService.findById(codigo);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/nombre/{nombre}")
	@ResponseBody
	public Album getAlbumByNombre(@PathVariable String nombre) {
		return albumService.findByNombre(nombre);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{codigo}/canciones")
	@ResponseBody
	public List<Cancion> getAlbumCanciones(@PathVariable int codigo) {
		return cancionService.findByAlbum(albumService.findById(codigo));
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{codigo}")
	@ResponseBody
	public Album deleteAlbum(@PathVariable int codigo) {
		return albumService.delete(codigo);
	}

	@RequestMapping(method = RequestMethod.POST, value = "u/{codigo}")
	@ResponseBody
	public Album updateAlbum(@PathVariable int codigo,
			@RequestParam("nombre") String nombre,
			@RequestParam("imagen_src") MultipartFile imagen_src,
			@RequestParam("file") MultipartFile file) {

		String baseDir = System.getenv("RSCNT_DATA_MEDIA") != null ? System
				.getenv("RSCNT_DATA_MEDIA") : "/home/_r/media/";

		Album album = albumService.findById(codigo);

		album.setCodigo(codigo);
		album.setNombre(nombre);

		try {
			if (!imagen_src.isEmpty()) {
				if (file.getContentType().startsWith("image")) {
					@SuppressWarnings("unused")
					File imgSrc;

					byte[] bytes = imagen_src.getBytes();
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(imgSrc = new File(baseDir
									+ "/covers/" + album.getImagen_src())));
					stream.write(bytes);
					stream.close();

				}
			}
			if (!file.isEmpty()) {
				if (file.getContentType().startsWith("image")) {
					@SuppressWarnings("unused")
					File banner;

					byte[] bytes = file.getBytes();
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(banner = new File(
									baseDir
											+ "/banners/"
											+ Utils.stringClearSpaces(album
													.getNombre()) + ".jpg")));
					stream.write(bytes);
					stream.close();

				}
			}
		} catch (Exception excep) {
			excep.printStackTrace();
		}

		return albumService.update(album);

	}
}
