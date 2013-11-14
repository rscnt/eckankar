package io.rscnt.repo;

import io.rscnt.model.Genero;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GeneroRepo extends JpaRepository<Genero, Integer> {

	@Query("select g from Genero g where LOWER(g.nombre) = LOWER(:generoNombre)")
	public Genero findByNombre(@Param("generoNombre") String generoNombre);

}