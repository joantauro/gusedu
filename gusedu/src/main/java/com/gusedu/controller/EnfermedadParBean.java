package com.gusedu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gusedu.model.Enfermedad;
import com.gusedu.model.EnfermedadPar;
import com.gusedu.service.EnfermedadService;
import com.gusedu.service.ParService;

@Component
@Scope(value="session")
public class EnfermedadParBean {

	@Autowired
	EnfermedadService enfermedadService;
	
	@Autowired
	ParService parService;
	
	private Enfermedad enfermedad;
	private List<Enfermedad> enfermedades;	
	private List<EnfermedadPar> enfermedadesPar;
	
	public EnfermedadParBean(){		
		enfermedad = new Enfermedad(); 
	}

	public Enfermedad getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}

	public List<Enfermedad> getEnfermedades() {
		return enfermedades;
	}

	public void setEnfermedades(List<Enfermedad> enfermedades) {
		this.enfermedades = enfermedades;
	}

	public List<EnfermedadPar> getEnfermedadesPar() {
		return enfermedadesPar;
	}

	public void setEnfermedadesPar(List<EnfermedadPar> enfermedadesPar) {
		this.enfermedadesPar = enfermedadesPar;
	}

	public List<Enfermedad> autoComplete(String query){
		List<Enfermedad> allEnfermedades = enfermedadService.getAll();
		List<Enfermedad> enfermedadesFiltradas = new ArrayList<>();
		
		for (int i = 0; i < allEnfermedades.size(); i++) {
			Enfermedad enfermedad = allEnfermedades.get(i);
			if (enfermedad.getNombre().toLowerCase().startsWith(query)) {
				enfermedadesFiltradas.add(enfermedad);
			}
		}
		return enfermedadesFiltradas;
	}

	public String cargarEnfermedad(){
		enfermedadesPar = parService.parByEnfermedad(enfermedad);
		return "pm:mostrarPares?transition=flip";
	}		
	
	public String toEnfermedadPar(){
		enfermedad = new Enfermedad();
		return "pm:enfermedadPar?transition=flip";
	}
	
}
