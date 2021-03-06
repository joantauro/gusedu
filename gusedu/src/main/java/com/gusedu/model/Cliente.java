package com.gusedu.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
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
@Table(name="CLIENTE")
public class Cliente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "CLI_CODIGO")
    private Integer idCliente;
	
    @Column(nullable = true, name = "CLI_FEC_CREACION")
    private Date fechaCreacion;
    
    @Column(nullable = true, name = "CLI_USU_CREACION")
    private String usuarioCreacion;

    @Column(nullable = true, name = "CLI_ACTIVO")
    private Boolean esActivo;
    

    
    @Column(nullable = true, name = "CLI_CAN_REGULAR")
    private String cansancioRegular;
    
    @Column(nullable = true, name = "CLI_RES_REGULAR")
    private String resfrioRegular;
    
    @Column(nullable = true, name = "CLI_DOLCAB_REGULAR")
    private String dolorCabezaRegular;
    
    @Column(nullable = true, name = "CLI_PRO_GASTRICO")
    private String gastricoRegular;

    @Column(nullable = true, name = "CLI_OPERACION")
    private String operacion;
    
    @Column(nullable = true, name = "CLI_CIRUGIA_ESTETICA")
    private String cirugia;

    @Column(nullable = true, name = "CLI_ALERGIA")
    private String alergia;
    
    @Column(nullable = true, name = "CLI_MEDICAMENTOS")
    private String medicamentos;  
    
    @Column(nullable = true, name = "CLI_HIJOS")
    private String hijos;  
    
    @Column(nullable = true, name = "CLI_EJERCICIO")
    private String ejercicio;  
    
    @Column(nullable = true, name = "CLI_OCUPACION")
    private String ocupacion;      
    
    @Column(nullable = true, name = "CLI_HABITOS")
    private String habitosSueno;     
    
    @Column(nullable = true, name = "CLI_ALCOHOL")
    private String alcohol;     
    
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PER_CODIGO", nullable = true)
	private Persona cliPersona;
	
    @ManyToOne @JoinColumn(name="TCL_CODIGO", nullable=false)
    private TipoCliente cliTipoCliente;
    
    @OneToMany(mappedBy="visCliente")
	private Collection<Visita> cliVisitas;

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}	

	public String getCansancioRegular() {
		return cansancioRegular;
	}

	public void setCansancioRegular(String cansancioRegular) {
		this.cansancioRegular = cansancioRegular;
	}

	public String getResfrioRegular() {
		return resfrioRegular;
	}

	public void setResfrioRegular(String resfrioRegular) {
		this.resfrioRegular = resfrioRegular;
	}

	public String getDolorCabezaRegular() {
		return dolorCabezaRegular;
	}

	public void setDolorCabezaRegular(String dolorCabezaRegular) {
		this.dolorCabezaRegular = dolorCabezaRegular;
	}

	public String getGastricoRegular() {
		return gastricoRegular;
	}

	public void setGastricoRegular(String gastricoRegular) {
		this.gastricoRegular = gastricoRegular;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Persona getCliPersona() {
		return cliPersona;
	}

	public void setCliPersona(Persona cliPersona) {
		this.cliPersona = cliPersona;
	}

	public TipoCliente getCliTipoCliente() {
		return cliTipoCliente;
	}

	public void setCliTipoCliente(TipoCliente cliTipoCliente) {
		this.cliTipoCliente = cliTipoCliente;
	}
		
	public boolean isEsActivo() {
		return esActivo;
	}

	public void setEsActivo(boolean esActivo) {
		this.esActivo = esActivo;
	}
	
	public String getCirugia() {
		return cirugia;
	}

	public void setCirugia(String cirugia) {
		this.cirugia = cirugia;
	}

	public String getAlergia() {
		return alergia;
	}

	public void setAlergia(String alergia) {
		this.alergia = alergia;
	}

	public String getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(String medicamentos) {
		this.medicamentos = medicamentos;
	}

	public Boolean getEsActivo() {
		return esActivo;
	}

	public void setEsActivo(Boolean esActivo) {
		this.esActivo = esActivo;
	}

	public Collection<Visita> getCliVisitas() {
		return cliVisitas;
	}

	public void setCliVisitas(Collection<Visita> cliVisitas) {
		this.cliVisitas = cliVisitas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idCliente == null) ? 0 : idCliente.hashCode());
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
		Cliente other = (Cliente) obj;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", fechaCreacion="
				+ fechaCreacion + ", usuarioCreacion=" + usuarioCreacion + "]";
	}

	public String getHijos() {
		return hijos;
	}

	public void setHijos(String hijos) {
		this.hijos = hijos;
	}

	public String getEjercicio() {
		return ejercicio;
	}

	public void setEjercicio(String ejercicio) {
		this.ejercicio = ejercicio;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getHabitosSueno() {
		return habitosSueno;
	}

	public void setHabitosSueno(String habitosSueno) {
		this.habitosSueno = habitosSueno;
	}

	public String getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(String alcohol) {
		this.alcohol = alcohol;
	}        
    
    
}
