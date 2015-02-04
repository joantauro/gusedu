package com.gusedu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gusedu.model.Enfermedad;
import com.gusedu.service.EnfermedadService;
import com.gusedu.util.StaticUtil;

@Controller
public class EnfermedadBean {

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
		if(query != null){
			if(!query.isEmpty()){
				return enfermedades;
			}
		}
		return enfermedadService.getAll();
	}

	public void setEnfermedades(List<Enfermedad> enfermedades) {
		this.enfermedades = enfermedades;
	}

	public String add() {
		if (esRepetido()) {
			StaticUtil.errorMessage("Error", "El nombre está duplicado");
			enfermedad = new Enfermedad();
			return "pm:agregarEnfermedad?faces-redirect=true";
		}
		if (enfermedadService.saveEnfermedad(enfermedad)) {
			StaticUtil.correctMesage("Éxito",
					"Se ha registrado correctamente la enfermedad");
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			context.getFlash().setKeepMessages(true);
			return "pm:gestionEnfermedad?faces-redirect=true";
		} else {
			StaticUtil.errorMessage("Error",
					"No se pudo registrar la enfermedad");
			return "pm:agregarEnfermedad?faces-redirect=true";
		}
	}

	public String preAdd() {
		enfermedad = new Enfermedad();
		return "pm:agregarEnfermedad?transition=flip";
	}

	public String preUpdate(int idEnfermedad) {
		enfermedad = new Enfermedad();
		enfermedad = enfermedadService.getById(idEnfermedad);
		return "pm:editarEnfermedad?transition=flip";
	}

	public String update() {
		if (enfermedadService.updateEnfermedad(enfermedad)) {
			StaticUtil.correctMesage("Éxito",
					"Se ha actualizado correctamente la enfermedad");
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			context.getFlash().setKeepMessages(true);
			return null;
		} else {
			StaticUtil.errorMessage("Error",
					"No se pudo actualizad la enfermedad");
			return "pm:editarEnfermedad?faces-redirect=true";
		}
	}

	public void preDelete(int idEnfermedad) {
		enfermedad = enfermedadService.getById(idEnfermedad);
	}

	public void delete() {
		enfermedadService.deleteEnfermedad(enfermedad);
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
