package com.gusedu.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TIPO_PRODUCTO")
public class TipoProducto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "TPR_CODIGO")
    private Integer idTipoProducto;	
    
    @Column(nullable = true, name = "TPR_NOMBRE", length=20)
    private String nombre;
	
    @Column(nullable = true, name = "TPR_FEC_MODIFICACION")
    private Date fechaModificacion;
    
    @Column(nullable = true, name = "TPR_USU_MODIFICACION")
    private String usuarioModificacion;
    
	@OneToMany(mappedBy="proTipoProducto")
	private Collection<Producto> tprProductos;

	public Integer getIdTipoProducto() {
		return idTipoProducto;
	}

	public void setIdTipoProducto(Integer idTipoProducto) {
		this.idTipoProducto = idTipoProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public Collection<Producto> getTprProductos() {
		return tprProductos;
	}

	public void setTprProductos(Collection<Producto> tprProductos) {
		this.tprProductos = tprProductos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idTipoProducto == null) ? 0 : idTipoProducto.hashCode());
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
		TipoProducto other = (TipoProducto) obj;
		if (idTipoProducto == null) {
			if (other.idTipoProducto != null)
				return false;
		} else if (!idTipoProducto.equals(other.idTipoProducto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TipoProducto [idTipoProducto=" + idTipoProducto + ", nombre="
				+ nombre + ", fechaModificacion=" + fechaModificacion
				+ ", usuarioModificacion=" + usuarioModificacion + "]";
	}  
		    
}
