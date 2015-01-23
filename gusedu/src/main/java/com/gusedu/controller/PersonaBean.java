package com.gusedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gusedu.model.Cliente;
import com.gusedu.model.Persona;
import com.gusedu.service.PersonaService;

@Controller
public class PersonaBean {

	@Autowired
	PersonaService personaService;
	
	private Persona persona;
	private List<Persona> personas;
	private List<Cliente> clientes;
	
	public PersonaBean(){
		persona = new Persona();
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
	
	
	
}
