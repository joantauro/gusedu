package com.gusedu.service;

import java.util.List;

import com.gusedu.model.Punto;

public interface PuntoService {

	public List<Punto> getAllPuntos();
	
	public List<Punto> getAllOrdenAlfabeticoAsc();
	
	public List<Punto> getAllOrdenAlfabeticoDesc();
	
	public List<Punto> getAllOrdenGoiz();
	
	public Punto puntoById(Integer id);
	
	public Boolean savePunto(Punto punto);
	
	public Boolean updatePunto(Punto punto);
	
	public Boolean deletePunto(Punto punto);
	
	public Punto puntoByNombre(String nombrePunto);
	
}
