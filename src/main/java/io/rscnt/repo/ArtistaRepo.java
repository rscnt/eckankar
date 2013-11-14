package io.rscnt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.rscnt.model.Artista;

public interface ArtistaRepo extends JpaRepository<Artista, Integer> {

	@Query("select a from Artista a where LOWER(a.nombre) = LOWER(:artistaNombre)")
	public Artista findByNombre(@Param("artistaNombre") String artistaNombre);

}
