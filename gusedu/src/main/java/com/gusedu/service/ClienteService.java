package com.gusedu.service;

import java.util.List;

import com.gusedu.model.Cliente;

public interface ClienteService {

	public boolean saveCliente(Cliente cliente);
	
	public boolean updateCliente(Cliente cliente);
	
	public boolean deleteCliente(Cliente cliente);
	
	public List<Cliente> getClientes();	
	
	public Cliente getClienteById(Integer idCliente);
	
	public List<Cliente> getClientesPacientes();
	
	public Cliente getClienteByIdPersona(Integer idPersona);
		
	
}
