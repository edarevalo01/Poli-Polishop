package com.polishop.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "PRODUCTO")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String nombre;
	
	@NotNull
	@Column(length = 3000) 
	private String descripcion;
	
	@NotNull
	private String precio;
	
	private Long calificacion;
	
	@NotNull
	private Date fechaPublicacion;
	
	private String urlCarpetaImagenes;
	
	@NotNull
	private Long idVendedor;
	
	private Long idPropietario;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "idVendedor", referencedColumnName = "id", insertable = false, updatable = false)
	private Vendedor vendedor; // Relacion - Tabla VENDEDOR
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "idPropietario", referencedColumnName = "id", insertable = false, updatable = false)
	private PropietarioNegocio propietario; // Relacion - Tabla PROPIETARIO_NEGOCIO
	
	@JsonBackReference
	@OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
	private List<Comentario> comentarios = new ArrayList<Comentario>(); //Relacion - Tabla COMENTARIO
	
	@JsonBackReference
	@OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
	private List<ProductoCarrito> productoCarrito = new ArrayList<ProductoCarrito>(); //Relacion - Tabla PRODUCTO_CARRITO

	@JsonBackReference
	@OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
	private List<CategoriaProducto> categoriaProducto = new ArrayList<CategoriaProducto>(); //Relacion - Tabla CATEGORIA_PRODUCTO

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

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getUrlCarpetaImagenes() {
		return urlCarpetaImagenes;
	}

	public void setUrlCarpetaImagenes(String urlCarpetaImagenes) {
		this.urlCarpetaImagenes = urlCarpetaImagenes;
	}

	public Long getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(Long idVendedor) {
		this.idVendedor = idVendedor;
	}

	public Long getIdPropietario() {
		return idPropietario;
	}

	public void setIdPropietario(Long idPropietario) {
		this.idPropietario = idPropietario;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public PropietarioNegocio getPropietario() {
		return propietario;
	}

	public void setPropietario(PropietarioNegocio propietario) {
		this.propietario = propietario;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public List<ProductoCarrito> getProductoCarrito() {
		return productoCarrito;
	}

	public void setProductoCarrito(List<ProductoCarrito> productoCarrito) {
		this.productoCarrito = productoCarrito;
	}

	public List<CategoriaProducto> getCategoriaProducto() {
		return categoriaProducto;
	}

	public void setCategoriaProducto(List<CategoriaProducto> categoriaProducto) {
		this.categoriaProducto = categoriaProducto;
	}
	
}
