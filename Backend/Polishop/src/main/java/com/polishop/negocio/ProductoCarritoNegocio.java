package com.polishop.negocio;

public class ProductoCarritoNegocio {

	private Long idProducto;

	private String nombreProducto;

	private String nombreVendedor;

	private String valor;

	private Long cantidad;

	private String urlCarpetaImagenes;

	private Long idCarrito;

	private Long idCompra;

	private EstadosEnum estado;

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getNombreVendedor() {
		return nombreVendedor;
	}

	public void setNombreVendedor(String nombreVendedor) {
		this.nombreVendedor = nombreVendedor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public String getUrlCarpetaImagenes() {
		return urlCarpetaImagenes;
	}

	public void setUrlCarpetaImagenes(String urlCarpetaImagenes) {
		this.urlCarpetaImagenes = urlCarpetaImagenes;
	}

	public Long getIdCarrito() {
		return idCarrito;
	}

	public void setIdCarrito(Long idCarrito) {
		this.idCarrito = idCarrito;
	}

	public Long getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}

	public EstadosEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadosEnum estado) {
		this.estado = estado;
	}

}
