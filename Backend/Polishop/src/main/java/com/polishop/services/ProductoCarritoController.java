package com.polishop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.polishop.entities.ProductoCarrito;
import com.polishop.repositories.ProductoCarritoRepository;

@RestController
public class ProductoCarritoController {
	
	@Autowired
	private ProductoCarritoRepository productoCarritoRepositoryDAO;
	
	@CrossOrigin
	@RequestMapping(path = "/addProductoCarrito", method = RequestMethod.POST)
	public @ResponseBody String addProductoCarrito
	(@RequestParam Long idCarrito, @RequestParam Long idProducto) {
		ProductoCarrito productoCarrito = new ProductoCarrito();
		productoCarrito.setIdCarrito(idCarrito);
		productoCarrito.setIdProducto(idProducto);
		productoCarritoRepositoryDAO.save(productoCarrito);
		return "Producto de carrito agregado.";
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getProductoByCarrito")
	public Iterable<ProductoCarrito> getProductoByCarrito(@RequestParam Long idCarrito){
		return productoCarritoRepositoryDAO.findByIdCarrito(idCarrito);
	}

	@CrossOrigin
	@RequestMapping(path = "/getCarritoByProducto")
	public Iterable<ProductoCarrito> getCarritoByProducto(@RequestParam Long idProducto){
		return productoCarritoRepositoryDAO.findByIdProducto(idProducto);
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getAllProductoCarrito")
	public Iterable<ProductoCarrito> getAllProductoCarrito(){
		return productoCarritoRepositoryDAO.findAll();
	}
	
	@CrossOrigin
	@RequestMapping(path = "/deleteProductoCarrito")
	public String deleteProductoCarrito(@RequestParam Long idProductoCarrito) {
		productoCarritoRepositoryDAO.deleteById(idProductoCarrito);
		return "Producto de carrito eliminado.";
	}
	
}
