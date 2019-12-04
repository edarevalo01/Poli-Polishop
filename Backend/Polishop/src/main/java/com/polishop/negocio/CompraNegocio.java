package com.polishop.negocio;

import java.util.Date;

public class CompraNegocio {
	
	private String nombreComprador;
	
	private String ciudadComprador;
	
	private String direccionComprador;
	
	private String documentoComprador;
	
	private String nombreDestinatario;
	
	private String telefono1;
	
	private String telefono2;
	
	private String nombreProducto;
	
	private Long cantidad;
	
	private Date fechaCreacion;
	
	private String estadoCompra;

	public String getNombreComprador() {
		return nombreComprador;
	}

	public void setNombreComprador(String nombreComprador) {
		this.nombreComprador = nombreComprador;
	}

	public String getCiudadComprador() {
		return ciudadComprador;
	}

	public void setCiudadComprador(String ciudadComprador) {
		this.ciudadComprador = ciudadComprador;
	}

	public String getDireccionComprador() {
		return direccionComprador;
	}

	public void setDireccionComprador(String direccionComprador) {
		this.direccionComprador = direccionComprador;
	}

	public String getDocumentoComprador() {
		return documentoComprador;
	}

	public void setDocumentoComprador(String documentoComprador) {
		this.documentoComprador = documentoComprador;
	}

	public String getNombreDestinatario() {
		return nombreDestinatario;
	}

	public void setNombreDestinatario(String nombreDestinatario) {
		this.nombreDestinatario = nombreDestinatario;
	}

	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public String getEstadoCompra() {
		return estadoCompra;
	}

	public void setEstadoCompra(String estadoCompra) {
		this.estadoCompra = estadoCompra;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
}
