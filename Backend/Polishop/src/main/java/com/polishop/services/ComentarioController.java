package com.polishop.services;

import java.util.ArrayList;
import java.util.Date;

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
import com.polishop.negocio.ComentarioNegocio;
import com.polishop.repositories.ComentarioRepository;
import com.polishop.repositories.CompradorRepository;
import com.polishop.repositories.ProductoRepository;

@RestController
public class ComentarioController {

	@Autowired
	private ComentarioRepository comentarioRepositoryDAO;
	
	@Autowired
	private CompradorRepository compradorRepositoryDAO;
	
	@Autowired
	private ProductoRepository productoRepositoryDAO;
	
	@CrossOrigin
	@RequestMapping(path = "/addComentario")
	public @ResponseBody String addComentario
	(@RequestParam Long idComprador, @RequestParam Long idProducto, 
			@RequestParam String comentario, @RequestParam Long puntuacion) {
		Comentario ncomentario = new Comentario();
		ncomentario.setIdComprador(idComprador);
		ncomentario.setIdProducto(idProducto);
		ncomentario.setComentario(comentario);
		ncomentario.setFecha(new Date());
		ncomentario.setPuntuacion(puntuacion);
		comentarioRepositoryDAO.save(ncomentario);
		Iterable<Comentario> comentariosTotales = comentarioRepositoryDAO.findByIdProducto(idProducto);
		double sum = 0, size = 0;
		for (Comentario comentario2 : comentariosTotales) {
			sum += comentario2.getPuntuacion();
			size++;
		}
		double res = sum / size;
		System.out.println(res + " - " + sum + " - " + size);
		Producto producto = productoRepositoryDAO.findById(idProducto).get();  
		producto.setCalificacion(Math.round(res));
		productoRepositoryDAO.save(producto);
		return "{\"success\": \"Producto Agregado\"}";
	}

	@CrossOrigin
	@RequestMapping(path = "/getComentarioByComprador")
	public Iterable<Comentario> getComentarioByComprador(@RequestParam Long idComprador){
		return comentarioRepositoryDAO.findByIdComprador(idComprador);
	}

	@CrossOrigin
	@RequestMapping(path = "/getComentarioByProducto")
	public Iterable<ComentarioNegocio> getComentarioByProducto(@RequestParam Long idProducto){
		Iterable<Comentario> comentarioIterable = comentarioRepositoryDAO.findByIdProducto(idProducto); 
		ArrayList<ComentarioNegocio> comentarios = new ArrayList<ComentarioNegocio>();
		for(Comentario comentario: comentarioIterable) {
			ComentarioNegocio comentarioNegocio = new ComentarioNegocio();
			comentarioNegocio.setId(comentario.getId());
			Comprador comprador = compradorRepositoryDAO.findById(comentario.getIdComprador()).get();
			comentarioNegocio.setNombreComprador(comprador.getNombres() + " " + comprador.getApellidos());
			comentarioNegocio.setImagenComprador(comprador.getUrlFoto());
			Producto producto = productoRepositoryDAO.findById(comentario.getIdProducto()).get();
			comentarioNegocio.setNombreProducto(producto.getNombre());
			comentarioNegocio.setComentario(comentario.getComentario());
			comentarioNegocio.setFecha(String.valueOf(comentario.getFecha()).substring(0,10));
			comentarioNegocio.setPuntuacionProducto(comentario.getPuntuacion());
			comentarios.add(comentarioNegocio);
		}
		return comentarios;
	}

	@CrossOrigin
	@RequestMapping(path = "/getAllComentarios")
	public Iterable<Comentario> getAllComentarios(){
		return comentarioRepositoryDAO.findAll();
	}

	@CrossOrigin
	@RequestMapping(path = "/deleteComentario")
	public String deleteComentario(@RequestParam Long idComentario) {
		comentarioRepositoryDAO.deleteById(idComentario);
		return "Comentario eliminado.";
	}

}
