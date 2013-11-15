package io.rscnt.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import io.rscnt.service.AlbumService;
import io.rscnt.service.CancionService;
import io.rscnt.utils.Utils;
import io.rscnt.model.Album;

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

	@RequestMapping(method = RequestMethod.DELETE, value = "/{codigo}")
	@ResponseBody
	public Album deleteAlbum(@PathVariable int codigo) {
		return albumService.delete(codigo);
	}

	@RequestMapping(method = RequestMethod.POST, value = "u/{codigo}")
	@ResponseBody
	public Album updateAlbum(@PathVariable int codigo,
			@RequestParam("nombre") String nombre,
			@RequestParam("precio") Double precio,
			@RequestParam("imagen_src") String imagen_src,
			@RequestParam("file") MultipartFile file) {

		String baseDir = System.getenv("RSCNT_DATA_MEDIA") != null ? System
				.getenv("RSCNT_DATA_MEDIA") + "banners/"
				: "/home/_r/media/banners/";

		Album album = albumService.findById(codigo);

		album.setCodigo(codigo);
		album.setNombre(nombre);
		album.setPrecio(precio);
		album.setImagen_src(imagen_src);

		try {
			if (!file.isEmpty()) {
				if (file.getContentType().startsWith("image")) {
					@SuppressWarnings("unused")
					File banner;

					byte[] bytes = file.getBytes();
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(banner = new File(
									baseDir
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

	@RequestMapping(method = RequestMethod.GET, value = "/{codigo}/canciones")
	@ResponseBody
	public Album getCancionAlbum(@PathVariable int codigo) {
		return null;
	}

}
