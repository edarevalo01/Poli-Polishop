package com.polishop.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.polishop.entities.Carrito;
import com.polishop.repositories.CarritoRepository;

@RestController
public class CarritoController {
	
	@Autowired
	private CarritoRepository carritoRepositoryDAO;

	@CrossOrigin
	@RequestMapping(path = "/addCarrito", method = RequestMethod.POST)
	public @ResponseBody String addCarrito() {
		Carrito c = new Carrito();
		c.setFechaModificacion(new Date());
		carritoRepositoryDAO.save(c);
		return "Carrito guardado.";
	}
	
	@CrossOrigin
	@RequestMapping(path = "/updateCarrito", method = RequestMethod.POST)
	public @ResponseBody String updateCarrito(@RequestParam Long id) {
		Carrito c = carritoRepositoryDAO.findById(id).get();
		c.setFechaModificacion(new Date());
		carritoRepositoryDAO.save(c);
		return "Carrito guardado.";
	}
	
	@CrossOrigin
	@RequestMapping(path = "/deleteCarrito")
	public String deleteCarrito(@RequestParam Long idCarrito) {
		carritoRepositoryDAO.deleteById(idCarrito);
		return "Carrito eliminado.";
	}
	
}
