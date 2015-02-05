package com.gusedu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gusedu.model.Enfermedad;
import com.gusedu.model.Punto;
import com.gusedu.service.PuntoService;
import com.gusedu.util.StaticUtil;

@Controller
public class PuntoBean {

	@Autowired
	PuntoService puntoService;

	private Punto punto;
	private List<Punto> puntos;

	private String query;

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
		if (query != null) {
			if (!query.isEmpty()) {
				return puntos;
			}
		}
		return puntoService.getAllPuntos();
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
		return "index?faces-redirect=true";
	}

	public String backToConsultar() {
		punto = new Punto();
		return "pm:consultarPuntos?transition=flip";
	}

	public void cancelar() {
		punto = new Punto();
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String actualizarPunto() {
		if (esRepetido()) {
			StaticUtil.errorMessage("Error", "Hubo un error al a�adir el punto");			
			return null;
		}
		if (puntoService.updatePunto(punto)) {
			punto = new Punto();
			StaticUtil.correctMesage("�xito", "Se ha actualizado correctamente el punto");
			StaticUtil.keepMessages();			
			return "pm:consultarPuntos?transition=flip";
		} else {
			StaticUtil.errorMessage("Error", "Hubo un error al actualizar los datos del punto");			
			return null;
		}
	}

	public String a�adirPunto() {
		if (esRepetido()) {
			StaticUtil.errorMessage("Error", "El nombre del punto ya existe");			
			return null;
		}
		if (puntoService.savePunto(punto)) {
			StaticUtil.correctMesage("�xito", "Se ha a�adido correctamente el punto");
			StaticUtil.keepMessages();
			punto = new Punto();
			return "pm:consultarPuntos?transition=flip";
		} else {
			StaticUtil.errorMessage("Error", "Hubo un error al a�adir el punto");
			return null;
		}
	}

	public String nuevoPunto() {
		punto = new Punto();
		return "pm:nuevoPunto?transition=flip";
	}

	public boolean esRepetido() {
		List<Punto> allPuntos = puntoService.getAllPuntos();
		for (Punto p : allPuntos) {
			if (p.getNombre().toLowerCase().equals(punto.getNombre())) {
				return true;
			}
		}
		return false;
	}

	public void filtrarBusqueda() {
		puntos = puntoService.getAllPuntos();
		List<Punto> filtrados = new ArrayList<>();
		for (Punto p : puntos) {
			if (p.getNombre().toLowerCase().contains(query.toLowerCase())) {
				filtrados.add(p);
			}
		}
		puntos = filtrados;
	}

}
