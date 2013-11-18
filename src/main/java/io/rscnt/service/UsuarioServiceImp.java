package io.rscnt.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.rscnt.model.Usuario;
import io.rscnt.repo.UsuarioRepo;

@Service
public class UsuarioServiceImp implements UsuarioService {

	@Resource
	UsuarioRepo UsuarioRepo;

	@Override
	@Transactional
	public Usuario create(Usuario Usuario) {
		Usuario nuevoUsuario = Usuario;
		return UsuarioRepo.save(nuevoUsuario);
	}

	@Override
	@Transactional
	public Usuario delete(int codigo) {
		Usuario viejoUsuario = UsuarioRepo.findOne(codigo);
		if (viejoUsuario == null) {
			return null; // TODO: MANAGE THIS
		}
		UsuarioRepo.delete(viejoUsuario);
		return viejoUsuario;
	}

	@Override
	@Transactional
	public List<Usuario> findAll() {
		return UsuarioRepo.findAll();
	}

	@Override
	@Transactional
	public Usuario update(Usuario Usuario) {
		Usuario UsuarioActualizado = UsuarioRepo.findOne(Usuario.getCodigo());
		if (UsuarioActualizado == null) {
			return null; // TODO: MANAGE THIS
		}
		return UsuarioActualizado;
	}

	@Override
	public Usuario findById(int codigo) {
		Usuario UsuarioObtenido = UsuarioRepo.findOne(codigo);
		if (UsuarioObtenido == null) {
			return null; // TODO: MANAGE THIS
		}
		return UsuarioObtenido;
	}

	@Override
	public Usuario auth(String usuario, String password) {
		Usuario usuarioObtenido = UsuarioRepo.auth(usuario, password);
		return usuarioObtenido;
	}
}