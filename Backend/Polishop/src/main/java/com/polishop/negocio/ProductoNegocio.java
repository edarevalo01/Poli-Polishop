package com.polishop.negocio;

/**
 * @author Alejandro
 * Clase para gestionar información de productos
 */
public class ProductoNegocio {
	
	private Long id;
	
	private String nombre;
	 
	private String descripcion;
	
	private String precio;
	
	private Long calificacion;
	
	private String fechaPublicacion;
	
	private String urlCarpetaImagenes;
	
	private String nombreVendedor;
	
	private String descripcionVendedor;
	
	private String imagenVendedor;
	
	private Long calificacionVendedor;
	
	private String dependencia;
	
	private Long idPropietario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public Long getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Long calificacion) {
		this.calificacion = calificacion;
	}

	public String getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(String fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getUrlCarpetaImagenes() {
		return urlCarpetaImagenes;
	}

	public void setUrlCarpetaImagenes(String urlCarpetaImagenes) {
		this.urlCarpetaImagenes = urlCarpetaImagenes;
	}

	public String getNombreVendedor() {
		return nombreVendedor;
	}

	public void setNombreVendedor(String nombreVendedor) {
		this.nombreVendedor = nombreVendedor;
	}

	public String getDescripcionVendedor() {
		return descripcionVendedor;
	}

	public void setDescripcionVendedor(String descripcionVendedor) {
		this.descripcionVendedor = descripcionVendedor;
	}

	public String getImagenVendedor() {
		return imagenVendedor;
	}

	public void setImagenVendedor(String imagenVendedor) {
		this.imagenVendedor = imagenVendedor;
	}

	public Long getCalificacionVendedor() {
		return calificacionVendedor;
	}

	public void setCalificacionVendedor(Long calificacionVendedor) {
		this.calificacionVendedor = calificacionVendedor;
	}

	public Long getIdPropietario() {
		return idPropietario;
	}

	public void setIdPropietario(Long idPropietario) {
		this.idPropietario = idPropietario;
	}

	public String getDependencia() {
		return dependencia;
	}

	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}
	
}
