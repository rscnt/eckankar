package io.rscnt.service;

import io.rscnt.model.Album;
import io.rscnt.model.Cancion;

import java.util.List;

public interface CancionService {
	public Cancion create(Cancion Cancion);

	public Cancion delete(int codigo);

	public List<Cancion> findAll();

	public Cancion update(Cancion Cancion);

	public Cancion findById(int codidgo);

	public Cancion findByNombre(String nombre);

	public List<Cancion> findByAlbum(Album album);
}
