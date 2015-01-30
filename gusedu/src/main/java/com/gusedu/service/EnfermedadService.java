package com.gusedu.service;

import java.util.List;

import com.gusedu.model.Enfermedad;
import com.gusedu.model.EnfermedadPar;
import com.gusedu.model.Par;

public interface EnfermedadService {

	public List<Enfermedad> getAll();
	
	public boolean saveEnfermedad(Enfermedad enfermedad);
	
	public boolean saveEnfermedadPar(EnfermedadPar enfermedadPar);

	public boolean deleteEnfermedadPar(EnfermedadPar enfermedadPar); 
	
	public Enfermedad getById(Integer idEnfermedad);
	
	public EnfermedadPar getByParameters(Enfermedad enfermedad, Par par);
	
}
