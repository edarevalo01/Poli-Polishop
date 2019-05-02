package com.polishop.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.polishop.entities.Vendedor;
import com.polishop.negocio.Login;
import com.polishop.repositories.VendedorRepository;

@RestController
public class VendedorController {
	
	@Autowired
	private VendedorRepository vendedorRepositoryDAO;
	
	@CrossOrigin
	@RequestMapping(path = "/addVendedor", method = RequestMethod.POST)
	public @ResponseBody String addVendedor
	(@RequestParam String nombres, @RequestParam String apellidos, @RequestParam String correo, 
			@RequestParam String contrasena, @RequestParam String descripcion, @RequestParam String pais, 
			@RequestParam String ciudad,  @RequestParam String urlFoto, @RequestParam Long puntuacionVendedor){
		Vendedor vendedor = new Vendedor();
		vendedor.setNombres(nombres);
		vendedor.setApellidos(apellidos);
		vendedor.setCorreo(correo.toLowerCase());
		vendedor.setContrasena(contrasena);
		vendedor.setDescripcion(descripcion);
		vendedor.setPais(pais);
		vendedor.setCiudad(ciudad);
		vendedor.setUrlFoto(urlFoto);
		vendedor.setPuntuacionVendedor(puntuacionVendedor);
		vendedorRepositoryDAO.save(vendedor);
		return "Vendedor guardado.";
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getVendedorByCorreo")
	public Vendedor getVendedorByCorreo(@RequestParam String correo) {
		Optional<Vendedor> optVendedor = vendedorRepositoryDAO.findByCorreo(correo.toLowerCase());
		if(!optVendedor.isPresent()) return null;
		return optVendedor.get();
	}
	
	@CrossOrigin
	@RequestMapping(path = "/loginVendedor")
	public Login getLoginVendedor(@RequestParam String correo) {
		Optional<Vendedor> optVendedor = vendedorRepositoryDAO.findByCorreo(correo.toLowerCase());
		if(!optVendedor.isPresent()) return null;
		Login login = new Login();
		login.setCorreo(correo.toLowerCase());
		login.setContrasena(optVendedor.get().getContrasena());
		return login;
	}
	
	@CrossOrigin
	@RequestMapping("/getAllVendedores")
	public Iterable<Vendedor> getAllVendedores() {
		return vendedorRepositoryDAO.findAll();
	}

}
