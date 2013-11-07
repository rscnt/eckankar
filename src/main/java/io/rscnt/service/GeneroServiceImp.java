package io.rscnt.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.rscnt.model.Genero;
import io.rscnt.repo.GeneroRepo;

@Service
public class GeneroServiceImp implements GeneroService {

	@Resource
	GeneroRepo generoRepo;

	@Override
	@Transactional
	public Genero create(Genero genero) {
		Genero nuevoGenero = genero;
		return generoRepo.save(nuevoGenero);
	}

	@Override
	@Transactional
	public Genero delete(int codigo) {
		Genero viejoGenero = generoRepo.findOne((long) codigo);
		if (viejoGenero == null) {
			return null; // TODO: MANAGE THIS
		}
		generoRepo.delete(viejoGenero);
		return viejoGenero;
	}

	@Override
	@Transactional
	public List<Genero> findAll() {
		return generoRepo.findAll();
	}

	@Override
	@Transactional
	public Genero update(Genero genero) {
		Genero generoActualizado = generoRepo.findOne(genero.getCodigo());
		if (generoActualizado == null) {
			return null; // TODO: MANAGE THIS
		}
		return generoActualizado;
	}

	@Override
	public Genero findById(int codigo) {
		Genero generoObtenido = generoRepo.findOne((long) codigo);
		if (generoObtenido == null) {
			return null; // TODO: MANAGE THIS
		}
		return generoObtenido;
	}

}