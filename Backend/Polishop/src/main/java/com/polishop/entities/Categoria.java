package com.polishop.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "CATEGORIA")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String nombre; 
	
	@NotNull
	private String descripcion;
	
	@JsonBackReference
	@OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY, orphanRemoval = true)
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

	public List<CategoriaProducto> getCategoriaProducto() {
		return categoriaProducto;
	}

	public void setCategoriaProducto(List<CategoriaProducto> categoriaProducto) {
		this.categoriaProducto = categoriaProducto;
	}

}
