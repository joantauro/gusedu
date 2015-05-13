package com.gusedu.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;



import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gusedu.model.Persona;
import com.gusedu.service.ClienteService;
import com.gusedu.service.PersonaService;
import com.gusedu.util.StaticUtil;

@Component
@Scope(value="session")
public class PersonaBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	PersonaService personaService;
	
	@Autowired
	ClienteService clienteService;
	
	
	private Persona persona;
	
	public PersonaBean(){
		persona = new Persona();		
	
		
	}

	public void valida()
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		
		String query =fc.getExternalContext().getSessionMap().get("query").toString();
		if(StaticUtil.esSoloNumero(query))
		{
			persona.setDni(query);
			persona.setNombres("");
		}else
		{
			persona.setNombres(query.substring(0, 1).toUpperCase()+query.substring(1).toLowerCase());
			persona.setDni("");
		}
		
	}
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	//Metodo para registrar un nuevo paciente
	public String registroPaciente(){		
		String empresa = StaticUtil.userLogged();
		//Guarda la persona en la base de datos
		if(personaService.registroPaciente(persona, empresa)){
			//Crea nuevamente la instancia de persona
			persona = new Persona();
			//Muestra mensajes de éxito
			StaticUtil.correctMesage("Éxito", "Se ha registrado correctamente al paciente");
			StaticUtil.keepMessages();
			//Redirección
			return "index?faces-redirect=true";			
		}else{						
			return null;
		}
	}
	//--------------Esto metodo va para web------------
	public void registroPacienteV2(){		
		String empresa = StaticUtil.userLogged();
		RequestContext context = RequestContext.getCurrentInstance();
		FacesContext fc = FacesContext.getCurrentInstance();
		PacienteBean objetoBean =(PacienteBean) fc.getExternalContext().getSessionMap().get("pacienteBean");
		//Guarda la persona en la base de datos
		if(personaService.registroPaciente(persona, empresa)){
			
			//Crea nuevamente la instancia de persona
			persona = new Persona();
			//Muestra mensajes de éxito
			StaticUtil.correctMesage("Éxito", "Se ha registrado correctamente al paciente");
			StaticUtil.keepMessages();
			objetoBean.listado();
			context.execute("PF('dlg1').hide();");			
		}else{			
			System.out.println("Error Fatal");
			persona = new Persona();
		}
	}
	public void cancel()
	{
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlg1').hide();");
		persona = new Persona();
	}
	//----------------	
	//Metodo para cancelar el registro de la persona
	public String cancelar(){
		//Crea nuevamente la instancia de persona
		persona = new Persona();
		return "index";
	}					
			
}
