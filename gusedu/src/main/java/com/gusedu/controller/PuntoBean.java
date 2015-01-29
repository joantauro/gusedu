package com.gusedu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gusedu.model.Punto;
import com.gusedu.service.PuntoService;
import com.gusedu.util.StaticUtil;

@Controller
public class PuntoBean {

	@Autowired
	PuntoService puntoService;

	private Punto punto;
	private List<Punto> puntos;

	public PuntoBean() {
		punto = new Punto();
		puntos = new ArrayList<>();
	}

	public Punto getPunto() {
		return punto;
	}

	public void setPunto(Punto punto) {
		this.punto = punto;
	}

	public List<Punto> getPuntos() {
		puntos = puntoService.getAllPuntos();
		return puntos;
	}

	public void setPuntos(List<Punto> puntos) {
		this.puntos = puntos;
	}

	public String detallePunto(Integer id) {
		punto = puntoService.puntoById(id);
		return "pm:detallePunto?transition=flip";
	}

	public void cargarPunto(Integer id) {
		punto = puntoService.puntoById(id);
	}

	public void eliminarPunto() {
		puntoService.deletePunto(punto);
		punto = new Punto();
	}

	public String detallePuntoUpdate(Integer id) {
		punto = puntoService.puntoById(id);
		return "pm:gestionPunto?transition=flip";
	}

	public String backToIndex() {
		punto = new Punto();
		return "index";
	}

	public String backToConsultar() {
		punto = new Punto();
		return "pm:consultarPuntos?transition=flip";
	}

	public String actualizarPunto() {
		if (puntoService.updatePunto(punto)) {
			StaticUtil.correctMesage("Éxito", "Se ha actualizado correctamente el punto");
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			context.getFlash().setKeepMessages(true);
			return backToConsultar();
		} else {
			StaticUtil.errorMessage("Error", "Hubo un error al actualizar los datos del punto");
			return null;
		}
	}

	public String añadirPunto() {
		if (puntoService.savePunto(punto)) {
			StaticUtil.correctMesage("Éxito", "Se ha añadido correctamente el punto");
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			context.getFlash().setKeepMessages(true);
			return backToConsultar();
		} else {
			StaticUtil.errorMessage("Error", "Hubo un error al añadir el punto");
			return null;
		}
	}
	
	public String nuevoPunto(){
		punto = new Punto();
		return "pm:nuevoPunto?transition=flip";
	}

}
