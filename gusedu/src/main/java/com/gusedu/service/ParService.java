package com.gusedu.service;

import java.util.List;

import com.gusedu.model.Enfermedad;
import com.gusedu.model.Par;
import com.gusedu.model.Punto;
import com.gusedu.model.Sintoma;

public interface ParService {
	
	public List<Par> getAllPares();	

	public Par parById(Integer id);
	
	public Boolean savePar(Par par);
	
	public Boolean updatePar(Par par);
	
	public Boolean deletePar(Par par);
	
	public List<Enfermedad> getEnfermedades(Par par);
	
	public List<Sintoma> getSintomas(Par par);
	
	public Par parByPuntos(Punto p1, Punto p2);
	
}
