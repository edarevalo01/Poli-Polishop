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

import com.polishop.entities.Comentario;
import com.polishop.entities.Comprador;
import com.polishop.entities.Producto;
import com.polishop.entities.Vendedor;
import com.polishop.negocio.ComentarioNegocio;
import com.polishop.negocio.Respuesta;
import com.polishop.repositories.ComentarioRepository;
import com.polishop.repositories.CompradorRepository;
import com.polishop.repositories.ProductoRepository;
import com.polishop.repositories.VendedorRepository;

@RestController
public class ComentarioController {

	@Autowired
	private ComentarioRepository comentarioRepositoryDAO;
	
	@Autowired
	private CompradorRepository compradorRepositoryDAO;
	
	@Autowired
	private ProductoRepository productoRepositoryDAO;
	
	@Autowired
	private VendedorRepository vendedorRespositoryDAO;
	
	@CrossOrigin
	@RequestMapping(path = "/addComentario", method = RequestMethod.POST)
	public @ResponseBody Respuesta addComentario
	(@RequestParam Long idComprador, @RequestParam Long idProducto, 
			@RequestParam String comentario, @RequestParam Long puntuacion) {
		try {
			Comentario ncomentario = new Comentario();
			ncomentario.setIdComprador(idComprador);
			ncomentario.setIdProducto(idProducto);
			ncomentario.setComentario(comentario);
			ncomentario.setFecha(new Date());
			ncomentario.setPuntuacion(puntuacion);
			comentarioRepositoryDAO.save(ncomentario);
			setCalificacion(idProducto);
			return new Respuesta(Respuesta.OK, "Comentario agregado.");
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

	@CrossOrigin
	@RequestMapping(path = "/getComentarioByProducto", method = RequestMethod.GET)
	public Respuesta getComentarioByProducto(@RequestParam Long idProducto){
		try {
			Iterable<Comentario> comentarioIterable = comentarioRepositoryDAO.findByIdProducto(idProducto); 
			ArrayList<ComentarioNegocio> comentarios = new ArrayList<ComentarioNegocio>();
			for(Comentario comentario: comentarioIterable) {
				ComentarioNegocio comentarioNegocio = new ComentarioNegocio();
				comentarioNegocio.setId(comentario.getId());
				Comprador comprador = compradorRepositoryDAO.findById(comentario.getIdComprador()).get();
				comentarioNegocio.setIdComprador(comprador.getId());
				comentarioNegocio.setNombreComprador(comprador.getNombres() + " " + comprador.getApellidos());
				comentarioNegocio.setImagenComprador(comprador.getUrlFoto());
				Producto producto = productoRepositoryDAO.findById(comentario.getIdProducto()).get();
				comentarioNegocio.setNombreProducto(producto.getNombre());
				comentarioNegocio.setComentario(comentario.getComentario());
				comentarioNegocio.setFecha(String.valueOf(comentario.getFecha()).substring(0,10));
				comentarioNegocio.setPuntuacionProducto(comentario.getPuntuacion());
				comentarios.add(comentarioNegocio);
			}
			return new Respuesta(Respuesta.OK, comentarios);
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

	@CrossOrigin
	@RequestMapping(path = "/deleteComentario", method = RequestMethod.DELETE)
	public Respuesta deleteComentario(@RequestParam Long idComentario) {
		try {
			Optional<Comentario> comentarioOpt = comentarioRepositoryDAO.findById(idComentario);
			if(!comentarioOpt.isPresent()) {
				return new Respuesta(Respuesta.FAIL, "El comentario no existe.");
			}
			comentarioRepositoryDAO.deleteById(idComentario);
			return new Respuesta(Respuesta.OK, "Comentario eliminado.");
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}
	
	@CrossOrigin
	@RequestMapping(path = "/modificarComentario", method = RequestMethod.PUT)
	public Respuesta modificarComentario(@RequestParam Long idComentario, @RequestParam String comentario) {
		try {
			Optional<Comentario> comentarioOpt = comentarioRepositoryDAO.findById(idComentario);
			if(!comentarioOpt.isPresent()) {
				return new Respuesta(Respuesta.FAIL, "El comentario no existe.");
			}
			Comentario coment = comentarioOpt.get();
			coment.setComentario(comentario);
			comentarioRepositoryDAO.save(coment);
			return new Respuesta(Respuesta.OK, "El comentario fue modificado.");
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}
	
	private void setCalificacion(Long idProducto) {
		setCalificacionProducto(idProducto);
	}
	
	private void setCalificacionProducto(Long idProducto) {
		Iterable<Comentario> comentariosTotales = comentarioRepositoryDAO.findByIdProducto(idProducto);
		double sum = 0, size = 0;
		for (Comentario comentario2 : comentariosTotales) {
			sum += comentario2.getPuntuacion();
			size++;
		}
		double res = sum / size;
		Producto producto = productoRepositoryDAO.findById(idProducto).get();  
		producto.setCalificacion(Math.round(res));
		productoRepositoryDAO.save(producto);
		setCalificacionVendedor(producto);
	}
	
	private void setCalificacionVendedor(Producto producto) {
		Iterable<Producto> productos = productoRepositoryDAO.findByIdVendedor(producto.getVendedor().getId());
		double sum = 0, size = 0;
		for(Producto prod: productos) {
			sum += prod.getCalificacion();
			size++;
		}
		double res = sum / size;
		Vendedor vendedor = vendedorRespositoryDAO.findById(producto.getVendedor().getId()).get();
		vendedor.setPuntuacionVendedor(Math.round(res));
		vendedorRespositoryDAO.save(vendedor);
	}

}
