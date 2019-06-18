package com.polishop.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.polishop.entities.ProductoCarrito;

@Repository
public interface ProductoCarritoRepository extends CrudRepository<ProductoCarrito, Long>{
	
	public Iterable<ProductoCarrito> findByIdCarrito(Long idCarrito);
	
	public Iterable<ProductoCarrito> findByIdProducto(Long idProducto);
	
	public Optional<ProductoCarrito> findByIdProductoAndIdCarrito(Long idProducto, Long idCarrito);

}
