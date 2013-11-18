package io.rscnt.controller;

import io.rscnt.service.UsuarioService;
import io.rscnt.utils.Utils;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/")
	public String index(HttpSession session) {
		if (session.getAttribute("user") != null) {
			return "base";
		} else {
			return "login";
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("usuario") String usuario,
			@RequestParam("password") String password, HttpSession session) {

		if (!usuarioService.findAll().isEmpty()) {
			if (usuarioService.findAll().size() == 1) {
				if (usuarioService.auth(usuario, password) != null) {
					session.setAttribute("user", usuario);
					return "redirect:/";
				} else {
					return "redirect:login";
				}
			} else {

				if (usuarioService
						.auth(usuario, Utils.encodeToSHA512(password)) != null) {
					session.setAttribute("user", usuario);
					return "redirect:/";
				} else {
					return "redirect:login";
				}

			}
		} else {
			return "redirect:login";
		}

	}

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		if (session.getAttribute("user") != null) {
			session.setAttribute("user", null);
			return "redirect:login";
		} else {
			return "redirect:login";
		}
	}

	public IndexController() {
		// TODO Auto-generated constructor stub
	}

}
