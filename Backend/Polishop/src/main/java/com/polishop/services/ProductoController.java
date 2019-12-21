package com.polishop.services;

import java.io.File;
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
import com.polishop.negocio.Respuesta;
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
	public @ResponseBody Respuesta addProducto
	(@RequestParam String nombre, @RequestParam String descripcion, @RequestParam String precio,  
			@RequestParam String dependencia, @RequestParam Long idVendedor, @RequestParam Long idPropietario) {
		try {			
			Producto producto = new Producto();
			producto.setNombre(nombre);
			producto.setDescripcion(descripcion);
			producto.setPrecio(precio);
			producto.setCalificacion(5L);
			producto.setFechaPublicacion(new Date());
			Vendedor vendedor = vendedorRepositoryDAO.findById(idVendedor).get();
			String nombreCarpeta = (nombre + "guid" + vendedor.getNombres() + vendedor.getApellidos()).replaceAll("[^a-zA-Z0-9]", "");
			String folder = upload_folder.replaceAll("/", "\\\\");
			File directorio = new File(folder + "ImagenesPolishop\\Productos\\" + nombreCarpeta.toLowerCase()); //windows
			//			File directorio = new File(upload_folder + "ImagenesPolishop/Productos/" + nombreCarpeta.toLowerCase()); //linux
			directorio.mkdir();
			producto.setUrlCarpetaImagenes("assets/ImagenesPolishop/Productos/" + nombreCarpeta.toLowerCase());
			producto.setDependencia(dependencia);
			producto.setIdVendedor(idVendedor);
			producto.setIdPropietario(idPropietario);
			producto.setCantidadImagenes(0L);
			productoRepositoryDAO.save(producto);
			return new Respuesta(Respuesta.OK, "Producto guardado, por favor agregue las imágenes");
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

	@CrossOrigin
	@RequestMapping(path = "/saveImagenesProducto", method = RequestMethod.POST)
	public @ResponseBody Respuesta saveImagenesProducto
	(@RequestParam Long idProducto, @RequestParam("imagenProducto") MultipartFile imagenProducto, @RequestParam String nombreImagen) {
		try {			
			Producto producto = productoRepositoryDAO.findById(idProducto).get();
			Vendedor vendedor = vendedorRepositoryDAO.findById(producto.getIdVendedor()).get();
			GuardarImagenes img = new GuardarImagenes();
			String nombreCarpeta = (producto.getNombre() + "guid" + vendedor.getNombres() + vendedor.getApellidos()).replaceAll("[^a-zA-Z0-9]", "");
			String pathFolder = upload_folder + "ImagenesPolishop/Productos/" + nombreCarpeta.toLowerCase() + "/";
			String pathFile = img.cargaArchivos(imagenProducto, pathFolder, nombreImagen);
			if(pathFile.equals("fail")) {
				return new Respuesta(Respuesta.FAIL, "Error al cargar la imagen: " + nombreImagen);
			}
			producto.setCantidadImagenes(producto.getCantidadImagenes() + 1);
			productoRepositoryDAO.save(producto);
			return new Respuesta(Respuesta.OK, pathFile);
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

	@CrossOrigin
	@RequestMapping(path = "/getProductosVendedor", method = RequestMethod.GET)
	public Respuesta getProductosVendedor(@RequestParam Long idVendedor){
		try {
			Iterable<Producto> productoIterable = productoRepositoryDAO.findByIdVendedor(idVendedor);
			ArrayList<ProductoNegocio> listaProductos = getProductosDepurados(productoIterable);
			return new Respuesta(Respuesta.OK, listaProductos);			
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

	@CrossOrigin
	@RequestMapping(path = "/deleteProducto", method = RequestMethod.DELETE)
	public Respuesta deleteProducto(@RequestParam Long idProducto) {
		try {			
			Optional<Producto> prodOpt = productoRepositoryDAO.findById(idProducto);
			if(!prodOpt.isPresent()) {
				return new Respuesta(Respuesta.FAIL, "Producto no encontrado");
			}
			File directorio = null;
			for (int i = 1; i <= prodOpt.get().getCantidadImagenes(); i++) {				
				directorio = new File(upload_folder + prodOpt.get().getUrlCarpetaImagenes().substring(7) + "/p" + i + ".png");
				directorio.delete();
			}
			directorio = new File(upload_folder + prodOpt.get().getUrlCarpetaImagenes().substring(7));
			directorio.delete();
			productoRepositoryDAO.delete(prodOpt.get());
			return new Respuesta(Respuesta.OK, "Producto eliminado");
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

	@CrossOrigin
	@RequestMapping(path = "/updateProducto", method = RequestMethod.PUT)
	public @ResponseBody Respuesta updateProducto(@RequestParam Long idProducto, @RequestParam String nombre, 
			@RequestParam String precio, @RequestParam String descripcion) {
		try {			
			Optional<Producto> productoOpt = productoRepositoryDAO.findById(idProducto);
			if(!productoOpt.isPresent()) {
				return new Respuesta(Respuesta.FAIL, "Producto no encontrado.");
			}
			Producto newProducto = productoOpt.get();
			newProducto.setNombre(nombre);
			newProducto.setPrecio(precio);
			newProducto.setDescripcion(descripcion);
			productoRepositoryDAO.save(newProducto);
			return new Respuesta(Respuesta.OK, "Información de producto modificada.");
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

	@CrossOrigin
	@RequestMapping(path = "/getProductosByCalificacion", method = RequestMethod.GET)
	public Respuesta getProductosByCalificacion(@RequestParam Long calificacion){
		try {
			Iterable<Producto> productoIterable = productoRepositoryDAO.findByCalificacion(calificacion);
			ArrayList<ProductoNegocio> listaProductos = getProductosDepurados(productoIterable);
			return new Respuesta(Respuesta.OK, listaProductos);
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

	@CrossOrigin
	@RequestMapping(path = "/getAllProductos", method = RequestMethod.GET)
	public Respuesta getAllProductos(){
		try {
			Iterable<Producto> productoIterable = productoRepositoryDAO.findAll();
			ArrayList<ProductoNegocio> listaProductos = getProductosDepurados(productoIterable);
			return new Respuesta(Respuesta.OK, listaProductos);
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

	@CrossOrigin
	@RequestMapping(path = "/getProductoById", method = RequestMethod.GET)
	public Respuesta getProductoById(@RequestParam Long idProducto) {
		try {			
			Optional<Producto> optProducto = productoRepositoryDAO.findById(idProducto);
			if(!optProducto.isPresent()) {
				return new Respuesta(Respuesta.FAIL, "El producto no existe.");
			}
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
			return new Respuesta(Respuesta.OK, productoNegocio);
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

	@CrossOrigin
	@RequestMapping(path = "/getAllProductosByDependencia", method = RequestMethod.GET)
	public Respuesta getAllProductosByDependencia(@RequestParam String dependencia){
		try {			
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
			return new Respuesta(Respuesta.OK, productosNegocio);
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

	@CrossOrigin
	@RequestMapping(path = "/busquedaProducto", method = RequestMethod.GET)
	public Respuesta busquedaProducto(@RequestParam String nombre){
		try {			
			Iterable<Producto> productoIterable = productoRepositoryDAO.findByNombreContaining(nombre);
			ArrayList<ProductoNegocio> listaProductos = getProductosDepurados(productoIterable);
			return new Respuesta(Respuesta.OK, listaProductos);
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}
	
	private ArrayList<ProductoNegocio> getProductosDepurados(Iterable<Producto> productoIterable){
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
			newProductoNegocio.setCantidadImagenes(producto.getCantidadImagenes());
			productosNegocio.add(newProductoNegocio);
		}
		return productosNegocio;
	}

}
