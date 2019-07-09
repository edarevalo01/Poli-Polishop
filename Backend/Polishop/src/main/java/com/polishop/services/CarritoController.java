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
	public @ResponseBody String addCarrito() {
		Carrito c = new Carrito();
		c.setFechaModificacion(new Date());
		carritoRepositoryDAO.save(c);
		return "Carrito guardado." + c.getId();
	}
	
	@CrossOrigin
	@RequestMapping(path = "/updateCarrito", method = RequestMethod.POST)
	public @ResponseBody String updateCarrito(@RequestParam Long id) {
		Carrito c = carritoRepositoryDAO.findById(id).get();
		c.setFechaModificacion(new Date());
		carritoRepositoryDAO.save(c);
		return "Carrito guardado.";
	}
	
	@CrossOrigin
	@RequestMapping(path = "/deleteCarrito")
	public String deleteCarrito(@RequestParam Long idCarrito) {
		carritoRepositoryDAO.deleteById(idCarrito);
		return "Carrito eliminado.";
	}
	
	@CrossOrigin
	@RequestMapping(path = "/saveCarritoConCompra")
	public String saveCarritoConCompra(@RequestParam Long idProducto, @RequestParam Long idComprador, @RequestParam Long cantidad) {
		boolean nuevo = false;
		Optional<Compra> optCompra = compraRepositoryDAO.findByIdCompradorAndEstado(idComprador, "comprando");
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
			newCompra.setEstado("comprando");
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
		return nuevo? "Pedido creado" : "Pedido modificado";
	}
	
	@CrossOrigin
	@RequestMapping(path = "/eliminarProductoCarrito")
	public String eliminarProductoCarrito (@RequestParam Long idCarrito, @RequestParam Long idProducto) {
		Optional<ProductoCarrito> optPC = productoCarritoRepositoryDAO.findByIdProductoAndIdCarrito(idProducto, idCarrito);
		if(!optPC.isPresent()) return "El producto no existe en este carrito";
		productoCarritoRepositoryDAO.deleteById(optPC.get().getId());
		return "Producto Eliminado";
	}
	
}
