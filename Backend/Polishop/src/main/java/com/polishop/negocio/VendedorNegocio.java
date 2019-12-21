package com.polishop.negocio;

import com.polishop.entities.Vendedor;

public class VendedorNegocio {
	
	private Long id;
	
	private String nombres;
	
	private String apellidos;
	
	private String correo;
	
	private String descripcion;
	
	private String pais;
	
	private String ciudad;
	
	private String urlFoto;
	
	private String celular;
	
	private Long puntuacionVendedor;
	
	public VendedorNegocio(Vendedor vendedor) {
		this.id = vendedor.getId();
		this.nombres = vendedor.getNombres();
		this.apellidos = vendedor.getApellidos();
		this.correo = vendedor.getCorreo();
		this.descripcion = vendedor.getDescripcion();
		this.pais = vendedor.getPais();
		this.ciudad = vendedor.getCiudad();
		this.urlFoto = vendedor.getUrlFoto();
		this.celular = vendedor.getCelular();
		this.puntuacionVendedor = vendedor.getPuntuacionVendedor();
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Long getPuntuacionVendedor() {
		return puntuacionVendedor;
	}

	public void setPuntuacionVendedor(Long puntuacionVendedor) {
		this.puntuacionVendedor = puntuacionVendedor;
	}

}
