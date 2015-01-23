package com.gusedu.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ESTADO_CIVIL")
public class EstadoCivil{
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "ECI_CODIGO")
    private Integer idEstadoCivil;
    
    @Column(nullable= false, length = 50, name = "ECI_DESCRIPCION")
    private Integer descripcion;

    public Integer getIdEstadoCivil() {
        return idEstadoCivil;
    }

    public void setIdEstadoCivil(Integer idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public Integer getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(Integer descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.idEstadoCivil);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EstadoCivil other = (EstadoCivil) obj;
        if (!Objects.equals(this.idEstadoCivil, other.idEstadoCivil)) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "EstadoCivil [idEstadoCivil=" + idEstadoCivil + ", descripcion="
				+ descripcion + "]";
	}    
    
}
