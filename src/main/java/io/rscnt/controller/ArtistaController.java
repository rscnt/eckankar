package io.rscnt.controller;

import java.util.List;

import io.rscnt.service.ArtistaService;
import io.rscnt.model.Artista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public Artista getArtistaByCodigo(@RequestParam("codigo") long codigo) {
		return artistaService.findById((int) codigo);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/nombre/{nombre}")
	@ResponseBody
	public Artista getArtistaByNombre(@RequestParam("nombre") String nombre) {
		return artistaService.findByNombre(nombre);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{codigo}")
	@ResponseBody
	public Artista deleteArtista(@RequestParam("codigo") int codigo) {
		return artistaService.delete(codigo);
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public Artista deleteArtista(@ModelAttribute Artista artista) {
		return artistaService.update(artista);
	}

}
