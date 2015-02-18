package com.gusedu.service;

import java.util.List;

import com.gusedu.model.EnfermedadTerapia;
import com.gusedu.model.Par;
import com.gusedu.model.SintomaTerapia;
import com.gusedu.model.Terapia;
import com.gusedu.model.TerapiaPar;
import com.gusedu.model.TipoTerapia;
import com.gusedu.model.Visita;

public interface TerapiaService {
	
	public boolean saveTerapia(Terapia terapia);
	
	public boolean updateTerapia(Terapia terapia);
	
	public boolean deleteTerapia(Terapia terapia);
	
	public List<Terapia> terapiasPorVisita(Visita visita);

	public List<TipoTerapia> getTipoTerapias();
	
	public TipoTerapia tteById(Integer idTipoTerapia);
	
	public Terapia terapiaById(Integer idTerapia);
	
	public List<EnfermedadTerapia> getEnfermedadesByTerapia(Terapia terapia);
	
	public List<SintomaTerapia> getSintomasByTerapia(Terapia terapia);
	
	public boolean saveTerapiaPar(TerapiaPar terapiaPar);	
	
	public List<Par> getTerapiaParesFromTerapia(Terapia terapia);
	
	public TerapiaPar TerapiaParByParAndTerapia(Terapia terapia, Par par);
}
