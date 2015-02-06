package com.gusedu.service;

import java.util.List;

import com.gusedu.model.Terapia;
import com.gusedu.model.TipoTerapia;
import com.gusedu.model.Visita;

public interface TerapiaService {
	
	public boolean saveTerapia(Terapia terapia);
	
	public boolean updateTerapia(Terapia terapia);
	
	public boolean deleteTerapia(Terapia terapia);
	
	public List<Terapia> terapiasPorVisita(Visita visita);

	public List<TipoTerapia> getTipoTerapias();
	
	public TipoTerapia tteById(Integer idTipoTerapia);
	
}
