package io.rscnt.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.rscnt.model.Album;
import io.rscnt.model.Cancion;
import io.rscnt.repo.CancionRepo;

@Service
public class CancionServiceImp implements CancionService {

	@Resource
	CancionRepo CancionRepo;

	@Override
	@Transactional
	public Cancion create(Cancion Cancion) {
		Cancion nuevoCancion = Cancion;
		return CancionRepo.save(nuevoCancion);
	}

	@Override
	@Transactional
	public Cancion delete(int codigo) {
		Cancion viejoCancion = CancionRepo.findOne(codigo);
		if (viejoCancion == null) {
			return null;
		}
		CancionRepo.delete(viejoCancion);
		return viejoCancion;
	}

	@Override
	@Transactional
	public List<Cancion> findAll() {
		return CancionRepo.findAll();
	}

	@Override
	@Transactional
	public Cancion update(Cancion Cancion) {
		Cancion CancionActualizado = CancionRepo.findOne(Cancion.getCodigo());
		if (CancionActualizado == null) {
			return null;
		}
		return CancionActualizado;
	}

	@Override
	public Cancion findById(int codigo) {
		Cancion CancionObtenido = CancionRepo.findOne(codigo);
		if (CancionObtenido == null) {
			return null;
		}
		return CancionObtenido;
	}

	@Override
	public Cancion findByNombre(String nombre) {
		Cancion CancionObtenida = CancionRepo.findByNombre(nombre);
		if (CancionObtenida == null) {
			return null;
		}
		return CancionObtenida;
	}

	@Override
	public List<Cancion> findByAlbum(Album album) {
		List<Cancion> cancionesAlbum = CancionRepo.findByAlbum(album);
		if (cancionesAlbum == null) {
			return null;
		} else {
			return cancionesAlbum;
		}
	}

}
