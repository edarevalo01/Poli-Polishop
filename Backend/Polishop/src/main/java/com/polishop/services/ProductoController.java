package com.polishop.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.polishop.entities.Producto;
import com.polishop.entities.Vendedor;
import com.polishop.negocio.ProductoNegocio;
import com.polishop.repositories.ProductoRepository;
import com.polishop.repositories.VendedorRepository;

@RestController
public class ProductoController {
	
	@Autowired
	private ProductoRepository productoRepositoryDAO;
	
	@Autowired
	private VendedorRepository vendedorRepositoryDAO;
	
	@CrossOrigin
	@RequestMapping(path = "/addProducto", method = RequestMethod.POST)
	public @ResponseBody String addProducto
	(@RequestParam String nombre, @RequestParam String descripcion, @RequestParam String precio, 
			@RequestParam Long calificacion, @RequestParam String urlCarpetaImagenes, 
			@RequestParam String dependencia, @RequestParam Long idVendedor, @RequestParam Long idPropietario) {
		Producto producto = new Producto();
		producto.setNombre(nombre);
		producto.setDescripcion(descripcion);
		producto.setPrecio(precio);
		producto.setCalificacion(calificacion);
		producto.setFechaPublicacion(new Date());
		producto.setUrlCarpetaImagenes(urlCarpetaImagenes);
		producto.setDependencia(dependencia);
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
	
	@CrossOrigin
	@RequestMapping(path = "/getProductoById")
	public ProductoNegocio getProductoById(@RequestParam Long id) {
		Optional<Producto> optProducto = productoRepositoryDAO.findById(id);
		if(!optProducto.isPresent()) return null;
		Producto producto = optProducto.get();
		ProductoNegocio productoNegocio = new ProductoNegocio();
		productoNegocio.setId(producto.getId());
		productoNegocio.setNombre(producto.getNombre());
		productoNegocio.setDescripcion(producto.getDescripcion());
		productoNegocio.setPrecio(producto.getPrecio());
		productoNegocio.setCalificacion(producto.getCalificacion());
		productoNegocio.setFechaPublicacion(String.valueOf(producto.getFechaPublicacion()).substring(0,10));
		productoNegocio.setUrlCarpetaImagenes(producto.getUrlCarpetaImagenes());
		Vendedor vendedor = vendedorRepositoryDAO.findById(producto.getIdVendedor()).get();
		productoNegocio.setNombreVendedor(vendedor.getNombres() + " " + vendedor.getApellidos());
		productoNegocio.setDescripcionVendedor(vendedor.getDescripcion());
		productoNegocio.setImagenVendedor(vendedor.getUrlFoto());
		productoNegocio.setCalificacionVendedor(vendedor.getPuntuacionVendedor());
		productoNegocio.setDependencia(producto.getDependencia());
		productoNegocio.setIdPropietario(producto.getIdPropietario());
		return productoNegocio;
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getAllProductosByDependencia")
	public Iterable<ProductoNegocio> getAllProductosByDependencia(@RequestParam String dependencia){
		Iterable<Producto> productoIterable = productoRepositoryDAO.findByDependenciaOrderByCalificacionDesc(dependencia);
		int contador = 0;
		ArrayList<ProductoNegocio> productosNegocio = new ArrayList<ProductoNegocio>();
		for(Producto producto: productoIterable) {
			if(contador >= 9) break;
			ProductoNegocio newProductoNegocio = new ProductoNegocio();
			newProductoNegocio.setId(producto.getId());
			newProductoNegocio.setNombre(producto.getNombre());
			newProductoNegocio.setDescripcion(producto.getDescripcion());
			newProductoNegocio.setPrecio(producto.getPrecio());
			newProductoNegocio.setCalificacion(producto.getCalificacion());
			newProductoNegocio.setFechaPublicacion(String.valueOf(producto.getFechaPublicacion()).substring(0,10));
			newProductoNegocio.setUrlCarpetaImagenes(producto.getUrlCarpetaImagenes());
			Vendedor vendedor = vendedorRepositoryDAO.findById(producto.getIdVendedor()).get();
			newProductoNegocio.setNombreVendedor(vendedor.getNombres() + " " + vendedor.getApellidos());
			newProductoNegocio.setDescripcionVendedor(vendedor.getDescripcion());
			newProductoNegocio.setImagenVendedor(vendedor.getUrlFoto());
			newProductoNegocio.setCalificacionVendedor(vendedor.getPuntuacionVendedor());
			newProductoNegocio.setDependencia(producto.getDependencia());
			newProductoNegocio.setIdPropietario(producto.getIdPropietario());
			productosNegocio.add(newProductoNegocio);
			contador++;
		}
		return productosNegocio;
	}

}
