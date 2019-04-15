package com.polishop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.polishop.entities.CategoriaProducto;

@Repository
public interface CategoriaProductoRepository extends CrudRepository<CategoriaProducto, Long>{
	
	public Iterable<CategoriaProducto> findByIdProducto(Long idProducto);
	
	public Iterable<CategoriaProducto> findByIdCategoria(Long idCategoria);

}
