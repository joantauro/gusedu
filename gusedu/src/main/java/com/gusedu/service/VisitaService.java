package com.gusedu.service;

import java.util.List;

import com.gusedu.model.Cliente;
import com.gusedu.model.Visita;

public interface VisitaService {

	public boolean saveVisita(Visita visita);

	public boolean updateVisita(Visita visita);

	public boolean deleteVisita(Visita visita);

	public List<Visita> getVisitasCliente(Cliente cliente);
	
	public Visita getVisitaById(Integer idVisita);
	
	public Visita getLastVisitaCliente(Cliente cliente);
	
	public Visita buscarVisita(Cliente cliente);
}
