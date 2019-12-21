package com.polishop.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOG_ACTIVIDADES")
public class LogActividades {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/* Eliminar Usuario; Agregar Usuario; Eliminar Producto */
	private String nombreActividad;

	private String descripcion;

	/* ID + Nombre + Dependencia (42;Alejandro;admin) (32;Felipe;vendedor) */
	private String usuario;

	private Date fecha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreActividad() {
		return nombreActividad;
	}

	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
