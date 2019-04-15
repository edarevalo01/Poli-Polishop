package com.polishop.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.polishop.entities.LogActividades;

@Repository
public interface LogActividadesRepository extends CrudRepository<LogActividades, Long>{
	
	public Optional<LogActividades> findByNombreActividad(String nombreActividad);

}
