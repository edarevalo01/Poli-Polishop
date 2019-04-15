package com.polishop.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.polishop.entities.Categoria;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long>{
	
	public Optional<Categoria> findByNombre(String nombre);

}
