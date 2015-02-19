package com.gusedu.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "UNIDAD_MEDIDA")
public class UnidadMedida {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "UME_CODIGO")
    private Integer idUnidadMedida;
	
    @Column(nullable = true, name = "UME_DESCRIPCION", length=40)
    private String descripcion;	
    
	@OneToMany(mappedBy="proUnidadMedida")
	private Collection<Producto> umeProductos;

	public Integer getIdUnidadMedida() {
		return idUnidadMedida;
	}

	public void setIdUnidadMedida(Integer idUnidadMedida) {
		this.idUnidadMedida = idUnidadMedida;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Collection<Producto> getUmeProductos() {
		return umeProductos;
	}

	public void setUmeProductos(Collection<Producto> umeProductos) {
		this.umeProductos = umeProductos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idUnidadMedida == null) ? 0 : idUnidadMedida.hashCode());
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
		UnidadMedida other = (UnidadMedida) obj;
		if (idUnidadMedida == null) {
			if (other.idUnidadMedida != null)
				return false;
		} else if (!idUnidadMedida.equals(other.idUnidadMedida))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UnidadMedida [idUnidadMedida=" + idUnidadMedida
				+ ", descripcion=" + descripcion + "]";
	}	
	
}
