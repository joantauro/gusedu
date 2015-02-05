package com.gusedu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gusedu.model.Enfermedad;
import com.gusedu.service.EnfermedadService;

@Controller
public class EnfermedadParBean {

	@Autowired
	EnfermedadService enfermedadService;
	
	private Enfermedad enfermedad;
	private List<Enfermedad> enfermedades;
	
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
		return "pm:mostrarPares?transition=flip";
	}
	
}
