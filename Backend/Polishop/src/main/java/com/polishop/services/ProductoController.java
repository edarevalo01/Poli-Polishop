package com.polishop.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.polishop.entities.Producto;
import com.polishop.entities.Vendedor;
import com.polishop.negocio.GuardarImagenes;
import com.polishop.negocio.ProductoNegocio;
import com.polishop.repositories.ProductoRepository;
import com.polishop.repositories.VendedorRepository;

@RestController
public class ProductoController {
	
	@Autowired
	private ProductoRepository productoRepositoryDAO;
	
	@Autowired
	private VendedorRepository vendedorRepositoryDAO;
	
	@Value("${ruta.files.images}")
	private String upload_folder;
	
	@CrossOrigin
	@RequestMapping(path = "/addProducto", method = RequestMethod.POST)
	public @ResponseBody String addProducto
	(@RequestParam String nombre, @RequestParam String descripcion, @RequestParam String precio,  
			@RequestParam String dependencia, @RequestParam Long idVendedor, @RequestParam Long idPropietario) {
		
		Producto producto = new Producto();
		producto.setNombre(nombre);
		producto.setDescripcion(descripcion);
		producto.setPrecio(precio);
		producto.setCalificacion(0L);
		producto.setFechaPublicacion(new Date());
		Vendedor vendedor = vendedorRepositoryDAO.findById(idVendedor).get();
		String nombreCarpeta = (nombre+"guid"+vendedor.getNombres()+vendedor.getApellidos()).replaceAll("[^a-zA-Z0-9]", ""); //Nombre compuesto por el nombre del producto + "guid" + el nombre del vendedor
		//File directorio = new File("c:\\apache-tomcat-8.5.43\\webapps\\polishop\\assets\\ImagenesPolishop\\Productos\\"+nombreCarpeta.toLowerCase()); //windows
		File directorio = new File("/opt/tomcat/webapps/polishop/assets/ImagenesPolishop/Productos/"+nombreCarpeta.toLowerCase()); //linux
		//apache-tomcat-8.5.43/webapps/polishop/assets/ImagenesPolishop/
		///opt/tomcat/webapps/polishop/assets/ImagenesPolishop/
		directorio.mkdir();
		producto.setUrlCarpetaImagenes("assets/ImagenesPolishop/Productos/"+nombreCarpeta.toLowerCase());
		producto.setDependencia(dependencia);
		producto.setIdVendedor(idVendedor);
		producto.setIdPropietario(idPropietario);
		productoRepositoryDAO.save(producto);
		return "{\"success\": \"" + producto.getId() +"\"}";
	}
	
	@CrossOrigin
	@RequestMapping(path = "/saveImagenesProducto")
	public @ResponseBody String saveImagenesProducto
	(@RequestParam Long idProducto, @RequestParam("imagenProducto") MultipartFile imagenProducto, @RequestParam String nombreImagen) throws IOException{
		Producto producto = productoRepositoryDAO.findById(idProducto).get();
		Vendedor vendedor = vendedorRepositoryDAO.findById(producto.getIdVendedor()).get();
		GuardarImagenes img = new GuardarImagenes();
		String nombreCarpeta = (producto.getNombre()+"guid"+vendedor.getNombres()+vendedor.getApellidos()).replaceAll("[^a-zA-Z0-9]", ""); //Nombre compuesto por el nombre del producto + "guid" + el nombre del vendedor
		String pathFolder = upload_folder + "/Productos/" + nombreCarpeta.toLowerCase() + "/";
		String pathFile = img.cargaArchivos(imagenProducto, pathFolder, nombreImagen);
		return pathFile;
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getProductosVendedor")
	public Iterable<ProductoNegocio> getProductosVendedor(@RequestParam Long idVendedor){
		Iterable<Producto> productoIterable = productoRepositoryDAO.findByIdVendedor(idVendedor);
		ArrayList<ProductoNegocio> productosNegocio = new ArrayList<ProductoNegocio>();
		for(Producto producto: productoIterable) {
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
		}
		return productosNegocio;
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getProductosByPropietario")
	public Iterable<Producto> getProductosByPropietario(@RequestParam Long idPropietario){
		return productoRepositoryDAO.findByIdPropietario(idPropietario);
	}
	
	@CrossOrigin
	@RequestMapping(path = "/deleteProducto")
	public String deleteProducto(@RequestParam Long idProducto) {
		Optional<Producto> prodOpt = productoRepositoryDAO.findById(idProducto);
		if(!prodOpt.isPresent()) return "{\"success\": \"Producto no encontrado\"}";
		productoRepositoryDAO.delete(prodOpt.get());
		return "{\"success\": \"Producto eliminado\"}";
	}
	
	@CrossOrigin
	@RequestMapping(path = "/updateProducto")
	public @ResponseBody String updateProducto(@RequestParam Long idProducto, @RequestParam String nombre, 
			@RequestParam String precio, @RequestParam String descripcion) {
		Optional<Producto> productoOpt = productoRepositoryDAO.findById(idProducto);
		if(!productoOpt.isPresent()) return "Producto no encontrado.";
		Producto newProducto = productoOpt.get();
		newProducto.setNombre(nombre);
		newProducto.setPrecio(precio);
		newProducto.setDescripcion(descripcion);
		productoRepositoryDAO.save(newProducto);
		return "{\"success\": \"Producto actualizado\"}";
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
	
	@CrossOrigin
	@RequestMapping(path = "/busquedaProducto")
	public Iterable<ProductoNegocio> busquedaProducto(@RequestParam String nombre){
		Iterable<Producto> productoIterable = productoRepositoryDAO.findByNombreContaining(nombre);
		ArrayList<ProductoNegocio> productosNegocio = new ArrayList<ProductoNegocio>();
		for(Producto producto: productoIterable) {
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
		}
		return productosNegocio;
	}

}
