package com.polishop.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.polishop.entities.Categoria;import com.polishop.negocio.Respuesta;
import com.polishop.repositories.CategoriaRepository;

@RestController
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepositoryDAO;

	@CrossOrigin
	@RequestMapping(path = "/addCategoria", method = RequestMethod.POST)
	public @ResponseBody Respuesta addCategoria
	(@RequestParam String nombre, @RequestParam String descripcion) {
		try {
			ArrayList<Categoria> categorias = categoriaRepositoryDAO.findByNombreContaining(nombre.toLowerCase());
			if(categorias.size() > 0) {
				return new Respuesta(Respuesta.FAIL, "La categoría ya existe.");
			}
			Categoria categoria = new Categoria();
			categoria.setNombre(nombre.toLowerCase());
			categoria.setDescripcion(descripcion);
			categoriaRepositoryDAO.save(categoria);
			return new Respuesta(Respuesta.OK, "La categoría se ha guardado.");			
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

	@CrossOrigin
	@RequestMapping(path = "/getCategoriaByNombre", method = RequestMethod.GET)
	public Respuesta getCategoriaByNombre(@RequestParam String nombre) {
		try {
			Optional<Categoria> optCategoria = categoriaRepositoryDAO.findByNombre(nombre);
			if(!optCategoria.isPresent()) {
				return new Respuesta(Respuesta.FAIL, "La categoría no existe.");
			}
			return new Respuesta(Respuesta.OK, optCategoria.get());			
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

	@CrossOrigin
	@RequestMapping(path = "/getAllCategorias", method = RequestMethod.GET)
	public Respuesta getAllCategorias() {
		try {			
			return new Respuesta(Respuesta.OK, categoriaRepositoryDAO.findAll());
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}
	
	@CrossOrigin
	@RequestMapping(path = "/modificarCategoria", method = RequestMethod.PUT)
	public Respuesta modificarCategoria(@RequestParam Long idCategoria, @RequestParam String descripcion) {
		try {			
			Optional<Categoria> optCategoria = categoriaRepositoryDAO.findById(idCategoria);
			if(!optCategoria.isPresent()) {
				return new Respuesta(Respuesta.FAIL, "La categoría no existe.");
			}
			Categoria categoria = optCategoria.get();
			categoria.setDescripcion(descripcion);
			categoriaRepositoryDAO.save(categoria);
			return new Respuesta(Respuesta.OK, categoria);
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}
	
	@CrossOrigin
	@RequestMapping(path = "/eliminarCategoria", method = RequestMethod.DELETE)
	public Respuesta eliminarCategoria(@RequestParam Long idCategoria) {
		try {
			Optional<Categoria> optCategoria = categoriaRepositoryDAO.findById(idCategoria);
			if(!optCategoria.isPresent()) {
				return new Respuesta(Respuesta.FAIL, "La categoría no existe.");
			}
			categoriaRepositoryDAO.deleteById(idCategoria);
			return new Respuesta(Respuesta.OK, "Categofía eliminada satisfactoriamente.");
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}
	
}
