package io.rscnt.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import io.rscnt.service.AlbumService;
import io.rscnt.service.ArtistaService;
import io.rscnt.utils.Utils;
import io.rscnt.model.Album;
import io.rscnt.model.Artista;

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
@RequestMapping(value = "/artistas", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArtistaController {

	@Autowired
	private ArtistaService artistaService;
	@Autowired
	private AlbumService albumService;

	public ArtistaController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Artista> getArtistas() {
		return artistaService.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{codigo}")
	@ResponseBody
	public Artista getArtistaByCodigo(@PathVariable int codigo) {
		return artistaService.findById(codigo);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{codigo}/albums")
	@ResponseBody
	public List<Album> getAlbmsByArtista(@PathVariable int codigo) {
		return albumService.findByArtista(artistaService.findById(codigo));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/nombre/{nombre}")
	@ResponseBody
	public Artista getArtistaByNombre(@PathVariable String nombre) {
		return artistaService.findByNombre(nombre);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{codigo}")
	@ResponseBody
	public Artista deleteArtista(@PathVariable int codigo) {
		return artistaService.delete(codigo);
	}

	// YEAH AS YOU CAN SEE I GIVE UP, OK!! FUCK U MOTHE FUCKA .|. // THIS
	// COMMENT NEVER GO ON PRODUCTION SORRY.
	@RequestMapping(method = RequestMethod.POST, value = "u/{codigo}")
	@ResponseBody
	public Artista updateArtista(@RequestParam("nombre") String nombre,
			@RequestParam("descripcion") String descripcion,
			@RequestParam("imagen_src") String imagen_src,
			@RequestParam("file") MultipartFile file, @PathVariable int codigo) {

		String baseDir = System.getenv("RSCNT_DATA_MEDIA") != null ? System
				.getenv("RSCNT_DATA_MEDIA") + "banners/"
				: "/home/_r/media/banners/";

		Artista artista = artistaService.findByNombre(nombre);

		artista.setNombre(nombre);
		artista.setDescripcion(descripcion);
		artista.setImagen_src(imagen_src);

		try {
			if (!file.isEmpty()) {
				if (file.getContentType().startsWith("image")) {
					@SuppressWarnings("unused")
					File banner;

					byte[] bytes = file.getBytes();
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(banner = new File(baseDir
									+ Utils.stringClearSpaces(artista
											.getNombre()) + ".jpg")));
					stream.write(bytes);
					stream.close();

				}
			}
		} catch (Exception excep) {
			excep.printStackTrace();
		}

		return artistaService.update(artista);
	}

}
