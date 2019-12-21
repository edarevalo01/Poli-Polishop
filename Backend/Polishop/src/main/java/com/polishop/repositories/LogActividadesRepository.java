package com.polishop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.polishop.entities.LogActividades;

@Repository
public interface LogActividadesRepository extends CrudRepository<LogActividades, Long>{
	
	public Iterable<LogActividades> findAllByNombreActividad(String nombreActividad);

}
