package io.rscnt.service;

import java.util.List;

import io.rscnt.model.Artista;

public interface ArtistaService {
	public Artista create(Artista Artista);

	public Artista delete(int codigo);

	public List<Artista> findAll();

	public Artista update(Artista Artista);

	public Artista findById(int codidgo);

	public Artista findByNombre(String nombre);
}
