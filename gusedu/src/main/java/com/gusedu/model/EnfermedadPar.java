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
@Table(name="ENFERMEDAD_PAR")
public class EnfermedadPar {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "EXP_CODIGO")
    private Integer idEnfermedadPar;
	
    @ManyToOne @JoinColumn(name="ENF_CODIGO", nullable=false)
    private Enfermedad expEnfermedad;
    
    @ManyToOne @JoinColumn(name="PAR_CODIGO", nullable=false)
    private Par expPar;

	public Integer getIdEnfermedadPar() {
		return idEnfermedadPar;
	}

	public void setIdEnfermedadPar(Integer idEnfermedadPar) {
		this.idEnfermedadPar = idEnfermedadPar;
	}

	public Enfermedad getExpEnfermedad() {
		return expEnfermedad;
	}

	public void setExpEnfermedad(Enfermedad expEnfermedad) {
		this.expEnfermedad = expEnfermedad;
	}

	public Par getExpPar() {
		return expPar;
	}

	public void setExpPar(Par expPar) {
		this.expPar = expPar;
	}    

    
    
}
