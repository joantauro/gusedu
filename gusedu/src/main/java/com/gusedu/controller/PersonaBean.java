package com.gusedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gusedu.model.Persona;
import com.gusedu.service.ClienteService;
import com.gusedu.service.PersonaService;
import com.gusedu.util.StaticUtil;

@Component
@Scope(value="session")
public class PersonaBean {

	@Autowired
	PersonaService personaService;
	
	@Autowired
	ClienteService clienteService;
	
	private Persona persona;
	
	public PersonaBean(){
		persona = new Persona();						
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	//Metodo para registrar un nuevo paciente
	public String registroPaciente(){		
		//Guarda la persona en la base de datos
		if(personaService.registroPaciente(persona)){
			//Crea nuevamente la instancia de persona
			persona = new Persona();
			//Muestra mensajes de �xito
			StaticUtil.correctMesage("�xito", "Se ha registrado correctamente al paciente");
			StaticUtil.keepMessages();
			//Redirecci�n
			return "index?faces-redirect=true";			
		}else{			
			return null;
		}
	}
		
	//Metodo para cancelar el registro de la persona
	public String cancelar(){
		//Crea nuevamente la instancia de persona
		persona = new Persona();
		return "index";
	}						
			
}
