package io.rscnt.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.rscnt.model.Album;
import io.rscnt.model.Cancion;

public interface AlbumRepo extends JpaRepository<Album, Integer> {

	@Query("select a from Album a where LOWER(a.nombre) = LOWER(:albumNombre)")
	public Album findByNombre(@Param("albumNombre") String albumNombre);

	@Query("select c from Cancion a where LOWER(c.album) = LOWER(:albumCodigo)")
	public Set<Cancion> findCancionByAlbumCodigo(
			@Param("albumCodigo") int albumCodigo);

}
