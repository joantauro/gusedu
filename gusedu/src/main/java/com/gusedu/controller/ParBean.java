package com.gusedu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gusedu.model.Grupo;
import com.gusedu.model.Par;
import com.gusedu.model.Punto;
import com.gusedu.service.GrupoService;
import com.gusedu.service.ParService;
import com.gusedu.service.PuntoService;
import com.gusedu.util.StaticUtil;

@Controller
public class ParBean {

	@Autowired
	ParService parService;

	@Autowired
	PuntoService puntoService;
	
	@Autowired
	GrupoService grupoService;

	private Par par;
	private List<Par> pares;
	private List<Grupo> grupos;

	private Punto punto1;
	private Punto punto2;
	
	private Grupo grupoSeleccionado;

	public ParBean() {
		par = new Par();
		punto1 = new Punto();
		punto2 = new Punto();
		grupoSeleccionado = new Grupo();
	}

	public Par getPar() {
		return par;
	}

	public void setPar(Par par) {
		this.par = par;
	}

	public Punto getPunto1() {
		return punto1;
	}

	public void setPunto1(Punto punto1) {
		this.punto1 = punto1;
	}

	public Punto getPunto2() {
		return punto2;
	}

	public void setPunto2(Punto punto2) {
		this.punto2 = punto2;
	}

	public List<Par> getPares() {
		pares = parService.getAllPares();
		return pares;
	}

	public void setPares(List<Par> pares) {
		this.pares = pares;
	}

	public ParService getParService() {
		return parService;
	}

	public void setParService(ParService parService) {
		this.parService = parService;
	}

	public PuntoService getPuntoService() {
		return puntoService;
	}

	public void setPuntoService(PuntoService puntoService) {
		this.puntoService = puntoService;
	}

	public Grupo getGrupoSeleccionado() {
		return grupoSeleccionado;
	}

	public void setGrupoSeleccionado(Grupo grupoSeleccionado) {
		this.grupoSeleccionado = grupoSeleccionado;
	}

	public List<Grupo> getGrupos() {
		grupos = grupoService.getAllGrupos();
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public String backToConsultar() {
		par = new Par();
		punto1 = new Punto();
		punto2 = new Punto();
		grupoSeleccionado = new Grupo();
		return "pm:consultarPares?transition=flip";
	}

	public String toRegistrar() {
		par = new Par();
		punto1 = new Punto();
		punto2 = new Punto();
		return "pm:nuevoPar?transition=flip";
	}

	public List<Punto> autoCompletar(String query) {
		List<Punto> allPuntos = puntoService.getAllPuntos();
		List<Punto> puntosFiltrados = new ArrayList<Punto>();

		for (int i = 0; i < allPuntos.size(); i++) {
			Punto punto = allPuntos.get(i);
			if (punto.getNombre().toLowerCase().startsWith(query)) {
				puntosFiltrados.add(punto);
			}
		}
		return puntosFiltrados;
	}

	public String agregarPar() {
			par.setParGrupo(grupoSeleccionado);
			par.setParPunto1(punto1);
			par.setParPunto2(punto2);
		if (parService.savePar(par)) {
			grupoSeleccionado = new Grupo();
			punto1 = new Punto();
			punto2 = new Punto();
			StaticUtil.correctMesage("Éxito", "Se ha añadido correctamente el par");
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			context.getFlash().setKeepMessages(true);
			par = new Par();
			return "pm:agregarPar?transtion=flip";
		} else {
			StaticUtil.errorMessage("Error", "Hubo un error al añadir el par");
			return null;
		}
	}

}

