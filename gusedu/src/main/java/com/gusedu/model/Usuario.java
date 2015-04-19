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
@Table(name="USUARIO")
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "USU_CODIGO")
    private Integer idUsuario;
    
    @Column(nullable = false, name = "USU_USUARIO", length=50)
    private String usuario;
    
    @Column(nullable = false, name = "USU_PASSWORD", length=150)
    private String password;
    
    @Column(nullable = true, name = "USU_FEC_CREACION")
    private Date fechaCreacion;
    
    @Column(nullable = true, name = "USU_ACTIVO")
    private Boolean esActivo;
    
    @Column(nullable = true, name="USU_EMPRESA")
    private String empresa;
    
    @Column(nullable = true, name="USU_FEC_FINM")
    private Date fechafinm;
    
    @ManyToOne @JoinColumn(name="TUS_CODIGO", nullable=false)
    private TipoUsuario usuTipoUsuario;
    
	@OneToMany(mappedBy="menUsuarioEmisor")
	private Collection<Mensaje> usuMenEmisores;
	
	@OneToMany(mappedBy="menUsuarioReceptor")
	private Collection<Mensaje> usuMenReceptores;
		
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PER_CODIGO", nullable = true)
	private Persona usuPersona;
 
	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Boolean getEsActivo() {
		return esActivo;
	}

	public void setEsActivo(Boolean esActivo) {
		this.esActivo = esActivo;
	}

	public Collection<Mensaje> getUsuMenEmisores() {
		return usuMenEmisores;
	}

	public void setUsuMenEmisores(Collection<Mensaje> usuMenEmisores) {
		this.usuMenEmisores = usuMenEmisores;
	}

	public TipoUsuario getUsuTipoUsuario() {
		return usuTipoUsuario;
	}

	public void setUsuTipoUsuario(TipoUsuario usuTipoUsuario) {
		this.usuTipoUsuario = usuTipoUsuario;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

    public Date getFechafinm() {
        return fechafinm;
    }

    public void setFechafinm(Date fechafinm) {
        this.fechafinm = fechafinm;
    }	  
	
	public Collection<Mensaje> getUsuMenReceptores() {
		return usuMenReceptores;
	}

	public void setUsuMenReceptores(Collection<Mensaje> usuMenReceptores) {
		this.usuMenReceptores = usuMenReceptores;
	}

	public Persona getUsuPersona() {
		return usuPersona;
	}

	public void setUsuPersona(Persona usuPersona) {
		this.usuPersona = usuPersona;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idUsuario == null) ? 0 : idUsuario.hashCode());
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
		Usuario other = (Usuario) obj;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", usuario=" + usuario
				+ ", password=" + password + ", fechaCreacion=" + fechaCreacion
				+ ", esActivo=" + esActivo + ", fechafinm=" + fechafinm + "]";
	}

  
    
}
