package com.polishop.services;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.polishop.entities.Vendedor;
import com.polishop.negocio.GuardarImagenes;
import com.polishop.negocio.Login;
import com.polishop.repositories.VendedorRepository;

@RestController
public class VendedorController {
	
	@Autowired
	private VendedorRepository vendedorRepositoryDAO;
	
	@Value("${ruta.files.images}")
	private String upload_folder;
	
	
	@CrossOrigin
	@RequestMapping(path = "/addVendedor", method = RequestMethod.POST)
	public @ResponseBody String addVendedor
	(@RequestParam String nombres, @RequestParam String apellidos, @RequestParam String correo, 
			@RequestParam String contrasena, @RequestParam String descripcion, @RequestParam String pais, 
			@RequestParam String ciudad,  @RequestParam("urlFoto") MultipartFile urlFoto, @RequestParam Long puntuacionVendedor) throws IOException{
		GuardarImagenes img = new GuardarImagenes();
		String nombreImagen = correo.replaceAll("[^a-zA-Z0-9]", "");
		String pathFile = img.cargaArchivos(urlFoto, upload_folder, nombreImagen);
		Vendedor vendedor = new Vendedor();
		vendedor.setNombres(nombres);
		vendedor.setApellidos(apellidos);
		vendedor.setCorreo(correo.toLowerCase());
		vendedor.setContrasena(contrasena);
		vendedor.setDescripcion(descripcion);
		vendedor.setPais(pais);
		vendedor.setCiudad(ciudad);
		vendedor.setUrlFoto(pathFile);
		vendedor.setPuntuacionVendedor(puntuacionVendedor);
		vendedorRepositoryDAO.save(vendedor);
		return "Vendedor guardado.";
	}

	@CrossOrigin
	@RequestMapping(path = "/getVendedorById")
	public Vendedor getVendedorById(@RequestParam Long id) {
		Optional<Vendedor> optVendedor = vendedorRepositoryDAO.findById(id);
		if(!optVendedor.isPresent()) return null;
		return optVendedor.get();
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
