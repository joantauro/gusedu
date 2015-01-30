package com.gusedu.service;

import java.util.List;

import com.gusedu.model.Enfermedad;
import com.gusedu.model.EnfermedadPar;

public interface EnfermedadService {

	public List<Enfermedad> getAll();
	
	public Boolean saveEnfermedad(Enfermedad enfermedad);
	
	public Boolean saveEnfermedadPar(EnfermedadPar enfermedadPar);
	
}
