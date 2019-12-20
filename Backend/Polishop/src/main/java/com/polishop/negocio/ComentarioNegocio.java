package com.polishop.negocio;

public class ComentarioNegocio {
	
	private Long id;
	
	private Long idComprador;
	
	private String nombreComprador;
	
	private String nombreProducto;
	
	private String comentario;
	
	private String fecha;
	
	private String imagenComprador;
	
	private Long puntuacionProducto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdComprador() {
		return idComprador;
	}

	public void setIdComprador(Long idComprador) {
		this.idComprador = idComprador;
	}

	public String getNombreComprador() {
		return nombreComprador;
	}

	public void setNombreComprador(String nombreComprador) {
		this.nombreComprador = nombreComprador;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getImagenComprador() {
		return imagenComprador;
	}

	public void setImagenComprador(String imagenComprador) {
		this.imagenComprador = imagenComprador;
	}

	public Long getPuntuacionProducto() {
		return puntuacionProducto;
	}

	public void setPuntuacionProducto(Long puntuacionProducto) {
		this.puntuacionProducto = puntuacionProducto;
	}

}
