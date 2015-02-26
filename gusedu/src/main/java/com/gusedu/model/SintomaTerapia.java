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
@Table(name="SINTOMA_TERAPIA")
public class SintomaTerapia {
	
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "SXT_CODIGO")
    private Integer idSintoma;	
    
    @Column(nullable = true, name = "SXT_DOLOR")
    private Integer dolor;
    
    @Column(nullable= true, name="SXT_DESCRIPCION")
    private String descripcion;    
    
    @ManyToOne @JoinColumn(name="TER_CODIGO", nullable=false)
    private Terapia sxtTerapia;	
    
    @ManyToOne @JoinColumn(name="SIN_CODIGO", nullable=false)
    private Sintoma sxtSintoma;

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

	public Terapia getSxtTerapia() {
		return sxtTerapia;
	}

	public void setSxtTerapia(Terapia sxtTerapia) {
		this.sxtTerapia = sxtTerapia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Sintoma getSxtSintoma() {
		return sxtSintoma;
	}

	public void setSxtSintoma(Sintoma sxtSintoma) {
		this.sxtSintoma = sxtSintoma;
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
		SintomaTerapia other = (SintomaTerapia) obj;
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
				+ ", sxtTerapia=" + sxtTerapia + ", sxtSintoma=" + sxtSintoma
				+ "]";
	}	
    
}
