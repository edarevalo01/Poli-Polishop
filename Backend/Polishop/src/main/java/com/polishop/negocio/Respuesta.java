package com.polishop.negocio;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Respuesta {

	public final static String OK = "ok";
	public final static String FAIL = "fail";

	private String status;
	private Object mensaje;

	public Respuesta() {
		this("","");
	}

	public Respuesta(String status, Object mensaje) {
		this.status = status;
		this.mensaje = mensaje;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getMensaje() {
		return mensaje;
	}

	public void setMensaje(Object mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (IOException ex) {
			return super.toString();
		}
	}
}
