package com.polishop.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "COMPRA")
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String numeroPedido; //fecha+idcompra+idusuario
	
	private Long idComprador;
	
	@NotNull
	private String pais;
	
	private String departamento;
	
	@NotNull
	private String ciudad;
	
	@NotNull
	private String tipoDocumento;
	
	@NotNull
	private String numeroDocumento;
	
	@NotNull
	private String nombreDestinatario;
	
	@NotNull
	private String direccionEnvio;
	
	private String observaciones; //Ej. Torre 5, apartamento 4, barrio chapinero...
	
	@NotNull
	private String telefonoUno;
	
	private String telefonoDos;
	
	@NotNull
	private Date fechaCreacion;
	
	private Date fechaEstimadaEntrega;
	
	private String metodoPago;
	
	private Long idCarrito;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "idComprador", referencedColumnName = "id", insertable = false, updatable = false)
	private Comprador comprador;
	
	@JsonManagedReference
	@OneToOne
	@JoinColumn(name = "idCarrito", referencedColumnName = "id", insertable = false, updatable = false)
	private Carrito carrito;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public Long getIdComprador() {
		return idComprador;
	}

	public void setIdComprador(Long idComprador) {
		this.idComprador = idComprador;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNombreDestinatario() {
		return nombreDestinatario;
	}

	public void setNombreDestinatario(String nombreDestinatario) {
		this.nombreDestinatario = nombreDestinatario;
	}

	public String getDireccionEnvio() {
		return direccionEnvio;
	}

	public void setDireccionEnvio(String direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getTelefonoUno() {
		return telefonoUno;
	}

	public void setTelefonoUno(String telefonoUno) {
		this.telefonoUno = telefonoUno;
	}

	public String getTelefonoDos() {
		return telefonoDos;
	}

	public void setTelefonoDos(String telefonoDos) {
		this.telefonoDos = telefonoDos;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaEstimadaEntrega() {
		return fechaEstimadaEntrega;
	}

	public void setFechaEstimadaEntrega(Date fechaEstimadaEntrega) {
		this.fechaEstimadaEntrega = fechaEstimadaEntrega;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public Long getIdCarrito() {
		return idCarrito;
	}

	public void setIdCarrito(Long idCarrito) {
		this.idCarrito = idCarrito;
	}

	public Comprador getComprador() {
		return comprador;
	}

	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}

	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

}
