package io.rscnt.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

//import io.rscnt.service.AlbumService;
//import io.rscnt.service.ArtistaService;
//import io.rscnt.service.GeneroService;
import io.rscnt.service.CancionService;
import io.rscnt.utils.Utils;
import io.rscnt.model.Album;
import io.rscnt.model.Artista;
import io.rscnt.model.Cancion;
import io.rscnt.model.Genero;

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
@RequestMapping(value = "/cancions", produces = MediaType.APPLICATION_JSON_VALUE)
public class CancionController {

	@Autowired
	private CancionService cancionService;

	public CancionController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Cancion> getCancions() {
		return cancionService.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{codigo}")
	@ResponseBody
	public Cancion getCancionByCodigo(@PathVariable int codigo) {
		return cancionService.findById(codigo);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/nombre/{nombre}")
	@ResponseBody
	public Cancion getCancionByNombre(@PathVariable String nombre) {
		return cancionService.findByNombre(nombre);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/busqueda/{nombre}")
	@ResponseBody
	public List<Cancion> searchByNombre(@PathVariable String nombre) {
		return cancionService.searchByNombre(nombre);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{codigo}")
	@ResponseBody
	public Cancion deleteCancion(@PathVariable int codigo) {
		return cancionService.delete(codigo);
	}

	@RequestMapping(method = RequestMethod.POST, value = "u/{codigo}")
	@ResponseBody
	public Cancion updateCancion(@PathVariable int codigo,
			@RequestParam("nombre") String nombre,
			@RequestParam("album") Album album,
			@RequestParam("artista") Artista artista,
			@RequestParam("genero") Genero genero,
			@RequestParam("file") MultipartFile file) {

		String baseDir = System.getenv("RSCNT_DATA_MEDIA") != null ? System
				.getenv("RSCNT_DATA_MEDIA") + "banners/"
				: "/home/_r/media/banners/";

		Cancion cancion = cancionService.findById(codigo);

		cancion.setCodigo(codigo);
		cancion.setNombre(nombre);
		cancion.setAlbum(album);
		cancion.setArtista(artista);

		try {
			if (!file.isEmpty()) {
				if (file.getContentType().startsWith("image")) {
					@SuppressWarnings("unused")
					File banner;

					byte[] bytes = file.getBytes();
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(banner = new File(baseDir
									+ Utils.stringClearSpaces(cancion
											.getNombre()) + ".jpg")));
					stream.write(bytes);
					stream.close();

				}
			}
		} catch (Exception excep) {
			excep.printStackTrace();
		}

		return cancionService.update(cancion);

	}

}