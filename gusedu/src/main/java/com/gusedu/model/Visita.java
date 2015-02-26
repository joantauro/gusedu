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
@Table(name = "VISITA")
public class Visita {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "VIS_CODIGO")
	private Integer idVisita;

	@Column(nullable = true, name = "VIS_PRESENCIAL")
	private Boolean esPresencial;

	@Column(nullable = true, name = "VIS_ESTADO")
	private Integer estado;

	@Column(nullable = true, name = "VIS_PRIORIDAD")
	private Integer prioridad;

	@Column(nullable = true, name = "VIS_FEC_CREACION")
	private Date fechaCreacion;

	@Column(nullable = true, name = "VIS_USU_CREACION")
	private Date usuarioCreacion;
	
	@Column(nullable = true, name = "VIS_COSTO_TOTAL")
	private Double costoTotal;

	@ManyToOne
	@JoinColumn(name = "CLI_CODIGO", nullable = false)
	private Cliente visCliente;

	@OneToMany(mappedBy = "terVisita")
	private Collection<Terapia> visTerapias;
	
	@OneToOne(mappedBy="hclVisita")
	private HistoriaClinica visHistoriaClinica;
	
	@OneToMany(mappedBy="pxvVisita")
	private Collection<ProductoVisita> visProductoVisitas;
	
	public Integer getIdVisita() {
		return idVisita;
	}

	public void setIdVisita(Integer idVisita) {
		this.idVisita = idVisita;
	}

	public Boolean getEsPresencial() {
		return esPresencial;
	}

	public void setEsPresencial(Boolean esPresencial) {
		this.esPresencial = esPresencial;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Integer getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(Date usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Cliente getVisCliente() {
		return visCliente;
	}

	public void setVisCliente(Cliente visCliente) {
		this.visCliente = visCliente;
	}

	public Collection<ProductoVisita> getVisProductoVisitas() {
		return visProductoVisitas;
	}

	public void setVisProductoVisitas(Collection<ProductoVisita> visProductoVisitas) {
		this.visProductoVisitas = visProductoVisitas;
	}

	public Collection<Terapia> getVisTerapias() {
		return visTerapias;
	}

	public void setVisTerapias(Collection<Terapia> visTerapias) {
		this.visTerapias = visTerapias;
	}

	public Double getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(Double costoTotal) {
		this.costoTotal = costoTotal;
	}

	public HistoriaClinica getVisHistoriaClinica() {
		return visHistoriaClinica;
	}

	public void setVisHistoriaClinica(HistoriaClinica visHistoriaClinica) {
		this.visHistoriaClinica = visHistoriaClinica;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idVisita == null) ? 0 : idVisita.hashCode());
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
		Visita other = (Visita) obj;
		if (idVisita == null) {
			if (other.idVisita != null)
				return false;
		} else if (!idVisita.equals(other.idVisita))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Visita [idVisita=" + idVisita + ", esPresencial="
				+ esPresencial + ", estado=" + estado + ", prioridad="
				+ prioridad + ", fechaCreacion=" + fechaCreacion
				+ ", usuarioCreacion=" + usuarioCreacion + "]";
	}

	
	
}
