package com.polishop.negocio;

import com.polishop.entities.Comprador;

public class CompradorNegocio {

	private Long id;

	private String nombres;

	private String apellidos;

	private String username; 

	private String correo;

	private String pais;

	private String ciudad;

	private String urlFoto;

	private Long puntuacion;

	public CompradorNegocio(Comprador comprador) {
		this.id = comprador.getId();
		this.nombres = comprador.getNombres();
		this.apellidos = comprador.getApellidos();
		this.username = comprador.getUsername();
		this.correo = comprador.getCorreo();
		this.pais = comprador.getPais();
		this.ciudad = comprador.getCiudad();
		this.urlFoto = comprador.getUrlFoto();
		this.puntuacion = comprador.getPuntuacion();
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

	public Long getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Long puntuacion) {
		this.puntuacion = puntuacion;
	}

}
