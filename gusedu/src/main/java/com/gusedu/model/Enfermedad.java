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
@Table(name="ENFERMEDAD")
public class Enfermedad {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "ENF_CODIGO")
    private Integer idEnfermedad;
	
    @Column(nullable = true, name = "ENF_NOMBRE")
    private String nombre;
    
	@OneToMany(mappedBy="expEnfermedad")
	private Collection<EnfermedadPar> enfExp;

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
        
	
}
