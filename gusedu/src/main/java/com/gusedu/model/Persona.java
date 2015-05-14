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
    
    @Column(nullable= true, name= "PER_DNI", length = 15)
    private String dni;
    
    @Column(nullable= true, name= "PER_FACEBOOK", length = 50)
    private String facebook;
    
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
        
    @Column(name="PER_ESTADO_CIVIL", nullable=true, length=15)
    private String estadoCivil;
    
    @Column(name="PER_SEXO", nullable=true, length=1)
    private String sexo;
    
    @Column(name="PER_SIGNO_ZODIACAL", nullable=true)
    private String signoZodiacal;    
    
    @Column(name="PER_NIT", nullable=true)
    private Integer nit;
    
    @Column(name="PER_TEL_FIJO", nullable=true, length = 20)
    private String telefonoFijo;
    
    @Column(name="PER_TEL_MOVIL", nullable=true, length = 20)
    private String telefonoMovil;    
    
    @Column(name="PER_CLI_POTENCIAL", nullable=true)
    private Boolean esClientePotencial;
    
    @Column(name="PER_FEC_CREACION", nullable=true)
    private Date fechaCreacion;
    
    @Column(name="PER_ENTERO_GUSEDU", nullable=true)
    private String comoSeEntero;
    
    @Column(name="PER_USU_CREACION", nullable=true, length=100)
    private String usuarioCreacion;
    
    @Column(nullable = false, name = "PER_EDAD")
    private Integer edad;
    
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
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

	public Usuario getPerUsuario() {
		return perUsuario;
	}

	public void setPerUsuario(Usuario perUsuario) {
		this.perUsuario = perUsuario;
	}

	public Cliente getPerCliente() {
		return perCliente;
	}

	public void setPerCliente(Cliente perCliente) {
		this.perCliente = perCliente;
	}

	public Collection<Llamada> getPerLlamadas() {
		return perLlamadas;
	}

	public void setPerLlamadas(Collection<Llamada> perLlamadas) {
		this.perLlamadas = perLlamadas;
	}
	

	public String getComoSeEntero() {
		return comoSeEntero;
	}

	public void setComoSeEntero(String comoSeEntero) {
		this.comoSeEntero = comoSeEntero;
	}



	public String getSignoZodiacal() {
		return signoZodiacal;
	}

	public void setSignoZodiacal(String signoZodiacal) {
		this.signoZodiacal = signoZodiacal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idPersona == null) ? 0 : idPersona.hashCode());
		return result;
	}

	public String getTelefonoFijo() {
		return telefonoFijo;
	}

	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	public String getTelefonoMovil() {
		return telefonoMovil;
	}

	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
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
				+ ", estadoCivil=" + estadoCivil + ", sexo=" + sexo + ", nit="
				+ nit + ", telefonoFijo=" + telefonoFijo + ", telefonoMovil="
				+ telefonoMovil + ", esClientePotencial=" + esClientePotencial
				+ ", fechaCreacion=" + fechaCreacion + ", usuarioCreacion="
				+ usuarioCreacion + "]";
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	
}
