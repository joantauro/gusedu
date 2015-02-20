package com.gusedu.model;

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
@Table(name = "HISTORIA_CLINICA")
public class HistoriaClinica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "HCL_CODIGO")
	private Integer idHistoriaClinica;

	@Column(nullable = true, name = "HCL_PESO")
	private Double peso;

	@Column(nullable = true, name = "HCL_TALLA")
	private Double talla;

	@Column(nullable = true, name = "HCL_IMC")
	private Double imc;

	@Column(nullable = true, name = "HCL_TEMPERATURA")
	private Double temperatura;

	@Column(nullable = true, name = "HCL_TENSION_ARTERIAL", length = 8)
	private String tensionArterial;
	
	@Column(nullable = true, name = "HCL_GLUCOSA")
	private Double glucosa;	
	
	@Column(nullable = true, name = "HCL_DATOS_ADICIONALES", length = 500)
	private String datosAdicionales;
	
	//Update V2
	
	@Column(nullable = true, name = "HCL_EMBARAZO")
	private Boolean estaEmbarazada;
	
	@Column(nullable = true, name = "HCL_MES_EMBARAZO")
	private String mesEmbarazo;	
	
	@Column(nullable = true, name = "HCL_QUIMIOTERAPIA")
	private Boolean haRecibidoQuimio;
	
	@Column(nullable = true, name = "HCL_DISP_ELECTRONICO")
	private Boolean tieneDispElectronico;	
	
	//Update nutricional
	
 //Nutricionales
    
    @Column(nullable = true, name = "CLI_AZUCAR")
    private Boolean azucar;
    
    @Column(nullable = true, name = "CLI_SAL_YODADA")
    private Boolean salYodada;    
    
    @Column(nullable = true, name = "CLI_ES_VEGETARIANO")
    private Boolean esVegetariano;   
    
    @Column(nullable = true, name = "CLI_HAB_DESAYUNO")
    private String desayuno;    
    
    @Column(nullable = true, name = "CLI_HAB_ALMUERZO")
    private String almuerzo;    
    
    @Column(nullable = true, name = "CLI_HAB_CENA")
    private String cena;    
    
    @Column(nullable = true, name = "CLI_AGUA")
    private Integer agua;    
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "VIS_CODIGO", nullable = true)
	private Visita hclVisita;

	public Integer getIdHistoriaClinica() {
		return idHistoriaClinica;
	}

	public void setIdHistoriaClinica(Integer idHistoriaClinica) {
		this.idHistoriaClinica = idHistoriaClinica;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getTalla() {
		return talla;
	}

	public void setTalla(Double talla) {
		this.talla = talla;
	}

	public Double getImc() {
		return imc;
	}

	public void setImc(Double imc) {
		this.imc = imc;
	}

	public Double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}

	public String getTensionArterial() {
		return tensionArterial;
	}

	public void setTensionArterial(String tensionArterial) {
		this.tensionArterial = tensionArterial;
	}

	public Double getGlucosa() {
		return glucosa;
	}

	public void setGlucosa(Double glucosa) {
		this.glucosa = glucosa;
	}

	public String getDatosAdicionales() {
		return datosAdicionales;
	}

	public void setDatosAdicionales(String datosAdicionales) {
		this.datosAdicionales = datosAdicionales;
	}

	public Visita getHclVisita() {
		return hclVisita;
	}

	public void setHclVisita(Visita hclVisita) {
		this.hclVisita = hclVisita;
	}

	public Boolean getEstaEmbarazada() {
		return estaEmbarazada;
	}

	public void setEstaEmbarazada(Boolean estaEmbarazada) {
		this.estaEmbarazada = estaEmbarazada;
	}

	public String getMesEmbarazo() {
		return mesEmbarazo;
	}

	public void setMesEmbarazo(String mesEmbarazo) {
		this.mesEmbarazo = mesEmbarazo;
	}

	public Boolean getHaRecibidoQuimio() {
		return haRecibidoQuimio;
	}

	public void setHaRecibidoQuimio(Boolean haRecibidoQuimio) {
		this.haRecibidoQuimio = haRecibidoQuimio;
	}

	public Boolean getTieneDispElectronico() {
		return tieneDispElectronico;
	}

	public void setTieneDispElectronico(Boolean tieneDispElectronico) {
		this.tieneDispElectronico = tieneDispElectronico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((idHistoriaClinica == null) ? 0 : idHistoriaClinica
						.hashCode());
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
		HistoriaClinica other = (HistoriaClinica) obj;
		if (idHistoriaClinica == null) {
			if (other.idHistoriaClinica != null)
				return false;
		} else if (!idHistoriaClinica.equals(other.idHistoriaClinica))
			return false;
		return true;
	}

	public Boolean getAzucar() {
		return azucar;
	}

	public void setAzucar(Boolean azucar) {
		this.azucar = azucar;
	}

	public Boolean getSalYodada() {
		return salYodada;
	}

	public void setSalYodada(Boolean salYodada) {
		this.salYodada = salYodada;
	}

	public String getDesayuno() {
		return desayuno;
	}

	public void setDesayuno(String desayuno) {
		this.desayuno = desayuno;
	}

	public String getAlmuerzo() {
		return almuerzo;
	}

	public void setAlmuerzo(String almuerzo) {
		this.almuerzo = almuerzo;
	}

	public String getCena() {
		return cena;
	}

	public void setCena(String cena) {
		this.cena = cena;
	}

	public Integer getAgua() {
		return agua;
	}

	public void setAgua(Integer agua) {
		this.agua = agua;
	}

	public Boolean getEsVegetariano() {
		return esVegetariano;
	}

	public void setEsVegetariano(Boolean esVegetariano) {
		this.esVegetariano = esVegetariano;
	}

	@Override
	public String toString() {
		return "HistoriaClinica [idHistoriaClinica=" + idHistoriaClinica
				+ ", peso=" + peso + ", talla=" + talla + ", imc=" + imc
				+ ", temperatura=" + temperatura + ", tensionArterial="
				+ tensionArterial + ", glucosa=" + glucosa
				+ ", datosAdicionales=" + datosAdicionales + "]";
	}
	
	
	
}
