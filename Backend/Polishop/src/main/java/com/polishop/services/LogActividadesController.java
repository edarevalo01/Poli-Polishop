package com.polishop.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.polishop.entities.LogActividades;
import com.polishop.repositories.LogActividadesRepository;

@RestController
public class LogActividadesController {
	
	@Autowired
	private LogActividadesRepository logActividadesRepositoryDAO;
	
	@CrossOrigin
	@RequestMapping(path = "/addLogActividades", method = RequestMethod.POST)
	public @ResponseBody String addLogActividades
	(@RequestParam String nombreActividad, @RequestParam String descripcion, @RequestParam String usuario) {
		LogActividades logActividades = new LogActividades();
		logActividades.setNombreActividad(nombreActividad);
		logActividades.setDescripcion(descripcion);
		logActividades.setUsuario(usuario);
		logActividades.setFecha(new Date());
		logActividadesRepositoryDAO.save(logActividades);
		return "Log de Actividad agregado.";
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getLogActividadByNombre")
	public LogActividades getLogActividadByNombre(@RequestParam String nombreActividad) {
		Optional<LogActividades> optLogAct = logActividadesRepositoryDAO.findByNombreActividad(nombreActividad);
		if(!optLogAct.isPresent()) return null;
		return optLogAct.get();
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getAllLogActividades")
	public Iterable<LogActividades> getAllActividades(){
		return logActividadesRepositoryDAO.findAll();
	}

}
