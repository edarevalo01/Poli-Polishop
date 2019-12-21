package com.polishop.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "CARRITO")
public class Carrito {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date fechaModificacion;
	
	@JsonBackReference
	@OneToOne(mappedBy = "carrito", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Compra compra;  //Relacion - Tabla COMPRA
	
	@JsonBackReference
	@OneToMany(mappedBy = "carrito", fetch = FetchType.LAZY, orphanRemoval = true)
	private List<ProductoCarrito> productoCarrito = new ArrayList<ProductoCarrito>(); //Relacion - Tabla PRODUCTO_CARRITO

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public List<ProductoCarrito> getProductoCarrito() {
		return productoCarrito;
	}

	public void setProductoCarrito(List<ProductoCarrito> productoCarrito) {
		this.productoCarrito = productoCarrito;
	}

}
