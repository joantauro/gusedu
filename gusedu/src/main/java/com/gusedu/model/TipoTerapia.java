package com.gusedu.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TIPO_TERAPIA")
public class TipoTerapia {
	
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "TTE_CODIGO")
    private Integer idTipoTerapia;	
    
    @Column(nullable = true, name = "TTE_NOMBRE", length=20)
    private String nombre;
    
    @Column(nullable = true, name = "TTE_COSTO")
    private Double costo;

    @Column(nullable = true, name = "TTE_FEC_MODIFICACION")
    private Date fechaModificacion;
    
    @Column(nullable = true, name = "TTE_USU_MODIFICACION")
    private String usuarioModificacion;
    
	@OneToMany(mappedBy="terTipoTerapia", fetch = FetchType.EAGER)
	private Collection<Terapia> tteTerapias;

	public Integer getIdTipoTerapia() {
		return idTipoTerapia;
	}

	public void setIdTipoTerapia(Integer idTipoTerapia) {
		this.idTipoTerapia = idTipoTerapia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public Collection<Terapia> getTteTerapias() {
		return tteTerapias;
	}

	public void setTteTerapias(Collection<Terapia> tteTerapias) {
		this.tteTerapias = tteTerapias;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idTipoTerapia == null) ? 0 : idTipoTerapia.hashCode());
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
		TipoTerapia other = (TipoTerapia) obj;
		if (idTipoTerapia == null) {
			if (other.idTipoTerapia != null)
				return false;
		} else if (!idTipoTerapia.equals(other.idTipoTerapia))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TipoTerapia [idTipoTerapia=" + idTipoTerapia + ", nombre="
				+ nombre + ", costo=" + costo + ", fechaModificacion="
				+ fechaModificacion + ", usuarioModificacion="
				+ usuarioModificacion + ", tteTerapias=" + tteTerapias + "]";
	}		
	
}
