package com.gusedu.service;

import java.util.List;

import com.gusedu.model.Enfermedad;
import com.gusedu.model.EnfermedadPar;
import com.gusedu.model.EnfermedadTerapia;
import com.gusedu.model.Par;
import com.gusedu.model.Sintoma;
import com.gusedu.model.SintomaPar;

public interface EnfermedadService {

	//Enfermedad
	
	public List<Enfermedad> getAll();
	
	public boolean saveEnfermedad(Enfermedad enfermedad);
	
	public boolean updateEnfermedad(Enfermedad enfermedad);
	
	public boolean deleteEnfermedad(Enfermedad enfermedad);
	
	public Enfermedad getById(Integer idEnfermedad);
	
	public Enfermedad getByNombre(String nombre);
	
	//EnfermedadPar
	
	public boolean saveEnfermedadPar(EnfermedadPar enfermedadPar);

	public boolean deleteEnfermedadPar(EnfermedadPar enfermedadPar); 			
	
	public EnfermedadPar getByParameters(Enfermedad enfermedad, Par par);
	
	//SintomaPar
	
	public boolean saveSintomaPar(SintomaPar sintomaPar);

	public boolean deleteSintomaPar(SintomaPar sintomaPar);
	
	public SintomaPar getByParameters(Sintoma sintoma, Par par);
	
	//EnfermedadTerapia
	
	public boolean saveEnfermedadTerapia(EnfermedadTerapia enfermedadTerapia);

	public boolean deleteEnfermedadTerapia(EnfermedadTerapia enfermedadTerapia);

	
	
	
}
