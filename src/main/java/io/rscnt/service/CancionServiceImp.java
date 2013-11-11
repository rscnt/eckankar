package io.rscnt.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		Cancion viejoCancion = CancionRepo.findOne((long) codigo);
		if (viejoCancion == null) {
			return null; // TODO: MANAGE THIS
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
		Cancion CancionActualizado = CancionRepo.findOne((long) Cancion
				.getCodigo());
		if (CancionActualizado == null) {
			return null; // TODO: MANAGE THIS
		}
		return CancionActualizado;
	}

	@Override
	public Cancion findById(int codigo) {
		Cancion CancionObtenido = CancionRepo.findOne((long) codigo);
		if (CancionObtenido == null) {
			return null; // TODO: MANAGE THIS
		}
		return CancionObtenido;
	}

	@Override
	public Cancion findByNombre(String nombre) {
		Cancion CancionObtenida = CancionRepo.findByNombre(nombre);
		if (CancionObtenida == null) {
			return null; // TODO: MANAGE THIS
		}
		return CancionObtenida;
	}

}
