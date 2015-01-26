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
@Table(name="SINTOMA")
public class Sintoma {
	
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "SIN_CODIGO")
    private Integer idSintoma;
    
    @Column(nullable = true, name = "SIN_DESCRIPCION", length=40)
    private String descripcion;

	@OneToMany(mappedBy="sxtSintoma")
	private Collection<SintomaTerapia> sinSintomaTerapias;

	public Integer getIdSintoma() {
		return idSintoma;
	}

	public void setIdSintoma(Integer idSintoma) {
		this.idSintoma = idSintoma;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Collection<SintomaTerapia> getSinSintomaTerapias() {
		return sinSintomaTerapias;
	}

	public void setSinSintomaTerapias(Collection<SintomaTerapia> sinSintomaTerapias) {
		this.sinSintomaTerapias = sinSintomaTerapias;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idSintoma == null) ? 0 : idSintoma.hashCode());
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
		Sintoma other = (Sintoma) obj;
		if (idSintoma == null) {
			if (other.idSintoma != null)
				return false;
		} else if (!idSintoma.equals(other.idSintoma))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sintoma [idSintoma=" + idSintoma + ", descripcion="
				+ descripcion + ", sinSintomaTerapias=" + sinSintomaTerapias
				+ "]";
	}
	
}

