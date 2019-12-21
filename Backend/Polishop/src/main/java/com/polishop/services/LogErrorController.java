package com.polishop.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.polishop.entities.LogError;
import com.polishop.negocio.Respuesta;
import com.polishop.repositories.LogErrorRepository;

@RestController
public class LogErrorController {

	@Autowired
	private LogErrorRepository logErrorRepositoryDAO;

	@CrossOrigin
	@RequestMapping(path = "/addLogError", method = RequestMethod.POST)
	public @ResponseBody Respuesta addLogError
	(@RequestParam String nombreError, @RequestParam String descripcion) {
		try {
			LogError logError = new LogError();
			logError.setNombreError(nombreError);
			logError.setDescripcion(descripcion);
			logError.setFecha(new Date());
			logErrorRepositoryDAO.save(logError);
			return new Respuesta(Respuesta.OK, "Error reportado.");
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

	@CrossOrigin
	@RequestMapping(path = "/getLogErrorByNombreError", method = RequestMethod.GET)
	public Respuesta getLogErrorByNombreError(@RequestParam String nombreError) {
		try {
			Iterable<LogError> listaLog = logErrorRepositoryDAO.findAllByNombreError(nombreError);
			return new Respuesta(Respuesta.OK, listaLog);
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

	@CrossOrigin
	@RequestMapping(path = "/getAllLogError", method = RequestMethod.GET)
	public Respuesta getAllLogError(){
		try {
			Iterable<LogError> listaLog = logErrorRepositoryDAO.findAll();
			return new Respuesta(Respuesta.OK, listaLog);
		} catch (Exception e) {
			return new Respuesta(Respuesta.FAIL, e);
		}
	}

}
