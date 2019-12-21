package com.polishop.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.polishop.entities.Compra;
import com.polishop.entities.Comprador;
import com.polishop.entities.Producto;
import com.polishop.entities.ProductoCarrito;
import com.polishop.entities.Vendedor;
import com.polishop.negocio.CompraHistorial;
import com.polishop.negocio.CompraNegocio;
import com.polishop.negocio.EstadosEnum;
import com.polishop.negocio.ProductoCarritoNegocio;
import com.polishop.negocio.Respuesta;
import com.polishop.repositories.CarritoRepository;
import com.polishop.repositories.CompraRepository;
import com.polishop.repositories.CompradorRepository;
import com.polishop.repositories.ProductoCarritoRepository;
import com.polishop.repositories.ProductoRepository;
import com.polishop.repositories.VendedorRepository;

@RestController
public class CompraController {

	@Autowired
	private CompraRepository compraRepositoryDAO;

	@Autowired
	private ProductoCarritoRepository productoCarritoRepositoryDAO;

	@Autowired
	private CarritoRepository carritoRepositoryDAO;

	@Autowired
	private VendedorRepository vendedorRepositoryDAO;

	@Autowired
	private ProductoRepository productoRepositoryDAO;

	@Autowired
	private CompradorRepository compradorRepositoryDAO;

	@CrossOrigin
	@RequestMapping(path = "/getByIdCompra", method = RequestMethod.GET)
	public Respuesta getByIdCompra(@RequestParam Long idCompra) {
		try {			
			Optional<Compra> optCompra = compraRepositoryDAO.findById(idCompra);
			if(!optCompra.isPresent()) {
				return new Respuesta(Respuesta.FAIL, "La compra no existe");
			}
			Compra compra = optCompra.get();
			compra.getComprador().setContrasena(null);
			return new Respuesta(Respuesta.OK, compra);
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

	@CrossOrigin
	@RequestMapping(path = "/getByNumeroPedido", method = RequestMethod.GET)
	public Respuesta getByNumeroPedido(@RequestParam String numeroPedido) {
		try {
			Optional<Compra> optCompra = compraRepositoryDAO.findByNumeroPedido(numeroPedido);
			if(!optCompra.isPresent()) {
				return new Respuesta(Respuesta.FAIL, "La compra no existe.");
			}
			Compra compra = optCompra.get();
			compra.getComprador().setContrasena(null);
			return new Respuesta(Respuesta.OK, compra);
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

	@CrossOrigin
	@RequestMapping(path = "/getByComprador", method = RequestMethod.GET)
	public Respuesta getByComprador(@RequestParam Long idComprador) {
		try {
			Optional<Comprador> optComprador = compradorRepositoryDAO.findById(idComprador);
			if(!optComprador.isPresent()) {
				return new Respuesta(Respuesta.FAIL, "El comprador no existe");
			}
			return new Respuesta(Respuesta.OK, compraRepositoryDAO.findByIdComprador(idComprador));
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

	@CrossOrigin
	@RequestMapping(path = "/getAllCompras", method = RequestMethod.GET)
	public Respuesta getAllCompras(){
		try {
			ArrayList<CompraNegocio> compras = new ArrayList<>();
			Iterable<Compra> listaCompras = compraRepositoryDAO.findAll();
			for(Compra compra: listaCompras) {
				Iterable<ProductoCarrito> carrito = productoCarritoRepositoryDAO.findByIdCarrito(compra.getIdCarrito());
				ArrayList<Producto> productos = new ArrayList<>();
				for(ProductoCarrito prodCar: carrito) {
					Producto producto = productoRepositoryDAO.findById(prodCar.getIdProducto()).get();
					producto.getVendedor().setContrasena(null);
					producto.getPropietario().setContrasena(null);
					productos.add(producto);
				}
				CompraNegocio compNeg = new CompraNegocio();
				compNeg.setId(compra.getId());
				compNeg.setNumeroPedido(compra.getNumeroPedido());
				compNeg.setIdComprador(compra.getIdComprador());
				compNeg.setPais(compra.getPais());
				compNeg.setDepartamento(compra.getDepartamento());
				compNeg.setCiudad(compra.getCiudad());
				compNeg.setTipoDocumento(compra.getTipoDocumento());
				compNeg.setNumeroDocumento(compra.getNumeroDocumento());
				compNeg.setNombreDestinatario(compra.getNombreDestinatario());
				compNeg.setDireccionEnvio(compra.getDireccionEnvio());
				compNeg.setObservaciones(compra.getObservaciones());
				compNeg.setTelefonoUno(compra.getTelefonoUno());
				compNeg.setTelefonoDos(compra.getTelefonoDos());
				compNeg.setFechaCreacion(compra.getFechaCreacion());
				compNeg.setFechaEstimadaEntrega(compra.getFechaEstimadaEntrega());
				compNeg.setMetodoPago(compra.getMetodoPago());
				compNeg.setEstado(compra.getEstado());
				compNeg.setProductos(productos);
				compras.add(compNeg);
			}
			return new Respuesta(Respuesta.OK, compras);
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

	@CrossOrigin
	@RequestMapping(path = "/getProductosCarrito", method = RequestMethod.GET)
	public Respuesta getProductosCarrito(@RequestParam Long idComprador){
		try {
			Optional<Compra> optCompra = compraRepositoryDAO.findByIdCompradorAndEstado(idComprador, EstadosEnum.comprando);
			if(!optCompra.isPresent()) {
				return new Respuesta(Respuesta.FAIL, "La compra no existe.");
			}
			Compra compra = optCompra.get();
			Iterable<ProductoCarrito> productosCarritoBD = productoCarritoRepositoryDAO.findByIdCarrito(compra.getIdCarrito());
			ArrayList<ProductoCarritoNegocio> productosCarrito = new ArrayList<ProductoCarritoNegocio>();
			for (ProductoCarrito productoCarrito : productosCarritoBD) {
				Producto producto = productoRepositoryDAO.findById(productoCarrito.getIdProducto()).get();
				Vendedor vendedor = vendedorRepositoryDAO.findById(producto.getIdVendedor()).get();
				ProductoCarritoNegocio pcn = new ProductoCarritoNegocio();
				pcn.setIdProducto(productoCarrito.getIdProducto());
				pcn.setNombreProducto(producto.getNombre());
				pcn.setNombreVendedor(vendedor.getNombres() + " " + vendedor.getApellidos());
				pcn.setValor(producto.getPrecio());
				pcn.setCantidad(productoCarrito.getCantidad());
				pcn.setUrlCarpetaImagenes(producto.getUrlCarpetaImagenes());
				pcn.setIdCarrito(productoCarrito.getIdCarrito());
				pcn.setIdCompra(compra.getId());
				pcn.setEstado(compra.getEstado());
				productosCarrito.add(pcn);
			}
			return new Respuesta(Respuesta.OK, productosCarrito);
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

	@CrossOrigin
	@RequestMapping(path="/getHistorial", method = RequestMethod.GET)
	public Respuesta getHistorial(@RequestParam Long idVendedor){
		try {
			ArrayList<CompraHistorial> compras = new ArrayList<CompraHistorial>();
			Iterable<Compra> listaCompras = compraRepositoryDAO.findAll();
			for(Compra compra: listaCompras) {
				Iterable<ProductoCarrito> carrito = productoCarritoRepositoryDAO.findByIdCarrito(compra.getIdCarrito());
				loop: for(ProductoCarrito prodCar: carrito) {
					Producto producto = productoRepositoryDAO.findById(prodCar.getIdProducto()).get();
					if(producto.getIdVendedor() == idVendedor) {
						if(compra.getEstado().equals(EstadosEnum.comprando)) {
							continue loop;
						}
						CompraHistorial n = new CompraHistorial();
						n.setNombreComprador(compra.getNombreDestinatario());
						n.setCiudadComprador(compra.getCiudad());
						n.setDireccionComprador(compra.getDireccionEnvio());
						n.setDocumentoComprador(compra.getNumeroDocumento());
						n.setNombreDestinatario(compra.getNombreDestinatario());
						n.setTelefono1(compra.getTelefonoUno());
						n.setTelefono2(compra.getTelefonoDos());
						n.setNombreProducto(producto.getNombre());
						n.setCantidad(prodCar.getCantidad());
						n.setEstadoCompra(compra.getEstado());
						n.setFechaCreacion(compra.getFechaCreacion());
						compras.add(n);
					}
				}
			}
			return new Respuesta(Respuesta.OK, compras);
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

	@CrossOrigin
	@RequestMapping(path="/realizarCompra", method = RequestMethod.PUT)
	public Respuesta realizarCompra
	(@RequestParam Long idComprador, @RequestParam String pais, @RequestParam String departamento,
			@RequestParam String ciudad, @RequestParam String tipoDocumento, @RequestParam String numeroDocumento,
			@RequestParam String nombreDestinatario, @RequestParam String direccionEnvio, @RequestParam String observaciones,
			@RequestParam String telefonoUno, @RequestParam String telefonoDos, @RequestParam String metodoPago) {
		try {
			Optional<Compra> optCompra = compraRepositoryDAO.findByIdCompradorAndEstado(idComprador, EstadosEnum.comprando);
			if(!optCompra.isPresent()) {
				return new Respuesta(Respuesta.FAIL, "La compra no existe.");
			}
			Compra compra = optCompra.get();
			compra.setPais(pais);
			compra.setDepartamento(departamento);
			compra.setCiudad(ciudad);
			compra.setTipoDocumento(tipoDocumento);
			compra.setNumeroDocumento(numeroDocumento);
			compra.setNombreDestinatario(nombreDestinatario);
			compra.setDireccionEnvio(direccionEnvio);
			compra.setObservaciones(observaciones);
			compra.setTelefonoUno(telefonoUno);
			compra.setTelefonoDos(telefonoDos);
			compra.setMetodoPago(metodoPago);
			compra.setEstado(EstadosEnum.pendiente);
			compraRepositoryDAO.save(compra);
			return new Respuesta(Respuesta.OK, "Compra realizada.");
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

	@CrossOrigin
	@RequestMapping(path="/eliminarCompra", method = RequestMethod.DELETE)
	public Respuesta eliminarCompra(@RequestParam Long idCompra) {
		try {
			Optional<Compra> compraOpt = compraRepositoryDAO.findById(idCompra);
			if(!compraOpt.isPresent()) {
				return new Respuesta(Respuesta.FAIL, "La compra no existe.");
			}
			Compra compra = compraOpt.get();
			Iterable<ProductoCarrito> productosCarrito = productoCarritoRepositoryDAO.findByIdCarrito(compra.getIdCarrito());
			for(ProductoCarrito pc: productosCarrito) {
				productoCarritoRepositoryDAO.deleteById(pc.getId());
			}
			carritoRepositoryDAO.deleteById(compra.getIdCarrito());
			compraRepositoryDAO.deleteById(idCompra);
			return new Respuesta("ok", "Compra eliminada");
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

}
