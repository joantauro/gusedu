package com.gusedu.service;

import java.util.List;

import com.gusedu.model.Cliente;
import com.gusedu.model.Persona;
import com.gusedu.model.Terapia;

public interface PersonaService {
	
	public boolean savePersona(Persona persona);
	
	public boolean updatePersona(Persona persona);
	
	public boolean deletePersona(Persona persona);
	
	public List<Persona> getPersonas();
	
	public Persona getPersonaById(Integer id);
	
	public boolean registroPaciente(Persona persona, String username);
	
	public List<Terapia> terapiasPorPersona(Persona persona); 

	public Cliente buscarPorDni(String dni);
	
}
