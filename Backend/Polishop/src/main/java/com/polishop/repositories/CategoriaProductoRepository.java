package com.polishop.repositories;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.polishop.entities.CategoriaProducto;

@Repository
public interface CategoriaProductoRepository extends CrudRepository<CategoriaProducto, Long>{
	
	public ArrayList<CategoriaProducto> findByIdProducto(Long idProducto);
	
	public ArrayList<CategoriaProducto> findByIdCategoria(Long idCategoria);
	
	public Optional<CategoriaProducto> findByIdCategoriaAndIdProducto(Long idCategoria, Long idProducto);

}
