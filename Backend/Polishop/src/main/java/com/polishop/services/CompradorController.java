package com.polishop.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.polishop.entities.Comprador;
import com.polishop.negocio.Login;
import com.polishop.repositories.CompradorRepository;

@RestController
public class CompradorController {
	
	@Autowired
	private CompradorRepository compradorRepositoryDAO;
	
	@CrossOrigin
	@RequestMapping(path = "/addComprador", method = RequestMethod.POST)
	public @ResponseBody String addComprador
	(@RequestParam String nombres, @RequestParam String apellidos, @RequestParam String username,
			@RequestParam String correo, @RequestParam String contrasena, @RequestParam String pais, 
			@RequestParam String ciudad, @RequestParam String urlFoto, @RequestParam Long puntuacion){
		Comprador comprador = new Comprador();
		comprador.setNombres(nombres);
		comprador.setApellidos(apellidos);
		comprador.setUsername(username);
		comprador.setCorreo(correo.toLowerCase());
		comprador.setContrasena(contrasena);
		comprador.setPais(pais);
		comprador.setCiudad(ciudad);
		comprador.setUrlFoto(urlFoto);
		comprador.setPuntuacion(puntuacion);
		compradorRepositoryDAO.save(comprador);
		return "Comprador guardado.";
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getUsuarioByCorreo")
	public Comprador getCompradorByCorreo(@RequestParam String correo) {
		Optional<Comprador> optComprador = compradorRepositoryDAO.findByCorreo(correo.toLowerCase());
		if(!optComprador.isPresent()) return null;
		return optComprador.get();
	}
	
	@CrossOrigin
	@RequestMapping(path = "/loginUsuaio")
	public Login getLoginComprador(@RequestParam String correo) {
		Optional<Comprador> optComprador = compradorRepositoryDAO.findByCorreo(correo.toLowerCase());
		if(!optComprador.isPresent()) return null;
		Login login = new Login();
		login.setCorreo(correo.toLowerCase());
		login.setContrasena(optComprador.get().getContrasena());
		return login;
	}
	
	@CrossOrigin
	@RequestMapping("/getAllCompradores")
	public Iterable<Comprador> getAllCompradores() {
		return compradorRepositoryDAO.findAll();
	}

}
