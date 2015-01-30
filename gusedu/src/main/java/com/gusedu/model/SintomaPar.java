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
@Table(name="SINTOMA_PAR")
public class SintomaPar {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "SXP_CODIGO")
    private Integer idSintomaPar;
	
    @ManyToOne @JoinColumn(name="SIN_CODIGO", nullable=false)
    private Sintoma sxpSintoma;
    
    @ManyToOne @JoinColumn(name="PAR_CODIGO", nullable=false)
    private Par sxpPar;

	public Integer getIdSintomaPar() {
		return idSintomaPar;
	}

	public void setIdSintomaPar(Integer idSintomaPar) {
		this.idSintomaPar = idSintomaPar;
	}

	public Sintoma getSxpSintoma() {
		return sxpSintoma;
	}

	public void setSxpSintoma(Sintoma sxpSintoma) {
		this.sxpSintoma = sxpSintoma;
	}

	public Par getSxpPar() {
		return sxpPar;
	}

	public void setSxpPar(Par sxpPar) {
		this.sxpPar = sxpPar;
	}    
    
    
	
}
