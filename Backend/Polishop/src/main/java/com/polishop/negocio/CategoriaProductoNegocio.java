package com.polishop.negocio;

import com.polishop.entities.CategoriaProducto;

public class CategoriaProductoNegocio {
	
	private Long idCategoriaProducto;
	
	private Long idCategoria;
	
	private Long idProducto;
	
	private String nombreCategoria;
	
	private String nombreProducto;

	public CategoriaProductoNegocio(CategoriaProducto cp) {
		this.idCategoriaProducto = cp.getId();
		this.idCategoria = cp.getIdCategoria();
		this.idProducto = cp.getIdProducto();
		this.nombreCategoria = cp.getCategoria().getNombre();
		this.nombreProducto = cp.getProducto().getNombre();
	}

	public Long getIdCategoriaProducto() {
		return idCategoriaProducto;
	}

	public void setIdCategoriaProducto(Long idCategoriaProducto) {
		this.idCategoriaProducto = idCategoriaProducto;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

}
