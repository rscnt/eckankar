package io.rscnt.repo;

import io.rscnt.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

	@Query("select u from Usuario u where LOWER(u.usuario) = LOWER(:usuario) AND LOWER(u.password) = LOWER(:password)")
	public Usuario auth(@Param("usuario") String usuario,
			@Param("password") String password);

}