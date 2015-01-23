package com.gusedu.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="LLAMADA")
public class Llamada {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "LLA_CODIGO")
    private Integer idLlamada;
    
    @Column(nullable = true, name = "LLA_CONTESTADA")
    private Boolean esContestada;
    
    @Column(nullable = true, name = "LLA_DETALLE", length=300)
    private String detalle;
    
    @Column(nullable = true, name = "LLA_FEC_REALIZADA")
    private Date fechaRealizada;
    
    @Column(nullable = true, name = "LLA_USU_CREACION", length=100)
    private String usuarioCreacion;
    
    @ManyToOne @JoinColumn(name="PER_CODIGO", nullable=false)
    private Persona llaPersona;

	public Integer getIdLlamada() {
		return idLlamada;
	}

	public void setIdLlamada(Integer idLlamada) {
		this.idLlamada = idLlamada;
	}

	public Boolean getEsContestada() {
		return esContestada;
	}

	public void setEsContestada(Boolean esContestada) {
		this.esContestada = esContestada;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Date getFechaRealizada() {
		return fechaRealizada;
	}

	public void setFechaRealizada(Date fechaRealizada) {
		this.fechaRealizada = fechaRealizada;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Persona getLlaPersona() {
		return llaPersona;
	}

	public void setLlaPersona(Persona llaPersona) {
		this.llaPersona = llaPersona;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idLlamada == null) ? 0 : idLlamada.hashCode());
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
		Llamada other = (Llamada) obj;
		if (idLlamada == null) {
			if (other.idLlamada != null)
				return false;
		} else if (!idLlamada.equals(other.idLlamada))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Llamada [idLlamada=" + idLlamada + ", esContestada="
				+ esContestada + ", detalle=" + detalle + ", fechaRealizada="
				+ fechaRealizada + ", usuarioCreacion=" + usuarioCreacion + "]";
	}       
		
}
