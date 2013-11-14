package io.rscnt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.rscnt.model.Cancion;

public interface CancionRepo extends JpaRepository<Cancion, Integer> {
	@Query("select c from Cancion c where LOWER(c.nombre) = LOWER(:cancionNombre)")
	public Cancion findByNombre(@Param("cancionNombre") String cancionNombre);
}
