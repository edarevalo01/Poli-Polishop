package com.polishop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.polishop.entities.CategoriaProducto;
import com.polishop.repositories.CategoriaProductoRepository;

@RestController
public class CategoriaProductoController {
	
	@Autowired
	private CategoriaProductoRepository categoriaProductoRepositoryDAO;
	
	@CrossOrigin
	@RequestMapping(path = "/addCategoriaProducto", method = RequestMethod.POST)
	public @ResponseBody String addCategoriaProducto
	(@RequestParam Long idCategoria, @RequestParam Long idProducto) {
		CategoriaProducto categoriaProducto = new CategoriaProducto();
		categoriaProducto.setIdCategoria(idCategoria);
		categoriaProducto.setIdProducto(idProducto);
		categoriaProductoRepositoryDAO.save(categoriaProducto);
		return "Categoria de producto guardada.";
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getCategoriaByProducto")
	public Iterable<CategoriaProducto> getCategoriaByProducto(@RequestParam Long idProducto){
		return categoriaProductoRepositoryDAO.findByIdProducto(idProducto);
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getProductoByCategoria")
	public Iterable<CategoriaProducto> getProductoByCategoria(@RequestParam Long idCategoria){
		return categoriaProductoRepositoryDAO.findByIdCategoria(idCategoria);
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getAllCategoriaProductos")
	public Iterable<CategoriaProducto> getAllCategoriaProductos(){
		return categoriaProductoRepositoryDAO.findAll();
	}
	
	@CrossOrigin
	@RequestMapping(path = "/deleteCategoriaProducto")
	public String deleteCategoriaProducto(@RequestParam Long idCategoriaProducto) {
		categoriaProductoRepositoryDAO.deleteById(idCategoriaProducto);
		return "Categoria de producto eliminada.";
	}

}
