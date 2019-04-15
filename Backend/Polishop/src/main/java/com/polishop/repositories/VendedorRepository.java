package com.polishop.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.polishop.entities.Vendedor;

@Repository
public interface VendedorRepository extends CrudRepository<Vendedor, Long>{

	Optional<Vendedor> findByCorreo(String correo);
	
}
