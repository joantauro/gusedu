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
@Table(name="ENFERMEDAD_TERAPIA")
public class EnfermedadTerapia {
	
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "EXT_CODIGO")
    private Integer idEnfermedad;	
    
    @Column(nullable= true, name="EXT_DESCRIPCION")
    private String descripcion;
    
    @ManyToOne @JoinColumn(name="TER_CODIGO", nullable=false)
    private Terapia extTerapia;	
    
    @ManyToOne @JoinColumn(name="ENF_CODIGO", nullable=false)
    private Enfermedad extEnfermedad;   

	public Integer getIdEnfermedad() {
		return idEnfermedad;
	}

	public void setIdEnfermedad(Integer idEnfermedad) {
		this.idEnfermedad = idEnfermedad;
	}

	public Terapia getExtTerapia() {
		return extTerapia;
	}

	public void setExtTerapia(Terapia extTerapia) {
		this.extTerapia = extTerapia;
	}

	public Enfermedad getExtEnfermedad() {
		return extEnfermedad;
	}

	public void setExtEnfermedad(Enfermedad extEnfermedad) {
		this.extEnfermedad = extEnfermedad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    
    

}
