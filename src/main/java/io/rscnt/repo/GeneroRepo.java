package io.rscnt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.rscnt.model.Genero;

public interface GeneroRepo extends JpaRepository<Genero, Long> {
}