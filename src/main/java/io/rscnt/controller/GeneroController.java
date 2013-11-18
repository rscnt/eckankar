package io.rscnt.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import io.rscnt.service.GeneroService;
import io.rscnt.service.AlbumService;
import io.rscnt.utils.Utils;
import io.rscnt.model.Album;
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
@RequestMapping(value = "/generos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GeneroController {

	@Autowired
	private GeneroService generoService;
	@Autowired
	private AlbumService albumService;

	public GeneroController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Genero> getGeneros() {
		return generoService.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{codigo}")
	@ResponseBody
	public Genero getGeneroByCodigo(@PathVariable int codigo) {
		return generoService.findById(codigo);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/nombre/{nombre}")
	@ResponseBody
	public Genero getGeneroByNombre(@PathVariable String nombre) {
		return generoService.findByNombre(nombre);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{codigo}")
	@ResponseBody
	public Genero deleteGenero(@PathVariable int codigo) {
		return generoService.delete(codigo);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{codigo}/albums")
	@ResponseBody
	public List<Album> getAlbmsByArtista(@PathVariable int codigo) {
		return albumService.findByGenero(generoService.findById(codigo));
	}


	@RequestMapping(method = RequestMethod.POST, value = "u/{codigo}")
	@ResponseBody
	public Genero updateGenero(@PathVariable int codigo,
			@RequestParam("nombre") String nombre,
			@RequestParam("descripcion") String descripcion,
			@RequestParam("file") MultipartFile file) {

		String baseDir = System.getenv("RSCNT_DATA_MEDIA") != null ? System
				.getenv("RSCNT_DATA_MEDIA") + "banners/"
				: "/home/_r/media/banners/";

		Genero genero = generoService.findById(codigo);

		genero.setCodigo(codigo);
		genero.setNombre(nombre);
		genero.setDescripcion(descripcion);

		try {
			if (!file.isEmpty()) {
				if (file.getContentType().startsWith("image")) {
					@SuppressWarnings("unused")
					File banner;

					byte[] bytes = file.getBytes();
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(banner = new File(baseDir
									+ Utils.stringClearSpaces(genero
											.getNombre()) + ".jpg")));
					stream.write(bytes);
					stream.close();

				}
			}
		} catch (Exception excep) {
			excep.printStackTrace();
		}

		return generoService.update(genero);

	}

}
