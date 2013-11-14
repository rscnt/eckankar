package io.rscnt.controller;

import java.util.List;

import io.rscnt.service.GeneroService;
import io.rscnt.model.Genero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/generos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GeneroController {

	@Autowired
	private GeneroService generoService;

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

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public Genero deleteGenero(@ModelAttribute Genero genero) {
		return generoService.update(genero);
	}

}
