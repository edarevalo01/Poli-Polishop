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

import com.polishop.entities.ProductoCarrito;
import com.polishop.negocio.ProductoNegocio;
import com.polishop.negocio.Respuesta;
import com.polishop.repositories.ProductoCarritoRepository;

@RestController
public class ProductoCarritoController {

	@Autowired
	private ProductoCarritoRepository productoCarritoRepositoryDAO;

	@CrossOrigin
	@RequestMapping(path = "/addProductoCarrito", method = RequestMethod.POST)
	public @ResponseBody Respuesta addProductoCarrito
	(@RequestParam Long idCarrito, @RequestParam Long idProducto) {
		try {
			ProductoCarrito productoCarrito = new ProductoCarrito();
			productoCarrito.setIdCarrito(idCarrito);
			productoCarrito.setIdProducto(idProducto);
			productoCarrito.setCantidad(1L);
			productoCarritoRepositoryDAO.save(productoCarrito);
			return new Respuesta(Respuesta.OK, "Producto agregado al carrito de compras.");
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

	@CrossOrigin
	@RequestMapping(path = "/getProductoByCarrito", method = RequestMethod.GET)
	public Respuesta getProductoByCarrito(@RequestParam Long idCarrito){
		try {
			Iterable<ProductoCarrito> listaProductosPorCarrito = productoCarritoRepositoryDAO.findByIdCarrito(idCarrito);
			ArrayList<ProductoNegocio> listaProductos = new ArrayList<>();
			for(ProductoCarrito pc: listaProductosPorCarrito) {
				ProductoNegocio pn = new ProductoNegocio();
				pn.setId(pc.getId());
				pn.setNombre(pc.getProducto().getNombre());
				pn.setDescripcion(pc.getProducto().getDescripcion());
				pn.setPrecio(pc.getProducto().getPrecio());
				pn.setCalificacion(pc.getProducto().getCalificacion());
				pn.setFechaPublicacion(String.valueOf(pc.getProducto().getFechaPublicacion()));
				pn.setUrlCarpetaImagenes(pc.getProducto().getUrlCarpetaImagenes());
				pn.setNombreVendedor(pc.getProducto().getVendedor().getNombres());
				pn.setDescripcionVendedor(pc.getProducto().getVendedor().getDescripcion());
				pn.setImagenVendedor(pc.getProducto().getVendedor().getUrlFoto());
				pn.setCalificacionVendedor(pc.getProducto().getVendedor().getPuntuacionVendedor());
				pn.setDependencia(pc.getProducto().getDependencia());
				pn.setIdPropietario(pc.getProducto().getIdPropietario());
				listaProductos.add(pn);
			}
			return new Respuesta(Respuesta.OK, listaProductos);
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

	@CrossOrigin
	@RequestMapping(path = "/eliminarProductoCarrito", method = RequestMethod.DELETE)
	public Respuesta eliminarProductoCarrito (@RequestParam Long idCarrito, @RequestParam Long idProducto) {
		Optional<ProductoCarrito> optPC = productoCarritoRepositoryDAO.findByIdProductoAndIdCarrito(idProducto, idCarrito);
		if(!optPC.isPresent()) {
			return new Respuesta(Respuesta.FAIL, "El producto no existe en este carrito.");
		}
		productoCarritoRepositoryDAO.deleteById(optPC.get().getId());
		return new Respuesta(Respuesta.OK, "El producto ha sido eliminado del carrito de compras.");
	}

}
