package com.polishop.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.polishop.entities.LogActividades;
import com.polishop.negocio.Respuesta;
import com.polishop.repositories.LogActividadesRepository;

@RestController
public class LogActividadesController {
	
	@Autowired
	private LogActividadesRepository logActividadesRepositoryDAO;
	
	@CrossOrigin
	@RequestMapping(path = "/addLogActividades", method = RequestMethod.POST)
	public @ResponseBody Respuesta addLogActividades
	(@RequestParam String nombreActividad, @RequestParam String descripcion, @RequestParam String usuario) {
		try {			
			LogActividades logActividades = new LogActividades();
			logActividades.setNombreActividad(nombreActividad);
			logActividades.setDescripcion(descripcion);
			logActividades.setUsuario(usuario);
			logActividades.setFecha(new Date());
			logActividadesRepositoryDAO.save(logActividades);
			return new Respuesta(Respuesta.OK, "Log agregado por: " + usuario);
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getLogActividadByNombre", method = RequestMethod.GET)
	public Respuesta getLogActividadByNombre(@RequestParam String nombreActividad) {
		try {
			Iterable<LogActividades> listaLog = logActividadesRepositoryDAO.findAllByNombreActividad(nombreActividad);
			return new Respuesta(Respuesta.OK, listaLog);
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getAllLogActividades", method = RequestMethod.GET)
	public Respuesta getAllActividades(){
		try {
			Iterable<LogActividades> listaLog = logActividadesRepositoryDAO.findAll();
			return new Respuesta(Respuesta.OK, listaLog);
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

}
