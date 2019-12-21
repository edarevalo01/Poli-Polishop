package com.polishop.services;

import java.io.File;
import java.util.ArrayList;
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
import com.polishop.negocio.Respuesta;
import com.polishop.negocio.VendedorNegocio;
import com.polishop.repositories.VendedorRepository;

@RestController
public class VendedorController {
	
	@Autowired
	private VendedorRepository vendedorRepositoryDAO;
	
	@Value("${ruta.files.images}")
	private String upload_folder;
	
	@CrossOrigin
	@RequestMapping(path = "/addVendedor", method = RequestMethod.POST)
	public @ResponseBody Respuesta addVendedor
	(@RequestParam String nombres, @RequestParam String apellidos, @RequestParam String correo, 
			@RequestParam String contrasena, @RequestParam String descripcion, @RequestParam String pais, 
			@RequestParam String ciudad,  @RequestParam("urlFoto") MultipartFile urlFoto, @RequestParam String celular) {
		try {
			Optional<Vendedor> optVendedor = vendedorRepositoryDAO.findByCorreo(correo);
			if(optVendedor.isPresent()) {
				return new Respuesta(Respuesta.FAIL, "El vendedor ya se encuentra registrado.");
			}
			GuardarImagenes img = new GuardarImagenes();
			String nombreImagen = correo.replaceAll("[^a-zA-Z0-9]", "");
			String pathFile = img.cargaArchivos(urlFoto, upload_folder + "profilePhotos/sellers/", nombreImagen);
			if(pathFile.equals("fail")) {
				System.err.println("Error al guardar la foto del vendedor.");
			}
			String ruta = "assets/profilePhotos/sellers/" + nombreImagen + ".png";
			Vendedor vendedor = new Vendedor();
			vendedor.setNombres(nombres);
			vendedor.setApellidos(apellidos);
			vendedor.setCorreo(correo.toLowerCase());
			vendedor.setContrasena(contrasena);
			vendedor.setDescripcion(descripcion);
			vendedor.setPais(pais);
			vendedor.setCelular(celular);
			vendedor.setCiudad(ciudad);
			vendedor.setUrlFoto(ruta);
			vendedor.setPuntuacionVendedor(0L);
			vendedorRepositoryDAO.save(vendedor);
			return new Respuesta(Respuesta.OK, "Vendedor registrado.");
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

	@CrossOrigin
	@RequestMapping(path = "/getVendedorById", method = RequestMethod.GET)
	public Respuesta getVendedorById(@RequestParam Long idVendedor) {
		try {			
			Optional<Vendedor> optVendedor = vendedorRepositoryDAO.findById(idVendedor);
			if(!optVendedor.isPresent()) {
				return new Respuesta(Respuesta.FAIL, "El vendedor no existe.");
			}
			VendedorNegocio vendedor = new VendedorNegocio(optVendedor.get());
			return new Respuesta(Respuesta.OK, vendedor);
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getVendedorByCorreo", method = RequestMethod.GET)
	public Respuesta getVendedorByCorreo(@RequestParam String correo) {
		try {			
			Optional<Vendedor> optVendedor = vendedorRepositoryDAO.findByCorreo(correo.toLowerCase());
			if(!optVendedor.isPresent()) {
				return new Respuesta(Respuesta.FAIL, "El vendedor no existe.");
			}
			VendedorNegocio vendedor = new VendedorNegocio(optVendedor.get());
			return new Respuesta(Respuesta.OK, vendedor);
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}
	
	@CrossOrigin
	@RequestMapping(path = "/loginVendedor", method = RequestMethod.GET)
	public Respuesta getLoginVendedor(@RequestParam String correo, @RequestParam String contrasena) {
		Optional<Vendedor> optVendedor = vendedorRepositoryDAO.findByCorreo(correo.toLowerCase());
		if(!optVendedor.isPresent()) {
			return new Respuesta(Respuesta.FAIL, "Correo o contraseña incorrectos.");
		}
		if(optVendedor.get().getContrasena().equals(contrasena)) {
			return new Respuesta(Respuesta.OK, new VendedorNegocio(optVendedor.get()));
		}
		else {
			return new Respuesta(Respuesta.FAIL, "Correo o contraseña incorrectos.");
		}
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getAllVendedores", method = RequestMethod.GET)
	public Respuesta getAllVendedores() {
		try {
			Iterable<Vendedor> iterableVendedores = vendedorRepositoryDAO.findAll();
			ArrayList<VendedorNegocio> listaVendedores = new ArrayList<>();
			for(Vendedor vendedor: iterableVendedores) {
				listaVendedores.add(new VendedorNegocio(vendedor));
			}
			return new Respuesta(Respuesta.OK, listaVendedores);
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}
	
	@CrossOrigin
	@RequestMapping(path = "/deleteVendedor", method = RequestMethod.DELETE)
	public Respuesta deleteVendedor(@RequestParam Long idVendedor) {
		try {
			Optional<Vendedor> optVendedor = vendedorRepositoryDAO.findById(idVendedor);
			if(!optVendedor.isPresent()) {
				return new Respuesta(Respuesta.FAIL, "El vendedor no existe.");
			}
			File directorio = new File(upload_folder + optVendedor.get().getUrlFoto().substring(7));
			directorio.delete();
			vendedorRepositoryDAO.deleteById(idVendedor);
			return new Respuesta(Respuesta.OK, "Vendedor eliminado.");
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

}
