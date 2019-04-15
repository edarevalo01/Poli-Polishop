package com.polishop.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.polishop.entities.Categoria;
import com.polishop.repositories.CategoriaRepository;

@RestController
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepositoryDAO;
	
	@CrossOrigin
	@RequestMapping(path = "/addCategoria", method = RequestMethod.POST)
	public @ResponseBody String addCategoria
	(@RequestParam String nombre, @RequestParam String descripcion) {
		Categoria categoria = new Categoria();
		categoria.setNombre(nombre);
		categoria.setDescripcion(descripcion);
		categoriaRepositoryDAO.save(categoria);
		return "Categoría guardada.";
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getCategoriaByNombre")
	public Categoria getCategoriaByNombre(@RequestParam String nombre) {
		Optional<Categoria> optCategoria = categoriaRepositoryDAO.findByNombre(nombre);
		if(!optCategoria.isPresent()) return null;
		return optCategoria.get();
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getAllCategorias")
	public Iterable<Categoria> getAllCategorias(){
		return categoriaRepositoryDAO.findAll();
	}

}
