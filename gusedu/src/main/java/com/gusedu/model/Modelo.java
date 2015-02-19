package com.gusedu.model;

import java.util.Collection;

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

@Entity
@Table(name = "MODELO")
public class Modelo {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "MOD_CODIGO")
    private Integer idModelo;
	
    @Column(nullable = true, name = "MOD_NOMBRE", length=40)
    private String nombre;	
    
	@Column(nullable=true, name="MOD_COLOR", length=100)
	private String color;	
	
	@Column(nullable=true, name="MOD_SIZE", length=100)
	private String size;	
	
	@Column(nullable=true, name="MOD_YEAR_FAB", length=100)
	private String añoFabricacion;	
	
	@ManyToOne
	@JoinColumn(name = "MAR_CODIGO", nullable = false)
	private Marca modMarca;
	
	@OneToMany(mappedBy="proModelo", fetch = FetchType.EAGER)
	private Collection<Producto> modProductos;

	public Integer getIdModelo() {
		return idModelo;
	}

	public void setIdModelo(Integer idModelo) {
		this.idModelo = idModelo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getAñoFabricacion() {
		return añoFabricacion;
	}

	public void setAñoFabricacion(String añoFabricacion) {
		this.añoFabricacion = añoFabricacion;
	}

	public Marca getModMarca() {
		return modMarca;
	}

	public void setModMarca(Marca modMarca) {
		this.modMarca = modMarca;
	}

	public Collection<Producto> getModProductos() {
		return modProductos;
	}

	public void setModProductos(Collection<Producto> modProductos) {
		this.modProductos = modProductos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idModelo == null) ? 0 : idModelo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Modelo other = (Modelo) obj;
		if (idModelo == null) {
			if (other.idModelo != null)
				return false;
		} else if (!idModelo.equals(other.idModelo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Modelo [idModelo=" + idModelo + ", nombre=" + nombre
				+ ", color=" + color + ", size=" + size + ", añoFabricacion="
				+ añoFabricacion + "]";
	}
	
}
