package com.polishop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.polishop.entities.Comentario;

@Repository
public interface ComentarioRepository extends CrudRepository<Comentario, Long>{

	public Iterable<Comentario> findByIdComprador(Long idComprador);

	public Iterable<Comentario> findByIdProducto(Long idProducto);

}
