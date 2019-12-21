package com.polishop.negocio;

import com.polishop.entities.Administrador;

public class AdministradorNegocio {

	private Long id;

	private String nombres;

	private String apellidos;

	private String correo;

	public AdministradorNegocio(Administrador administrador) {
		this.id = administrador.getId();
		this.nombres = administrador.getNombres();
		this.apellidos = administrador.getApellidos();
		this.correo = administrador.getCorreo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

}
