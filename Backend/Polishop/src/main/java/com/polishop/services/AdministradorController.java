package com.polishop.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.polishop.entities.Administrador;
import com.polishop.negocio.AdministradorNegocio;
import com.polishop.negocio.Respuesta;
import com.polishop.repositories.AdministradorRepository;

@RestController
public class AdministradorController {

	@Autowired
	private AdministradorRepository administradorRepositoryDAO;

	@CrossOrigin
	@RequestMapping(path = "/addAdmin", method = RequestMethod.POST) 
	public @ResponseBody Respuesta addAdmin
	(@RequestParam String nombres, @RequestParam String apellidos, @RequestParam String correo, @RequestParam String contrasena) {
		Respuesta respuesta = new Respuesta();
		try {
			Optional<Administrador> optAdmin = administradorRepositoryDAO.findByCorreo(correo);
			if(optAdmin.isPresent()) {
				respuesta.setStatus(Respuesta.FAIL);
				respuesta.setMensaje("El administrador ya se encuentra registrado.");
				return respuesta;
			}
			else {
				Administrador administrador = new Administrador();
				administrador.setNombres(nombres);
				administrador.setApellidos(apellidos);
				administrador.setCorreo(correo.toLowerCase());
				administrador.setContrasena(contrasena);
				administradorRepositoryDAO.save(administrador);
				respuesta.setStatus(Respuesta.OK);
				respuesta.setMensaje("Administrador agregado satisfactoriamente.");
				return respuesta;
			}
		} catch (Exception e) {
			respuesta.setStatus(Respuesta.FAIL);
			respuesta.setMensaje(e);
			return respuesta;
		}
	}

	@CrossOrigin
	@RequestMapping(path = "/getAdminByCorreo", method = RequestMethod.GET)
	public Respuesta getAdminByCorreo(@RequestParam String correo) {
		try {
			Optional<Administrador> optAdmin = administradorRepositoryDAO.findByCorreo(correo.toLowerCase());
			if(!optAdmin.isPresent()) {
				return new Respuesta(Respuesta.FAIL, "El administrador no se encuenta registrado.");
			}
			AdministradorNegocio admin = new AdministradorNegocio(optAdmin.get());
			return new Respuesta(Respuesta.OK, admin);	
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}

	}

	@CrossOrigin
	@RequestMapping(path = "/loginAdmin", method = RequestMethod.GET)
	public Respuesta getLoginAdmin(@RequestParam String correo, @RequestParam String contrasena) {
		Optional<Administrador> optAdmin = administradorRepositoryDAO.findByCorreo(correo.toLowerCase());
		if(!optAdmin.isPresent()) {
			return new Respuesta(Respuesta.FAIL, "Correo o contraseña incorrectos.");
		}
		if(optAdmin.get().getContrasena().equals(contrasena)) {
			return new Respuesta(Respuesta.OK, new AdministradorNegocio(optAdmin.get()));
		}
		else {
			return new Respuesta(Respuesta.FAIL, "Correo o contraseña incorrectos.");
		}
	}

	@CrossOrigin
	@RequestMapping(path = "/getAllAdmin", method = RequestMethod.GET)
	public Respuesta getAllAdmin() {
		try {
			ArrayList<AdministradorNegocio> listaAdmin = new ArrayList<>();
			Iterable<Administrador> iterableAdmin = administradorRepositoryDAO.findAll();
			for(Administrador adm: iterableAdmin) {
				listaAdmin.add(new AdministradorNegocio(adm));
			}
			return new Respuesta(Respuesta.OK, listaAdmin);			
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

	@CrossOrigin
	@RequestMapping(path = "/deleteAdmin", method = RequestMethod.DELETE)
	public Respuesta deteleAdmin(@RequestParam Long idAdmin) {
		try {
			Optional<Administrador> optAdmin = administradorRepositoryDAO.findById(idAdmin);
			if(!optAdmin.isPresent()) {
				return new Respuesta(Respuesta.FAIL, "El Administrador con id (" + idAdmin + ") no existe.");
			}
			administradorRepositoryDAO.deleteById(idAdmin);
			return new Respuesta(Respuesta.OK, "Administrador eliminado.");
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

}
