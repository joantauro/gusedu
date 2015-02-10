package com.gusedu.service;

import java.util.List;

import com.gusedu.model.Sintoma;
import com.gusedu.model.SintomaTerapia;

public interface SintomaService {

	public List<Sintoma> getAll();
 	
	public boolean saveSintoma(Sintoma sintoma);
	
	public boolean updateSintoma(Sintoma sintoma);
	
	public boolean deleteSintoma(Sintoma sintoma);
	
	public Sintoma getById(Integer id);
	
	public Sintoma getByNombre(String nombre);
	
	//SintomaTerapia
	
	public boolean saveSintomaTerapia(SintomaTerapia sintomaTerapia);
	
}
