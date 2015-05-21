 package com.gusedu.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ENFERMEDAD")
public class Enfermedad implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "ENF_CODIGO")
	private Integer idEnfermedad;

	@Column(nullable = true, name = "ENF_NOMBRE")
	private String nombre;

	@OneToMany(mappedBy = "expEnfermedad", orphanRemoval = true)
	private Collection<EnfermedadPar> enfExp;
	
	@OneToMany(mappedBy="extEnfermedad", orphanRemoval=true)
	private Collection<EnfermedadTerapia> enfEnfermedadTerapias;

	public Integer getIdEnfermedad() {
		return idEnfermedad;
	}

	public void setIdEnfermedad(Integer idEnfermedad) {
		this.idEnfermedad = idEnfermedad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Collection<EnfermedadPar> getEnfExp() {
		return enfExp;
	}

	public void setEnfExp(Collection<EnfermedadPar> enfExp) {
		this.enfExp = enfExp;
	}

	public Collection<EnfermedadTerapia> getEnfEnfermedadTerapias() {
		return enfEnfermedadTerapias;
	}

	public void setEnfEnfermedadTerapias(
			Collection<EnfermedadTerapia> enfEnfermedadTerapias) {
		this.enfEnfermedadTerapias = enfEnfermedadTerapias;
	}

}
