package io.rscnt.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.rscnt.model.Album;
import io.rscnt.model.Artista;
import io.rscnt.repo.AlbumRepo;

@Service
public class AlbumServiceImp implements AlbumService {

	@Resource
	AlbumRepo AlbumRepo;

	@Override
	@Transactional
	public Album create(Album Album) {
		Album nuevoAlbum = Album;
		return AlbumRepo.save(nuevoAlbum);
	}

	@Override
	@Transactional
	public Album delete(int codigo) {
		Album viejoAlbum = AlbumRepo.findOne(codigo);
		if (viejoAlbum == null) {
			return null; // TODO: MANAGE THIS
		}
		AlbumRepo.delete(viejoAlbum);
		return viejoAlbum;
	}

	@Override
	@Transactional
	public List<Album> findAll() {
		return AlbumRepo.findAll();
	}

	@Override
	@Transactional
	public Album update(Album Album) {
		Album AlbumActualizado = AlbumRepo.findOne(Album.getCodigo());
		if (AlbumActualizado == null) {
			return null; // TODO: MANAGE THIS
		}
		return AlbumActualizado;
	}

	@Override
	public Album findById(int codigo) {
		Album AlbumObtenido = AlbumRepo.findOne(codigo);
		if (AlbumObtenido == null) {
			return null; // TODO: MANAGE THIS
		}
		return AlbumObtenido;
	}

	public Album findByNombre(String nombre) {
		Album albumObtenido = AlbumRepo.findByNombre(nombre);
		if (albumObtenido == null) {
			return null;
		}
		return albumObtenido;
	}

	@Override
	public List<Album> findByArtista(Artista artista) {
		List<Album> albumesEncontrados = AlbumRepo.findByArtista(artista);
		return albumesEncontrados;
	}
}