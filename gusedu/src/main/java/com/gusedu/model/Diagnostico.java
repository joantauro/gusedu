package com.gusedu.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="DIAGNOSTICO")
public class Diagnostico {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "DIA_CODIGO")
    private Integer idDiagnostico;
	
	@Column(nullable = true, name = "DIA_DESCRIPCION", length=300)
	private String descripcion;
	
	@Column(nullable = true, name = "DIA_RESULTADO", length=500)
	private String resultado;
	
	@Column(nullable = true, name = "DIA_FEC_REALIZADO")
	private Date fechaRealizado;
	
	@Column(nullable = true, name = "DIA_USU_CREACION", length=100)
	private String usuarioCreacion;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TER_CODIGO", nullable = true)
	private Terapia diaTerapia;

	public Integer getIdDiagnostico() {
		return idDiagnostico;
	}

	public void setIdDiagnostico(Integer idDiagnostico) {
		this.idDiagnostico = idDiagnostico;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public Date getFechaRealizado() {
		return fechaRealizado;
	}

	public void setFechaRealizado(Date fechaRealizado) {
		this.fechaRealizado = fechaRealizado;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Terapia getDiaTerapia() {
		return diaTerapia;
	}

	public void setDiaTerapia(Terapia diaTerapia) {
		this.diaTerapia = diaTerapia;
	}
	
}
