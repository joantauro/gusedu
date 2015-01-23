package com.gusedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gusedu.model.Cliente;
import com.gusedu.model.Persona;
import com.gusedu.service.ClienteService;
import com.gusedu.service.PersonaService;

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
	
	
			
}
