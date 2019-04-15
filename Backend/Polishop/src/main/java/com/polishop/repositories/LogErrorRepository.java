package com.polishop.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.polishop.entities.LogError;

@Repository
public interface LogErrorRepository extends CrudRepository<LogError, Long>{
	
	public Optional<LogError> findByNombreError(String nombreError);

}
