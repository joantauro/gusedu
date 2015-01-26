package com.gusedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gusedu.model.Cliente;
import com.gusedu.service.PersonaService;
import com.gusedu.util.StaticUtil;

@Controller
public class VisitaBean {

	@Autowired
	PersonaService personaService;
	
	private String busquedaDni;
	private Cliente busquedaCliente;
	
	public VisitaBean(){
		busquedaCliente = new Cliente();
		busquedaDni = "";
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
		return "pm:registroVisita2?transition=flip";
	}
	
}
