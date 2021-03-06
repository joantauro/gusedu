package com.gusedu.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gusedu.model.Enfermedad;
import com.gusedu.service.EnfermedadService;
import com.gusedu.util.StaticUtil;

@Component
@Scope(value="session")
public class EnfermedadBean  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	EnfermedadService enfermedadService;

	private Enfermedad enfermedad;
	private List<Enfermedad> enfermedades;

	private String query;

	public EnfermedadBean() {
		enfermedad = new Enfermedad();
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Enfermedad getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}

	public List<Enfermedad> getEnfermedades() {
		if (query != null) {
			if (!query.isEmpty()) {
				return enfermedades;
			}
		}
		return enfermedadService.getAll();
	}
	public void listar()
	{
		enfermedades=enfermedadService.getAll();
	}
	public void setEnfermedades(List<Enfermedad> enfermedades) {
		this.enfermedades = enfermedades;
	}

	public String preAdd() {
		enfermedad = new Enfermedad();
		return "pm:agregarEnfermedad?transition=flip";
	}
	
	public void preAddWeb(){
		enfermedad = new Enfermedad();
	}

	public String add() {
		if (esRepetido()) {
			StaticUtil.errorMessage("Error", "El nombre est� duplicado");
			return null;
		}
		if (enfermedadService.saveEnfermedad(enfermedad)) {
			StaticUtil.correctMesage("�xito", "Se ha registrado correctamente la enfermedad");
			enfermedad = new Enfermedad();
			return "pm:gestionEnfermedad?transition=flip";
		} else {
			StaticUtil.errorMessage("Error", "No se pudo registrar la enfermedad");
			return null;
		}
	}
	
	public void addWeb(){
		if (esRepetido()) {
			StaticUtil.errorMessage("Error", "El nombre est� duplicado");
			enfermedad = new Enfermedad();
			return  ;
		}
		if (enfermedadService.saveEnfermedad(enfermedad)) {
			StaticUtil.correctMesage("�xito", "Se ha registrado correctamente la enfermedad");
			enfermedad = new Enfermedad();
		 
		} else {
			StaticUtil.errorMessage("Error", "No se pudo registrar la enfermedad");
 
		}
	}

	public String preUpdate(int idEnfermedad) {
		enfermedad = enfermedadService.getById(idEnfermedad);
		return "pm:editarEnfermedad?transition=flip";
	}
	
	public void preUpdateWeb(int idEnfermedad) {
		enfermedad = enfermedadService.getById(idEnfermedad);
	}

	public String update() {
		if (enfermedadService.updateEnfermedad(enfermedad)) {
			StaticUtil.correctMesage("�xito", "Se ha actualizado correctamente la enfermedad");
			return "pm:gestionEnfermedad?transition=flip";
		} else {
			StaticUtil.errorMessage("Error", "No se pudo actualizar la enfermedad");
			return null;
		}
	}
	
	public void updateWeb() {
		if (enfermedadService.updateEnfermedad(enfermedad)) {
			StaticUtil.correctMesage("�xito", "Se ha actualizado correctamente la enfermedad");
			enfermedad = new Enfermedad();
			listar();
		} else {
			StaticUtil.errorMessage("Error", "No se pudo actualizar la enfermedad");
 
		}
	}

	public void preDelete(int idEnfermedad) {
		enfermedad = enfermedadService.getById(idEnfermedad);
	}

	public void delete() {
		if(enfermedadService.deleteEnfermedad(enfermedad)){
			StaticUtil.correctMesage("�xito", "Se ha actualizado correctamente la enfermedad");	
			listar();
		}else{
			StaticUtil.errorMessage("Error", "No se pudo eliminar la enfermedad");			
		}		
		enfermedad = new Enfermedad();
	}

	public void cancelar() {
		enfermedad = new Enfermedad();
	}

	public boolean esRepetido() {
		boolean repetido = false;
		for (Enfermedad e : enfermedadService.getAll()) {
			if (e.getNombre().equalsIgnoreCase(enfermedad.getNombre())) {
				repetido = true;
				return repetido;
			}
		}
		return repetido;
	}

	public void filtrarBusqueda() {
		enfermedades = enfermedadService.getAll();
		List<Enfermedad> filtrados = new ArrayList<>();
		for (Enfermedad e : enfermedades) {
			if (e.getNombre().toLowerCase().contains(query.toLowerCase())) {
				filtrados.add(e);
			}
		}
		enfermedades = filtrados;
	}

}
