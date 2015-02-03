package com.gusedu.service;

import java.util.List;

import com.gusedu.model.Sintoma;

public interface SintomaService {

	public List<Sintoma> getAll();
 	
	public boolean saveSintoma(Sintoma sintoma);
	
	public boolean updateSintoma(Sintoma sintoma);
	
	public boolean deleteSintoma(Sintoma sintoma);
	
	public Sintoma getById(Integer id);
	
}
