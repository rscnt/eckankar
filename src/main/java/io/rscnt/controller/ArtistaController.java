package io.rscnt.controller;

import java.util.List;

import io.rscnt.service.ArtistaService;
import io.rscnt.model.Artista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public Artista deleteArtista(@ModelAttribute Artista artista,
			@RequestParam("banner") MultipartFile banner) {
		
		return artistaService.update(artista);
	}

}
