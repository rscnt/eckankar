package io.rscnt.service;

import java.util.List;

import io.rscnt.model.Usuario;

public interface UsuarioService {

	public Usuario create(Usuario usuario);

	public Usuario delete(int codigo);

	public List<Usuario> findAll();

	public Usuario update(Usuario usuario);

	public Usuario findById(int codidgo);

	public Usuario auth(String usuario, String password);

}
