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

import com.polishop.entities.CategoriaProducto;
import com.polishop.negocio.CategoriaProductoNegocio;
import com.polishop.negocio.Respuesta;
import com.polishop.repositories.CategoriaProductoRepository;

@RestController
public class CategoriaProductoController {
	
	@Autowired
	private CategoriaProductoRepository categoriaProductoRepositoryDAO;
	
	@CrossOrigin
	@RequestMapping(path = "/addCategoriaProducto", method = RequestMethod.POST)
	public @ResponseBody Respuesta addCategoriaProducto
	(@RequestParam Long idCategoria, @RequestParam Long idProducto) {
		try {
			Optional<CategoriaProducto> categoriaOpt = categoriaProductoRepositoryDAO.findByIdCategoriaAndIdProducto(idCategoria, idProducto);
			if(categoriaOpt.isPresent()) {
				return new Respuesta(Respuesta.FAIL, "La categoria ya existe en este producto.");
			}
			CategoriaProducto categoriaProducto = new CategoriaProducto();
			categoriaProducto.setIdCategoria(idCategoria);
			categoriaProducto.setIdProducto(idProducto);
			categoriaProductoRepositoryDAO.save(categoriaProducto);
			return new Respuesta(Respuesta.OK, "Categoria de producto guardada.");
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getCategoriasByProducto", method = RequestMethod.GET)
	public Respuesta getCategoriaByProducto(@RequestParam Long idProducto){
		try {
			ArrayList<CategoriaProducto> categoriasList = categoriaProductoRepositoryDAO.findByIdProducto(idProducto);
			ArrayList<CategoriaProductoNegocio> categorias = new ArrayList<>();
			for(CategoriaProducto cp: categoriasList) {
				categorias.add(new CategoriaProductoNegocio(cp));
			}
			return new Respuesta(Respuesta.OK, categorias);
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getProductosByCategoria", method = RequestMethod.GET)
	public Respuesta getProductoByCategoria(@RequestParam Long idCategoria){
		try {
			ArrayList<CategoriaProducto> productosList = categoriaProductoRepositoryDAO.findByIdCategoria(idCategoria);
			ArrayList<CategoriaProductoNegocio> productos = new ArrayList<>();
			for(CategoriaProducto cp: productosList) {
				productos.add(new CategoriaProductoNegocio(cp));
			}
			return new Respuesta(Respuesta.OK, productos);
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}
	
	@CrossOrigin
	@RequestMapping(path = "/deleteCategoriaProducto", method = RequestMethod.DELETE)
	public Respuesta deleteCategoriaProducto(@RequestParam Long idCategoria, Long idProducto) {
		try {
			Optional<CategoriaProducto> categoriaOpt = categoriaProductoRepositoryDAO.findByIdCategoriaAndIdProducto(idCategoria, idProducto);
			if(!categoriaOpt.isPresent()) {
				return new Respuesta(Respuesta.FAIL, "La categoria no existe en el producto."); 
			}
			categoriaProductoRepositoryDAO.deleteById(categoriaOpt.get().getId());
			return new Respuesta(Respuesta.OK, "Categoria eliminada del producto");
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

}
