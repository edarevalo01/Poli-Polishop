package com.polishop.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.polishop.entities.Administrador;

@Repository
public interface AdministradorRepository extends CrudRepository<Administrador, Long>{

	public Optional<Administrador> findByCorreo(String correo);

}
