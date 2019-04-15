package com.polishop.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.polishop.entities.Producto;
import com.polishop.repositories.ProductoRepository;

@RestController
public class ProductoController {
	
	@Autowired
	private ProductoRepository productoRepositoryDAO;
	
	@CrossOrigin
	@RequestMapping(path = "/addProducto", method = RequestMethod.POST)
	public @ResponseBody String addProducto
	(@RequestParam String nombre, @RequestParam String descripcion, @RequestParam String precio, 
			@RequestParam Long calificacion, @RequestParam String urlCarpetaImagenes, 
			@RequestParam Long idVendedor, @RequestParam Long idPropietario) {
		Producto producto = new Producto();
		producto.setNombre(nombre);
		producto.setDescripcion(descripcion);
		producto.setPrecio(precio);
		producto.setCalificacion(calificacion);
		producto.setFechaPublicacion(new Date());
		producto.setUrlCarpetaImagenes(urlCarpetaImagenes);
		producto.setIdVendedor(idVendedor);
		producto.setIdPropietario(idPropietario);
		productoRepositoryDAO.save(producto);
		return "Producto agregado.";
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getProductosVendedor")
	public Iterable<Producto> getProductosVendedor(@RequestParam Long idVendedor){
		return productoRepositoryDAO.findByIdVendedor(idVendedor);
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getProductosByPropietario")
	public Iterable<Producto> getProductosByPropietario(@RequestParam Long idPropietario){
		return productoRepositoryDAO.findByIdPropietario(idPropietario);
	}
	
	@CrossOrigin
	@RequestMapping(path = "/deleteProducto")
	public String deleteProducto(@RequestParam Long idProducto) {
		productoRepositoryDAO.deleteById(idProducto);
		return "Producto eliminado.";
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getProductosByCalificacion")
	public Iterable<Producto> getProductosByCalificacion(@RequestParam Long calificacion){
		return productoRepositoryDAO.findByCalificacion(calificacion);
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getAllProductos")
	public Iterable<Producto> getAllProductos(){
		return productoRepositoryDAO.findAll();
	}

}
