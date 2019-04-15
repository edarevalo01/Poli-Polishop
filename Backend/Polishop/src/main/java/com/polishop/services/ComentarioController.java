package com.polishop.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.polishop.entities.Comentario;
import com.polishop.repositories.ComentarioRepository;

@RestController
public class ComentarioController {

	@Autowired
	private ComentarioRepository comentarioRepositoryDAO;
	
	@CrossOrigin
	@RequestMapping(path = "/addComentario", method = RequestMethod.POST)
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
		return "Comentario agregado.";
	}

	@CrossOrigin
	@RequestMapping(path = "/getComentarioByComprador")
	public Iterable<Comentario> getComentarioByComprador(@RequestParam Long idComprador){
		return comentarioRepositoryDAO.findByIdComprador(idComprador);
	}

	@CrossOrigin
	@RequestMapping(path = "/getComentarioByProducto")
	public Iterable<Comentario> getComentarioByProducto(@RequestParam Long idProducto){
		return comentarioRepositoryDAO.findByIdProducto(idProducto);
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
