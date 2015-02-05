package com.gusedu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gusedu.model.Cliente;
import com.gusedu.model.Sintoma;
import com.gusedu.service.ClienteService;
import com.gusedu.util.StaticUtil;

@Controller
public class PacienteBean {

	@Autowired
	ClienteService clienteService;

	private Cliente cliente;
	private List<Cliente> clientes;

	private String query;

	public PacienteBean() {
		cliente = new Cliente();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public List<Cliente> getClientes() {
		if (query != null) {
			if (!query.isEmpty()) {
				return clientes;
			}
		}
		return clienteService.getClientesPacientes();
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public String preEditarPaciente(Integer idCliente) {
		cliente = clienteService.getClienteById(idCliente);
		return "editarCliente?faces-redirect=true";
	}

	public String editar() {
		if (clienteService.updateCliente(cliente)) {
			StaticUtil
					.correctMesage("Éxito", "Se ha actualizado correctamente");
			StaticUtil.keepMessages();
			return "consultarPacientes?faces-redirect=true";
		} else {
			StaticUtil.errorMessage("Error", "No se pudo actualizar");
			return null;
		}
	}

	public String cancelar() {
		cliente = new Cliente();
		return "consultarPacientes?faces-redirect=true";
	}

	public void filtrarBusqueda() {
		clientes = clienteService.getClientesPacientes();
		List<Cliente> filtrados = new ArrayList<>();
		for (Cliente c : clientes) {
			if (c.getCliPersona().getApellidoPaterno().toLowerCase()
					.contains(query.toLowerCase())
					|| c.getCliPersona().getApellidoMaterno().toLowerCase()
							.contains(query.toLowerCase())
					|| c.getCliPersona().getDni().toString().contains(query)
					|| c.getCliPersona().getNombres().toLowerCase()
							.contains(query.toLowerCase())) {
				filtrados.add(c);
			}
		}
		clientes = filtrados;
	}

}
