package com.gusedu.service;

import java.util.List;

import com.gusedu.model.Cliente;
import com.gusedu.model.Visita;

public interface ClienteService {

	public boolean saveCliente(Cliente cliente);
	
	public boolean updateCliente(Cliente cliente);
	
	public boolean deleteCliente(Cliente cliente);
	
	public List<Cliente> getClientes();	
	
	public List<Cliente> getClientesPacientes();		
	
}
