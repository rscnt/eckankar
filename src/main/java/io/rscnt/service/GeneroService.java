package io.rscnt.service;

import java.util.List;

import io.rscnt.model.Genero;

public interface GeneroService {
	
	public Genero create(Genero genero);
	public Genero delete(int codigo);
	public List<Genero> findAll();
	public Genero update(Genero genero);
	public Genero findById(int codidgo);
	
}
