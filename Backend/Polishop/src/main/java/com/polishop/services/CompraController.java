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

import com.polishop.entities.Compra;
import com.polishop.entities.Producto;
import com.polishop.entities.ProductoCarrito;
import com.polishop.entities.Vendedor;
import com.polishop.negocio.ProductoCarritoNegocio;
import com.polishop.repositories.CompraRepository;
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
	private VendedorRepository vendedorRepositoryDAO;
	
	@Autowired
	private ProductoRepository productoRepositoryDAO;

	@CrossOrigin
	@RequestMapping(path = "/addCompra", method = RequestMethod.POST)
	public @ResponseBody String addCompra
	(@RequestParam Long idComprador, @RequestParam String pais, @RequestParam String departamento,
			@RequestParam String ciudad, @RequestParam String tipoDocumento, @RequestParam String numeroDocumento,
			@RequestParam String nombreDestinatario, @RequestParam String direccionEnvio, @RequestParam String observaciones,
			@RequestParam String telefonoUno, @RequestParam String telefonoDos, @RequestParam String metodoPago, @RequestParam Long idCarrito) {
		Date date = new Date();
		Compra compra = new Compra();
		compra.setNumeroPedido(date.getTime()+"-"+idComprador);
		compra.setIdComprador(idComprador);
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
		compra.setFechaCreacion(date);
		compra.setFechaEstimadaEntrega(date);
		compra.setMetodoPago(metodoPago);
		compra.setIdCarrito(idCarrito);
		compraRepositoryDAO.save(compra);
		return "Compra agregada.";
	}

	@CrossOrigin
	@RequestMapping(path = "/getByIdCompra")
	public Compra getByIdCompra(@RequestParam Long idCompra) {
		Optional<Compra> optCompra = compraRepositoryDAO.findById(idCompra);
		if(!optCompra.isPresent()) return null;
		return optCompra.get();
	}

	@CrossOrigin
	@RequestMapping(path = "/getByNumeroPedido")
	public Compra getByNumeroPedido(@RequestParam String numeroPedido) {
		Optional<Compra> optCompra = compraRepositoryDAO.findByNumeroPedido(numeroPedido);
		if(!optCompra.isPresent()) return null;
		return optCompra.get();
	}

	@CrossOrigin
	@RequestMapping(path = "/getByComprador")
	public Iterable<Compra> getByComprador(@RequestParam Long idComprador) {
		return compraRepositoryDAO.findByIdComprador(idComprador);
	}

	@CrossOrigin
	@RequestMapping(path = "/getByNumeroDocumento")
	public Iterable<Compra> getByNumeroDocumento(@RequestParam String numeroDocumento) {
		return compraRepositoryDAO.findByNumeroDocumento(numeroDocumento);
	}

	@CrossOrigin
	@RequestMapping(path = "/getByCarrito")
	public Compra getByCarrito(@RequestParam Long idCarrito) {
		Optional<Compra> optCompra = compraRepositoryDAO.findByIdCarrito(idCarrito);
		if(!optCompra.isPresent()) return null;
		return optCompra.get();
	}

	@CrossOrigin
	@RequestMapping(path = "/getAllCompras")
	public Iterable<Compra> getAllCompras(){
		return compraRepositoryDAO.findAll();
	}

	@CrossOrigin
	@RequestMapping(path = "/getProductosCarrito")
	public Iterable<ProductoCarritoNegocio> getProductosCarrito(@RequestParam Long idComprador){
		Optional<Compra> optCompra = compraRepositoryDAO.findByIdCompradorAndEstado(idComprador, "comprando");
		if(!optCompra.isPresent()) return new ArrayList<ProductoCarritoNegocio>();
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
			productosCarrito.add(pcn);
		}
		return productosCarrito;
	}

}
