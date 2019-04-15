package com.polishop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.polishop.entities.Carrito;

@Repository
public interface CarritoRepository extends CrudRepository<Carrito, Long>{

}
