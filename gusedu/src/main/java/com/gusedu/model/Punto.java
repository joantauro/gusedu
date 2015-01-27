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
@Table(name="PUNTO")
public class Punto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "PUN_CODIGO")
    private Integer idPunto;
    
    @Column(nullable= true, name= "PUN_NOMBRE", length=30)
    private String nombre;
    
    @Column(nullable= true, name= "PUN_UBICACION", length=50)
    private String ubicacion;    
    
	@OneToMany(mappedBy="parPunto1")
	private Collection<Par> punPares1;
	
	@OneToMany(mappedBy="parPunto2")
	private Collection<Par> punPares2;

	public Integer getIdPunto() {
		return idPunto;
	}

	public void setIdPunto(Integer idPunto) {
		this.idPunto = idPunto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Collection<Par> getPunPares1() {
		return punPares1;
	}

	public void setPunPares1(Collection<Par> punPares1) {
		this.punPares1 = punPares1;
	}

	public Collection<Par> getPunPares2() {
		return punPares2;
	}

	public void setPunPares2(Collection<Par> punPares2) {
		this.punPares2 = punPares2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPunto == null) ? 0 : idPunto.hashCode());
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
		Punto other = (Punto) obj;
		if (idPunto == null) {
			if (other.idPunto != null)
				return false;
		} else if (!idPunto.equals(other.idPunto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Punto [idPunto=" + idPunto + ", nombre=" + nombre
				+ ", ubicacion=" + ubicacion + ", punPares1=" + punPares1
				+ ", punPares2=" + punPares2 + "]";
	}		
	
	
	
}
