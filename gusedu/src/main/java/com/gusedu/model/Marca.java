package com.gusedu.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MARCA")
public class Marca {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "MAR_CODIGO")
    private Integer idMarca;
	
    @Column(nullable = true, name = "MAR_NOMBRE", length=40)
    private String nombre;	
    
	@OneToMany(mappedBy="modMarca")
	private Collection<Modelo> marModelos;

	public Integer getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(Integer idMarca) {
		this.idMarca = idMarca;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Collection<Modelo> getMarModelos() {
		return marModelos;
	}

	public void setMarModelos(Collection<Modelo> marModelos) {
		this.marModelos = marModelos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idMarca == null) ? 0 : idMarca.hashCode());
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
		Marca other = (Marca) obj;
		if (idMarca == null) {
			if (other.idMarca != null)
				return false;
		} else if (!idMarca.equals(other.idMarca))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Marca [idMarca=" + idMarca + ", nombre=" + nombre + "]";
	}

}
