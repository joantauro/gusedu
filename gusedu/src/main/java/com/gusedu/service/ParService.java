package com.gusedu.service;

import java.util.List;

import com.gusedu.model.Enfermedad;
import com.gusedu.model.EnfermedadPar;
import com.gusedu.model.Grupo;
import com.gusedu.model.Par;
import com.gusedu.model.Punto;
import com.gusedu.model.Sintoma;
import com.gusedu.model.SintomaPar;

public interface ParService {
	
	public List<Par> getAllPares();
	
	public List<Par> getAllParesOrderByP1();
	
	public List<Par> getAllParesOrderByP2();
	
	public List<Par> getAllParesOrderGoiz();

	public Par parById(Integer id);
	
	public Boolean savePar(Par par);
	
	public Boolean updatePar(Par par);
	
	public Boolean deletePar(Par par);
	
	public List<Enfermedad> getEnfermedades(Par par);
	
	public List<Sintoma> getSintomas(Par par);
	
	public Par parByPuntos(Punto p1, Punto p2, Grupo g);

	public List<EnfermedadPar> parByEnfermedad(Enfermedad enfermedad);
	
	public List<SintomaPar> parBySintoma(Sintoma sintoma);
	
}

