package com.polishop.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.polishop.entities.Compra;

@Repository
public interface CompraRepository extends CrudRepository<Compra, Long>{
	
	public Optional<Compra> findByNumeroPedido(String numeroPedido);
	
	public Iterable<Compra> findByIdComprador(Long idComprador);
	
	public Iterable<Compra> findByNumeroDocumento(String numeroDocumento);
	
	public Optional<Compra> findByIdCarrito(Long idCarrito);
	
}
