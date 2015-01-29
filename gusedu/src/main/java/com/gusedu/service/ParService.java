package com.gusedu.service;

import java.util.List;

import com.gusedu.model.Par;
import com.gusedu.model.Punto;

public interface ParService {
	
	public List<Par> getAllPares();	

	public Par parById(Integer id);
	
	public Boolean savePar(Par par);
	
	public Boolean updatePar(Par par);
	
	public Boolean deletePar(Par par);
	
}
