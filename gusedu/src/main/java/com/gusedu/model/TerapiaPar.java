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
@Table(name="TERAPIA_PAR")
public class TerapiaPar {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "TXP_CODIGO")
    private Integer idTerapiaPar;
	
    @ManyToOne @JoinColumn(name="TER_CODIGO", nullable=false)
    private Terapia txpTerapia;
    
    @ManyToOne @JoinColumn(name="PAR_CODIGO", nullable=false)
    private Par txpPar;

	public Integer getIdTerapiaPar() {
		return idTerapiaPar;
	}

	public void setIdTerapiaPar(Integer idTerapiaPar) {
		this.idTerapiaPar = idTerapiaPar;
	}

	public Terapia getTxpTerapia() {
		return txpTerapia;
	}

	public void setTxpTerapia(Terapia txpTerapia) {
		this.txpTerapia = txpTerapia;
	}

	public Par getTxpPar() {
		return txpPar;
	}

	public void setTxpPar(Par txpPar) {
		this.txpPar = txpPar;
	}
	
}
