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
@Table(name="TIPO_CLIENTE")
public class TipoCliente {
	
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "TCL_CODIGO")
    private Integer idTipoCliente;	

    @Column(nullable = true, name = "TCL_DESCRIPCION")
    private String descripcion;
    
	@OneToMany(mappedBy="cliTipoCliente")
	private Collection<Cliente> tclClientes;

	public Integer getIdTipoCliente() {
		return idTipoCliente;
	}

	public void setIdTipoCliente(Integer idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Collection<Cliente> getTclClientes() {
		return tclClientes;
	}

	public void setTclClientes(Collection<Cliente> tclClientes) {
		this.tclClientes = tclClientes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idTipoCliente == null) ? 0 : idTipoCliente.hashCode());
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
		TipoCliente other = (TipoCliente) obj;
		if (idTipoCliente == null) {
			if (other.idTipoCliente != null)
				return false;
		} else if (!idTipoCliente.equals(other.idTipoCliente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TipoCliente [idTipoCliente=" + idTipoCliente + ", descripcion="
				+ descripcion + "]";
	}    
    
	
}
