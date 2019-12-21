package com.polishop.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.polishop.entities.PropietarioNegocio;

@Repository
public interface PropietarioNegocioRepository extends CrudRepository<PropietarioNegocio, Long>{

	Optional<PropietarioNegocio> findByCorreo(String correo);

}
