package com.gusedu.service;

import java.util.List;

import com.gusedu.model.Cliente;
import com.gusedu.model.EnfermedadTerapia;
import com.gusedu.model.EnfermedadVisita;
import com.gusedu.model.Par;
import com.gusedu.model.SintomaTerapia;
import com.gusedu.model.SintomaVisita;
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
	
	public List<Terapia> terapiasPorCliente(Cliente cliente);
	
	public boolean saveSintomaVisita(SintomaVisita sintomavista);
	
	public List<SintomaVisita> getAllSintomaxVisita(Visita vis);
	
	public boolean saveEnfermedadVisita(EnfermedadVisita enfermedadvista);
	
	public List<EnfermedadVisita> getAllEnfermedadxVisita(Visita vis);
	
	public List<TerapiaPar> getAllTerapiaParbyTerapia(Terapia terapia);
	
	public List<TerapiaPar> getAllTerapiaParbyVisita(Visita visita);
	
	public String getAllParbyAllTerapia(Terapia terapia,Par par);
	
	public List<Terapia> getAllTerapiabyCliente(Cliente cli);
	
	public Terapia lastTerapia(Cliente cli);
	
	public boolean updateTerapiaPar(TerapiaPar terapiapar);

	public List<TerapiaPar> getAllParbyCliente(Cliente cliente);
}
