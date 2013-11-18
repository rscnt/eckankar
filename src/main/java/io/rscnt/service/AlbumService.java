package io.rscnt.service;

import java.util.List;

import io.rscnt.model.Album;
import io.rscnt.model.Artista;
import io.rscnt.model.Genero;

public interface AlbumService {
	public Album create(Album Album);

	public Album delete(int codigo);

	public List<Album> findAll();

	public Album update(Album Album);

	public Album findById(int codidgo);

	public Album findByNombre(String nombre);

	public List<Album> findByArtista(Artista artista);

	public List<Album> findByGenero(Genero genero);
}
