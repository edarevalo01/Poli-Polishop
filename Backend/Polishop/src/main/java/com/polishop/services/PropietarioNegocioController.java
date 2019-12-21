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
import com.polishop.negocio.Respuesta;
import com.polishop.repositories.PropietarioNegocioRepository;

@RestController
public class PropietarioNegocioController {
	
	@Autowired
	private PropietarioNegocioRepository propietarioNegocioRepositoryDAO;
	
	@CrossOrigin
	@RequestMapping(path = "/addPropietario", method = RequestMethod.POST)
	public @ResponseBody Respuesta addPropietario
	(@RequestParam String nombres, @RequestParam String apellidos,
			@RequestParam String correo, @RequestParam String contrasena) {
		try {
			Optional<PropietarioNegocio> optPropietarioNegocio = propietarioNegocioRepositoryDAO.findByCorreo(correo.toLowerCase());
			if(optPropietarioNegocio.isPresent()) {
				return new Respuesta(Respuesta.FAIL, "El usuario asociado a este correo ya existe.");
			}
			PropietarioNegocio propietarioNegocio = new PropietarioNegocio();
			propietarioNegocio.setNombres(nombres);
			propietarioNegocio.setApellidos(apellidos);
			propietarioNegocio.setCorreo(correo.toLowerCase());
			propietarioNegocio.setContrasena(contrasena);
			propietarioNegocioRepositoryDAO.save(propietarioNegocio);
			return new Respuesta(Respuesta.OK, "Propietario guardado.");
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getPropietarioByCorreo", method = RequestMethod.GET)
	public Respuesta getPropietarioByCorreo(@RequestParam String correo) {
		try {			
			Optional<PropietarioNegocio> optPropietarioNegocio = propietarioNegocioRepositoryDAO.findByCorreo(correo.toLowerCase());
			if(!optPropietarioNegocio.isPresent()) {
				return new Respuesta(Respuesta.FAIL, "El usuario asociado a este correo no existe");
			}
			PropietarioNegocio prop = optPropietarioNegocio.get();
			prop.setContrasena(null);
			return new Respuesta(Respuesta.OK, prop);
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getAllPropietarios", method = RequestMethod.GET)
	public Respuesta getAllPropietarios() {
		try {
			Iterable<PropietarioNegocio> listaPropietarios = propietarioNegocioRepositoryDAO.findAll();
			return new Respuesta(Respuesta.OK, listaPropietarios);
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

}
