package com.gusedu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="SINTOMA_VISITA")
public class SintomaVisita {
	
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "SXV_CODIGO")
    private Integer idSintoma;	
    
    @Column(nullable = true, name = "SXV_DOLOR")
    private Integer dolor;
    
    @Column(nullable= true, name="SXV_DESCRIPCION")
    private String descripcion;    
    
    @ManyToOne @JoinColumn(name="VIS_CODIGO", nullable=false)
    private Visita sxvVisita;	
    
    @ManyToOne @JoinColumn(name="SIN_CODIGO", nullable=false)
    private Sintoma sxvSintoma;

	public Integer getIdSintoma() {
		return idSintoma;
	}

	public void setIdSintoma(Integer idSintoma) {
		this.idSintoma = idSintoma;
	}

	public Integer getDolor() {
		return dolor;
	}

	public void setDolor(Integer dolor) {
		this.dolor = dolor;
	}


	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Visita getSxvVisita() {
		return sxvVisita;
	}

	public void setSxvVisita(Visita sxvVisita) {
		this.sxvVisita = sxvVisita;
	}

	public Sintoma getSxvSintoma() {
		return sxvSintoma;
	}

	public void setSxvSintoma(Sintoma sxvSintoma) {
		this.sxvSintoma = sxvSintoma;
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
		SintomaVisita other = (SintomaVisita) obj;
		if (idSintoma == null) {
			if (other.idSintoma != null)
				return false;
		} else if (!idSintoma.equals(other.idSintoma))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SintomaTerapia [idSintoma=" + idSintoma + ", dolor=" + dolor
				+ ", sxtTerapia=" + sxvVisita + ", sxtSintoma=" + sxvSintoma
				+ "]";
	}


    
}
