package com.gusedu.controller;

import java.util.List;

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
