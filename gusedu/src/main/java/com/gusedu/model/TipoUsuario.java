package com.gusedu.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TIPO_USUARIO")
public class TipoUsuario {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "TUS_CODIGO")
    private Integer idTipoUsuario;	

    @Column(nullable = true, name = "TUS_DESCRIPCION")
    private String descripcion;
    
	@OneToMany(mappedBy="usuTipoUsuario")
	private Collection<Usuario> tusUsuarios;

	public Integer getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(Integer idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Collection<Usuario> getTusUsuarios() {
		return tusUsuarios;
	}

	public void setTusUsuarios(Collection<Usuario> tusUsuarios) {
		this.tusUsuarios = tusUsuarios;
	}
	
	
	
}
