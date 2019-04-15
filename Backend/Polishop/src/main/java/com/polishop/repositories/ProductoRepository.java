package com.polishop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.polishop.entities.Producto;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long>{
	
	public Iterable<Producto> findByIdVendedor(Long idVendedor);
	
	public Iterable<Producto> findByIdPropietario(Long idPropietario);
	
	public Iterable<Producto> findByCalificacion(Long calificacion);
	
}
