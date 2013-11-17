package io.rscnt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.rscnt.model.Album;
import io.rscnt.model.Artista;

public interface AlbumRepo extends JpaRepository<Album, Integer> {

	@Query("select a from Album a where LOWER(a.nombre) = LOWER(:albumNombre)")
	public Album findByNombre(@Param("albumNombre") String albumNombre);
	
	@Query("select a from Album a where a.artista = :artista")
	public List<Album> findByArtista(@Param("artista") Artista artista);
	
}
