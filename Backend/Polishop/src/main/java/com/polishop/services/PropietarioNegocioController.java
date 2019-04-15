package com.polishop.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.polishop.entities.PropietarioNegocio;
import com.polishop.negocio.Login;
import com.polishop.repositories.PropietarioNegocioRepository;

@RestController
public class PropietarioNegocioController {
	
	@Autowired
	private PropietarioNegocioRepository propietarioNegocioRepositoryDAO;
	
	@CrossOrigin
	@RequestMapping(path = "/addPropietario", method = RequestMethod.POST)
	public @ResponseBody String addPropietario
	(@RequestParam String nombres, @RequestParam String apellidos,
			@RequestParam String correo, @RequestParam String contrasena){
		PropietarioNegocio propietarioNegocio = new PropietarioNegocio();
		propietarioNegocio.setNombres(nombres);
		propietarioNegocio.setApellidos(apellidos);
		propietarioNegocio.setCorreo(correo.toLowerCase());
		propietarioNegocio.setContrasena(contrasena);
		propietarioNegocioRepositoryDAO.save(propietarioNegocio);
		return "Propietario guardado.";
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getPropietarioByCorreo")
	public PropietarioNegocio getPropietarioByCorreo(@RequestParam String correo) {
		Optional<PropietarioNegocio> optPropietarioNegocio = propietarioNegocioRepositoryDAO.findByCorreo(correo.toLowerCase());
		if(!optPropietarioNegocio.isPresent()) return null;
		return optPropietarioNegocio.get();
	}
	
	@CrossOrigin
	@RequestMapping(path = "/loginPropietario")
	public Login getLoginPropietario(@RequestParam String correo) {
		Optional<PropietarioNegocio> optPropietarioNegocio = propietarioNegocioRepositoryDAO.findByCorreo(correo.toLowerCase());
		if(!optPropietarioNegocio.isPresent()) return null;
		Login login = new Login();
		login.setCorreo(correo.toLowerCase());
		login.setContrasena(optPropietarioNegocio.get().getContrasena());
		return login;
	}
	
	@CrossOrigin
	@RequestMapping("/getAllPropietarios")
	public Iterable<PropietarioNegocio> getAllPropietarios() {
		return propietarioNegocioRepositoryDAO.findAll();
	}

}
