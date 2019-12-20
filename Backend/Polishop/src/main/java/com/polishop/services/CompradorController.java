package com.polishop.services;

import java.util.Optional;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.polishop.entities.Comprador;
import com.polishop.negocio.GuardarImagenes;
import com.polishop.negocio.Login;
import com.polishop.negocio.Respuesta;
import com.polishop.repositories.CompradorRepository;

@RestController
public class CompradorController {
	
	@Autowired
	private CompradorRepository compradorRepositoryDAO;
	
	@Value("${ruta.files.images}")
	private String upload_folder;
	
	@CrossOrigin
	@RequestMapping(path = "/addComprador", method = RequestMethod.POST)
	public @ResponseBody Respuesta addComprador
	(@RequestParam String nombres, @RequestParam String apellidos,
			@RequestParam String correo, @RequestParam String contrasena, @RequestParam String pais, 
			@RequestParam String ciudad, @RequestParam("urlFoto") MultipartFile urlFoto, @RequestParam Long puntuacion){
		try {
			Optional<Comprador> compradorOpt = compradorRepositoryDAO.findByCorreo(correo);
			if(compradorOpt.isPresent()) {
				return new Respuesta(Respuesta.FAIL, "El usuario ya existe.");
			}
			GuardarImagenes img = new GuardarImagenes();
			String nombreImagen = new StringTokenizer(correo, "@").nextToken().toLowerCase();
			String pathFile = img.cargaArchivos(urlFoto, upload_folder + "profilePhotos/users/", nombreImagen);
			if(pathFile.equals("fail")) {
				System.err.println("Error al guardar la foto");
			}
			String ruta = "assets/profilePhotos/users/"+nombreImagen+".png";
			Comprador comprador = new Comprador();
			comprador.setNombres(nombres);
			comprador.setApellidos(apellidos);
			comprador.setUsername(nombreImagen);
			comprador.setCorreo(correo.toLowerCase());
			comprador.setContrasena(contrasena);
			comprador.setPais(pais);
			comprador.setCiudad(ciudad);
			comprador.setUrlFoto(ruta);
			comprador.setPuntuacion(puntuacion);
			compradorRepositoryDAO.save(comprador);
			return new Respuesta(Respuesta.OK, "Usuario registrado satisfactoriamente.");
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getUsuarioById", method = RequestMethod.GET)
	public Respuesta getCompradorById(@RequestParam Long idComprador) {
		try {
			Optional<Comprador> optComprador = compradorRepositoryDAO.findById(idComprador);
			if(!optComprador.isPresent()) {
				return new Respuesta(Respuesta.FAIL, "El comprador no existe.");
			}
			Comprador comprador = optComprador.get();
			comprador.setContrasena(null);
			return new Respuesta(Respuesta.OK, comprador);	
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getUsuarioByCorreo", method = RequestMethod.GET)
	public Respuesta getCompradorByCorreo(@RequestParam String correo) {
		try {
			Optional<Comprador> optComprador = compradorRepositoryDAO.findByCorreo(correo.toLowerCase());
			if(!optComprador.isPresent()) {
				return new Respuesta(Respuesta.FAIL, "El comprador no existe.");
			}
			Comprador comprador = optComprador.get();
			comprador.setContrasena(null);
			return new Respuesta(Respuesta.OK, comprador);				
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}
	
	@CrossOrigin
	@RequestMapping(path = "/loginUsuario")
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
