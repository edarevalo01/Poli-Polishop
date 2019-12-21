package com.polishop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.polishop.entities.LogError;

@Repository
public interface LogErrorRepository extends CrudRepository<LogError, Long>{
	
	public Iterable<LogError> findAllByNombreError(String nombreError);

}
