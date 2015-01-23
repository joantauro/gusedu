package com.gusedu.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="PERSONA")
public class Persona {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "PER_CODIGO")
    private Integer idPersona;
    
    @Column(nullable= true, name= "PER_DNI")
    private Integer dni;
    
    @Column(nullable= false, name= "PER_NOMBRES", length= 100)
    private String nombres;
    
    @Column(nullable= false, name= "PER_APELLIDO_P", length= 100)
    private String apellidoPaterno;
    
    @Column(nullable= false, name= "PER_APELLIDO_M", length= 100)
    private String apellidoMaterno;
    
    @Column(nullable= true, name= "PER_CORREO", length= 100)
    private String correo;
    
    @Column(name="PER_FEC_NACIMIENTO", nullable=true)
    private Date fechaNacimiento;
    
    @Column(name="PER_TALLA", nullable=true)
    private Double talla;
    
    @Column(name="PER_PESO", nullable=true)
    private Double peso;
    
    @Column(name="PER_ESTADO_CIVIL", nullable=true, length=15)
    private String estadoCivil;
    
    @Column(name="PER_SEXO", nullable=true, length=1)
    private String sexo;
    
    @Column(name="PER_NIT", nullable=true)
    private Integer nit;
    
    @Column(name="PER_CLI_POTENCIAL", nullable=true)
    private Boolean esClientePotencial;
    
    @Column(name="PER_FEC_CREACION", nullable=true)
    private Date fechaCreacion;
    
    @Column(name="PER_USU_CREACION", nullable=true, length=100)
    private String usuarioCreacion;
    
	@OneToOne(mappedBy="usuPersona")
	private Usuario perUsuario;	

	@OneToOne(mappedBy="cliPersona")
	private Cliente perCliente;	
	
	@OneToMany(mappedBy="llaPersona")
	private Collection<Llamada> perLlamadas;
	
	public Integer getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Double getTalla() {
		return talla;
	}

	public void setTalla(Double talla) {
		this.talla = talla;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Integer getNit() {
		return nit;
	}

	public void setNit(Integer nit) {
		this.nit = nit;
	}

	public Boolean getEsClientePotencial() {
		return esClientePotencial;
	}

	public void setEsClientePotencial(Boolean esClientePotencial) {
		this.esClientePotencial = esClientePotencial;
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

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idPersona == null) ? 0 : idPersona.hashCode());
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
		Persona other = (Persona) obj;
		if (idPersona == null) {
			if (other.idPersona != null)
				return false;
		} else if (!idPersona.equals(other.idPersona))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Persona [idPersona=" + idPersona + ", dni=" + dni
				+ ", nombres=" + nombres + ", apellidoPaterno="
				+ apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno
				+ ", correo=" + correo + ", fechaNacimiento=" + fechaNacimiento
				+ ", talla=" + talla + ", peso=" + peso + ", estadoCivil="
				+ estadoCivil + ", sexo=" + sexo + ", nit=" + nit
				+ ", esClientePotencial=" + esClientePotencial
				+ ", fechaCreacion=" + fechaCreacion + ", usuarioCreacion="
				+ usuarioCreacion + "]";
	}	    
	
}
