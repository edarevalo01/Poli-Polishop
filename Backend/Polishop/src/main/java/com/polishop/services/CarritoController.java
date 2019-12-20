package com.polishop.services;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.polishop.entities.Carrito;
import com.polishop.entities.Compra;
import com.polishop.entities.ProductoCarrito;
import com.polishop.negocio.EstadosEnum;
import com.polishop.negocio.Respuesta;
import com.polishop.repositories.CarritoRepository;
import com.polishop.repositories.CompraRepository;
import com.polishop.repositories.ProductoCarritoRepository;

@RestController
public class CarritoController {
	
	@Autowired
	private CarritoRepository carritoRepositoryDAO;
	
	@Autowired
	private CompraRepository compraRepositoryDAO;
	
	@Autowired
	private ProductoCarritoRepository productoCarritoRepositoryDAO;

	@CrossOrigin
	@RequestMapping(path = "/addCarrito", method = RequestMethod.POST)
	public @ResponseBody Respuesta addCarrito() {
		try {			
			Carrito carrito = new Carrito();
			carrito.setFechaModificacion(new Date());
			carritoRepositoryDAO.save(carrito);
			return new Respuesta(Respuesta.OK, "Carrito creado.");
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}
	
	@CrossOrigin
	@RequestMapping(path = "/updateCarrito", method = RequestMethod.PUT)
	public @ResponseBody Respuesta updateCarrito(@RequestParam Long id) {
		try {
			Carrito c = carritoRepositoryDAO.findById(id).get();
			c.setFechaModificacion(new Date());
			carritoRepositoryDAO.save(c);
			return new Respuesta(Respuesta.OK, "Carrito actualizado.");			
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}
	
	@CrossOrigin
	@RequestMapping(path = "/deleteCarrito", method = RequestMethod.DELETE)
	public Respuesta deleteCarrito(@RequestParam Long idCarrito) {
		try {
			Optional<Carrito> optCarrito = carritoRepositoryDAO.findById(idCarrito);
			if(!optCarrito.isPresent()) {
				return new Respuesta(Respuesta.FAIL, "El carrito no existe.");
			}
			carritoRepositoryDAO.deleteById(idCarrito);
			return new Respuesta(Respuesta.OK, "Carrito eliminado.");
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}
	
	@CrossOrigin
	@RequestMapping(path = "/saveCarritoConCompra", method = RequestMethod.PUT)
	public Respuesta saveCarritoConCompra(@RequestParam Long idProducto, @RequestParam Long idComprador, @RequestParam Long cantidad) {
		boolean nuevo = false;
		Optional<Compra> optCompra = compraRepositoryDAO.findByIdCompradorAndEstado(idComprador, EstadosEnum.comprando);
		if(!optCompra.isPresent()) {
			nuevo = true;
			
			Date date = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, 5); // Adding 5 days
			
			Carrito newCarrito = new Carrito();
			newCarrito.setFechaModificacion(new Date());
			carritoRepositoryDAO.save(newCarrito);
			
			Compra newCompra = new Compra();
			newCompra.setNumeroPedido(date.getTime()+"-"+idComprador);
			newCompra.setIdComprador(idComprador);
			newCompra.setPais("");
			newCompra.setDepartamento("");
			newCompra.setCiudad("");
			newCompra.setTipoDocumento("");
			newCompra.setNumeroDocumento("");
			newCompra.setNombreDestinatario("");
			newCompra.setDireccionEnvio("");
			newCompra.setObservaciones("");
			newCompra.setTelefonoUno("");
			newCompra.setTelefonoDos("");
			newCompra.setFechaCreacion(date);
			newCompra.setFechaEstimadaEntrega(calendar.getTime());
			newCompra.setMetodoPago("");
			newCompra.setIdCarrito(newCarrito.getId());
			newCompra.setEstado(EstadosEnum.comprando);
			compraRepositoryDAO.save(newCompra);
			
			ProductoCarrito newProductoCarrito = new ProductoCarrito();
			newProductoCarrito.setIdCarrito(newCarrito.getId());
			newProductoCarrito.setIdProducto(idProducto);
			newProductoCarrito.setCantidad(1L);
			productoCarritoRepositoryDAO.save(newProductoCarrito);
		}
		else {
			Compra compra = optCompra.get();
			Optional<ProductoCarrito> optPC = productoCarritoRepositoryDAO.findByIdProductoAndIdCarrito(idProducto, compra.getIdCarrito());
			if(optPC.isPresent()) {
				ProductoCarrito newProductoCarrito = optPC.get();
				newProductoCarrito.setCantidad(newProductoCarrito.getCantidad()+cantidad);
				productoCarritoRepositoryDAO.save(newProductoCarrito);	
			}
			else {
				ProductoCarrito newProductoCarrito = new ProductoCarrito();
				newProductoCarrito.setIdProducto(idProducto);
				newProductoCarrito.setIdCarrito(compra.getIdCarrito());
				newProductoCarrito.setCantidad(1L);
				productoCarritoRepositoryDAO.save(newProductoCarrito);
			}
		}
		if(nuevo) {
			return new Respuesta(Respuesta.OK, "Pedido creado.");
		}
		else {			
			return new Respuesta(Respuesta.OK, "Pedido modificado.");
		}
	}
	
	@CrossOrigin
	@RequestMapping(path="/addInfoCompra", method = RequestMethod.PUT)
	public Respuesta addInfoCompra(
			@RequestParam Long idProducto, @RequestParam Long idComprador, @RequestParam String pais,
			@RequestParam String departamento, @RequestParam String ciudad, @RequestParam String numeroDocumento,
			@RequestParam String nombreDestinatario, @RequestParam String direccionEnvio, @RequestParam String observaciones,
			@RequestParam String telUno, @RequestParam String telDos) {
		Optional<Compra> optCompra = compraRepositoryDAO.findByIdCompradorAndEstado(idComprador, EstadosEnum.comprando);
		if(optCompra.isPresent()) {
			Compra newCompra = optCompra.get();
			newCompra.setPais(pais);
			newCompra.setDepartamento(departamento);
			newCompra.setCiudad(ciudad);
			newCompra.setTipoDocumento("Cédula de ciudadanía");
			newCompra.setNumeroDocumento(numeroDocumento);
			newCompra.setNombreDestinatario(nombreDestinatario);
			newCompra.setDireccionEnvio(direccionEnvio);
			newCompra.setObservaciones(observaciones);
			newCompra.setTelefonoUno(telUno);
			newCompra.setTelefonoDos(telDos);
			newCompra.setMetodoPago("no-reply");
			newCompra.setEstado(EstadosEnum.pendiente);
			compraRepositoryDAO.save(newCompra);
			return new Respuesta(Respuesta.OK, "Información agregada.");
		}
		return new Respuesta(Respuesta.FAIL, "La compra no existe.");
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
