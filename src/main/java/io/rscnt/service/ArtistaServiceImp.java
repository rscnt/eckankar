package io.rscnt.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.rscnt.model.Artista;
import io.rscnt.repo.ArtistaRepo;

@Service
public class ArtistaServiceImp implements ArtistaService {

	@Resource
	ArtistaRepo ArtistaRepo;

	@Override
	@Transactional
	public Artista create(Artista Artista) {
		Artista nuevoArtista = Artista;
		return ArtistaRepo.save(nuevoArtista);
	}

	@Override
	@Transactional
	public Artista delete(int codigo) {
		Artista viejoArtista = ArtistaRepo.findOne(codigo);
		if (viejoArtista == null) {
			return null; // TODO: MANAGE THIS
		}
		ArtistaRepo.delete(viejoArtista);
		return viejoArtista;
	}

	@Override
	@Transactional
	public List<Artista> findAll() {
		return ArtistaRepo.findAll();
	}

	@Override
	@Transactional
	public Artista update(Artista Artista) {
		Artista ArtistaActualizado = ArtistaRepo.findOne(Artista.getCodigo());
		if (ArtistaActualizado == null) {
			return null; // TODO: MANAGE THIS
		}
		return ArtistaActualizado;
	}

	@Override
	public Artista findById(int codigo) {
		Artista ArtistaObtenido = ArtistaRepo.findOne(codigo);
		if (ArtistaObtenido == null) {
			return null; // TODO: MANAGE THIS
		}
		return ArtistaObtenido;
	}

	@Override
	public Artista findByNombre(String nombre) {
		Artista artistaObtenido = ArtistaRepo.findByNombre(nombre);
		if (artistaObtenido == null) {
			return null;
		} else {
			return artistaObtenido;
		}
	}

}