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

import com.polishop.entities.LogError;
import com.polishop.repositories.LogErrorRepository;

@RestController
public class LogErrorController {
	
	@Autowired
	private LogErrorRepository logErrorRepositoryDAO;
	
	@CrossOrigin
	@RequestMapping(path = "/addLogError", method = RequestMethod.POST)
	public @ResponseBody String addLogError
	(@RequestParam String nombreError, @RequestParam String descripcion) {
		LogError logError = new LogError();
		logError.setNombreError(nombreError);
		logError.setDescripcion(descripcion);
		logError.setFecha(new Date());
		logErrorRepositoryDAO.save(logError);
		return "Log de Error agregado.";
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getLogErrorByNombreError")
	public LogError getLogErrorByNombreError(@RequestParam String nombreError) {
		Optional<LogError> optLogErr = logErrorRepositoryDAO.findByNombreError(nombreError);
		if(!optLogErr.isPresent()) return null;
		return optLogErr.get();
	}
	
	@CrossOrigin
	@RequestMapping(path = "/getAllLogError")
	public Iterable<LogError> getAllLogError(){
		return logErrorRepositoryDAO.findAll();
	}

}
