package com.gusedu.controller;

import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gusedu.model.Cliente;
import com.gusedu.model.Terapia;
import com.gusedu.model.Visita;
import com.gusedu.service.ClienteService;
import com.gusedu.service.PersonaService;
import com.gusedu.service.TerapiaService;
import com.gusedu.service.VisitaService;
import com.gusedu.util.StaticUtil;

@Controller
public class VisitaBean {

	@Autowired
	PersonaService personaService;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	VisitaService visitaService;
	
	@Autowired
	TerapiaService terapiaService;
	
	private String busquedaDni;
	private Cliente busquedaCliente;
	
	private Boolean esPresencial;
	private Integer prioridad;

	private List<Visita> visitasPaciente;
	private Visita visita;
	
	
	private Visita visitaSeleccionada;
	private List<Terapia> terapiasDeVisita;
	
		
	public VisitaBean(){
		busquedaCliente = new Cliente();
		busquedaDni = "";
		visita = new Visita();
		visitaSeleccionada = new Visita();
	}
	
	public String getBusquedaDni() {
		return busquedaDni;
	}

	public void setBusquedaDni(String busquedaDni) {
		this.busquedaDni = busquedaDni;
	}

	public Cliente getBusquedaCliente() {
		return busquedaCliente;
	}

	public void setBusquedaCliente(Cliente busquedaCliente) {
		this.busquedaCliente = busquedaCliente;
	}

	public String volverRegistroVisita(){
		busquedaDni = "";
		busquedaCliente = new Cliente();
		return "pm:registroVisita?transition=flip";
	}

	public Boolean getEsPresencial() {
		return esPresencial;
	}

	public void setEsPresencial(Boolean esPresencial) {
		this.esPresencial = esPresencial;
	}

	public List<Visita> getVisitasPaciente() {
		return visitasPaciente;
	}

	public void setVisitasPaciente(List<Visita> visitasPaciente) {
		this.visitasPaciente = visitasPaciente;
	}

	public Integer getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

	public Visita getVisita() {
		return visita;
	}

	public void setVisita(Visita visita) {
		this.visita = visita;
	}

	public Visita getVisitaSeleccionada() {
		return visitaSeleccionada;
	}

	public void setVisitaSeleccionada(Visita visitaSeleccionada) {
		this.visitaSeleccionada = visitaSeleccionada;
	}

	public List<Terapia> getTerapiasDeVisita() {
		return terapiasDeVisita;
	}

	public void setTerapiasDeVisita(List<Terapia> terapiasDeVisita) {
		this.terapiasDeVisita = terapiasDeVisita;
	}

	public String volver(){
		busquedaDni = "";
		busquedaCliente = new Cliente();
		return "index";
	}
	
	public String buscarPersona(){
		busquedaCliente = personaService.buscarPorDni(busquedaDni);
		if(busquedaCliente==null){
			StaticUtil.errorMessage("Error", "No se ha encontrado el paciente buscado");
			return null;
		}		
		visitasPaciente = visitaService.getVisitasCliente(busquedaCliente);		
		return "pm:registroVisita2?transition=flip";
	}
	
	public String registrarVisita(){
		visita.setEsPresencial(esPresencial);
		visita.setPrioridad(prioridad);
		visita.setEstado(1);
		if(visitaService.saveVisita(visita)){
			terapiasDeVisita = terapiaService.terapiasPorVisita(visita);
			visitaSeleccionada = visita;
			visita = new Visita();
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			context.getFlash().setKeepMessages(true);
			return "gestionVisita?faces-redirect=true";
		}else{			
			return null;
		}
	}
	
}
