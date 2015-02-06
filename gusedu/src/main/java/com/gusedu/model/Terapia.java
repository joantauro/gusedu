package com.gusedu.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TERAPIA")
public class Terapia {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "TER_CODIGO")
    private Integer idTerapia;
	
	@Column(nullable = true, name = "TER_FEC_REALIZADA")
	private Date fechaRealizada;
	
	@Column(nullable = true, name = "TER_FEC_PROXIMA")
	private Date fechaProxima;
	
	@Column(nullable = true, name = "TER_DESCRIPCION", length=200)
	private String descripcion;
	
	@Column(nullable = true, name = "TER_USU_CREACION", length=100)
	private String usuarioCreacion;
	
    @ManyToOne @JoinColumn(name="VIS_CODIGO", nullable=false)
    private Visita terVisita;	
    	
    @ManyToOne @JoinColumn(name="TTE_CODIGO", nullable=false)
    private TipoTerapia terTipoTerapia;	
    
	@OneToMany(mappedBy="sxtTerapia")
	private Collection<SintomaTerapia> terSintomaTerapias;

	@OneToOne(mappedBy="diaTerapia")
	private Diagnostico terDiagnostico;
	
	@OneToMany(mappedBy="txpTerapia", orphanRemoval=true)
	private Collection<TerapiaPar> terTerapiaPares;		
	
	@OneToMany(mappedBy="extTerapia")
	private Collection<EnfermedadTerapia> terEnfermedadTerapias;	
	
	public Diagnostico getTerDiagnostico() {
		return terDiagnostico;
	}

	public void setTerDiagnostico(Diagnostico terDiagnostico) {
		this.terDiagnostico = terDiagnostico;
	}

	public Collection<TerapiaPar> getTerTerapiaPares() {
		return terTerapiaPares;
	}

	public void setTerTerapiaPares(Collection<TerapiaPar> terTerapiaPares) {
		this.terTerapiaPares = terTerapiaPares;
	}

	public Integer getIdTerapia() {
		return idTerapia;
	}

	public void setIdTerapia(Integer idTerapia) {
		this.idTerapia = idTerapia;
	}

	public Date getFechaRealizada() {
		return fechaRealizada;
	}

	public void setFechaRealizada(Date fechaRealizada) {
		this.fechaRealizada = fechaRealizada;
	}

	public Date getFechaProxima() {
		return fechaProxima;
	}

	public void setFechaProxima(Date fechaProxima) {
		this.fechaProxima = fechaProxima;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Visita getTerVisita() {
		return terVisita;
	}

	public void setTerVisita(Visita terVisita) {
		this.terVisita = terVisita;
	}

	public TipoTerapia getTerTipoTerapia() {
		return terTipoTerapia;
	}

	public void setTerTipoTerapia(TipoTerapia terTipoTerapia) {
		this.terTipoTerapia = terTipoTerapia;
	}

	public Collection<SintomaTerapia> getTerSintomaTerapias() {
		return terSintomaTerapias;
	}

	public void setTerSintomaTerapias(Collection<SintomaTerapia> terSintomaTerapias) {
		this.terSintomaTerapias = terSintomaTerapias;
	}

	public Collection<EnfermedadTerapia> getTerEnfermedadTerapias() {
		return terEnfermedadTerapias;
	}

	public void setTerEnfermedadTerapias(
			Collection<EnfermedadTerapia> terEnfermedadTerapias) {
		this.terEnfermedadTerapias = terEnfermedadTerapias;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idTerapia == null) ? 0 : idTerapia.hashCode());
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
		Terapia other = (Terapia) obj;
		if (idTerapia == null) {
			if (other.idTerapia != null)
				return false;
		} else if (!idTerapia.equals(other.idTerapia))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Terapia [idTerapia=" + idTerapia + ", fechaRealizada="
				+ fechaRealizada + ", fechaProxima=" + fechaProxima
				+ ", descripcion=" + descripcion + ", usuarioCreacion="
				+ usuarioCreacion + ", terVisita=" + terVisita
				+ ", terTipoTerapia=" + terTipoTerapia
				+ ", terSintomaTerapias=" + terSintomaTerapias + "]";
	}
	
}
