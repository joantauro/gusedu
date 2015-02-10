package com.gusedu.service;

import com.gusedu.model.HistoriaClinica;
import com.gusedu.model.Visita;

public interface HistoriaClinicaService {

	public boolean saveHistoriaClinica(HistoriaClinica historiaClinica);
	
	public HistoriaClinica getHistoriaByVisita(Visita visita);
	
}
