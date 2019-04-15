package com.polishop.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.polishop.entities.Comprador;

@Repository
public interface CompradorRepository extends CrudRepository<Comprador, Long>{
	
	public Optional<Comprador> findByCorreo(String correo);

}
