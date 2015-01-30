package com.gusedu.controller;

import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gusedu.model.Cliente;
import com.gusedu.model.Persona;
import com.gusedu.model.Terapia;
import com.gusedu.service.ClienteService;
import com.gusedu.service.PersonaService;
import com.gusedu.util.StaticUtil;

@Controller
public class PersonaBean {

	@Autowired
	PersonaService personaService;
	
	@Autowired
	ClienteService clienteService;
	
	private Persona persona;
	private List<Persona> personas;
	private List<Cliente> pacientes;
	
	private Persona personaSeleccionada;	
	private List<Terapia> terapiasPaciente;	
	
	
	public PersonaBean(){
		persona = new Persona();
		personaSeleccionada = new Persona();					
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Persona> getPersonas() {
		personas = personaService.getPersonas();
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	public List<Cliente> getPacientes() {
		pacientes = clienteService.getClientesPacientes();
		return pacientes;
	}

	public void setPacientes(List<Cliente> pacientes) {
		this.pacientes = pacientes;
	}

	public Persona getPersonaSeleccionada() {
		return personaSeleccionada;
	}

	
	public void setPersonaSeleccionada(Persona personaSeleccionada) {
		this.personaSeleccionada = personaSeleccionada;
	}	
	
	public List<Terapia> getTerapiasPaciente() {
		return terapiasPaciente;
	}

	public void setTerapiasPaciente(List<Terapia> terapiasPaciente) {
		this.terapiasPaciente = terapiasPaciente;
	}

	public String registroPaciente(){
		if(personaService.registroPaciente(persona)){
			persona = new Persona();
			StaticUtil.correctMesage("Éxito", "Se ha registrado correctamente al paciente");
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			context.getFlash().setKeepMessages(true);
			return "index?faces-redirect=true";			
		}else{			
			return null;
		}
	}
	
	public String continuarRegistro(){
		return "pm:registroPacienteSecond?transition=flip";
	}
		
	public String cancelar(){
		persona = new Persona();
		return "index";
	}		
	
	public String cargarTerapias(int idPersona){
		personaSeleccionada = personaService.getPersonaById(idPersona);
		terapiasPaciente = personaService.terapiasPorPersona(personaSeleccionada);
		return "consultarTerapias";
	}						
			
}
