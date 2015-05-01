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
@Table(name="ENFERMEDAD_VISITA")
public class EnfermedadVisita {
	
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "EXV_CODIGO")
    private Integer idEnfermedad;	
    
    @Column(nullable= true, name="EXV_DESCRIPCION")
    private String descripcion;
    
    @ManyToOne @JoinColumn(name="VIS_CODIGO", nullable=false)
    private Visita exvVisita;	
    
    @ManyToOne @JoinColumn(name="ENF_CODIGO", nullable=false)
    private Enfermedad exvEnfermedad;   

	public Integer getIdEnfermedad() {
		return idEnfermedad;
	}

	public void setIdEnfermedad(Integer idEnfermedad) {
		this.idEnfermedad = idEnfermedad;
	}

 

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Visita getExvVisita() {
		return exvVisita;
	}

	public void setExvVisita(Visita exvVisita) {
		this.exvVisita = exvVisita;
	}

	public Enfermedad getExvEnfermedad() {
		return exvEnfermedad;
	}

	public void setExvEnfermedad(Enfermedad exvEnfermedad) {
		this.exvEnfermedad = exvEnfermedad;
	}
    
    

}
