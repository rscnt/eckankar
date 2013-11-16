package io.rscnt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.rscnt.model.Album;

public interface AlbumRepo extends JpaRepository<Album, Integer> {

	@Query("select a from Album a where LOWER(a.nombre) = LOWER(:albumNombre)")
	public Album findByNombre(@Param("albumNombre") String albumNombre);

}
