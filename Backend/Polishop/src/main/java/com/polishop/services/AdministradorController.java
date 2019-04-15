package com.polishop.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.polishop.entities.Administrador;
import com.polishop.negocio.Login;
import com.polishop.repositories.AdministradorRepository;

@RestController
public class AdministradorController {

	@Autowired
	private AdministradorRepository administradorRepositoryDAO;

	@CrossOrigin
	@RequestMapping(path = "/addAdmin", method = RequestMethod.POST) 
	public @ResponseBody String addAdmin
	(@RequestParam String nombres, @RequestParam String apellidos, @RequestParam String correo, @RequestParam String contrasena) {
		Administrador administrador = new Administrador();
		administrador.setNombres(nombres);
		administrador.setApellidos(apellidos);
		administrador.setCorreo(correo.toLowerCase());
		administrador.setContrasena(contrasena);
		administradorRepositoryDAO.save(administrador);
		return "Administrador guardado.";
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getAdminByCorreo")
	public Administrador getAdminByCorreo(@RequestParam String correo) {
		Optional<Administrador> optAdmin = administradorRepositoryDAO.findByCorreo(correo.toLowerCase());
		if(!optAdmin.isPresent()) return null;
		return optAdmin.get();
	}
	
	@CrossOrigin
	@RequestMapping(path = "/loginAdmin")
	public Login getLoginAdmin(@RequestParam String correo) {
		Optional<Administrador> optAdmin = administradorRepositoryDAO.findByCorreo(correo.toLowerCase());
		if(!optAdmin.isPresent()) return null;
		Login login = new Login();
		login.setCorreo(correo.toLowerCase());
		login.setContrasena(optAdmin.get().getContrasena());
		return login;
	}
	
	@CrossOrigin
	@RequestMapping("/getAllAdmin")
	public Iterable<Administrador> getAllAdmin() {
		return administradorRepositoryDAO.findAll();
	}

}
