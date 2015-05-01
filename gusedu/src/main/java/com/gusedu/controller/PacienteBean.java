package com.gusedu.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gusedu.model.Cliente;
import com.gusedu.service.ClienteService;
import com.gusedu.util.StaticUtil;

@Component
@Scope(value="session")
public class PacienteBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Autowired
	ClienteService clienteService;

	private Cliente cliente;
	private List<Cliente> clientes;
	private String opciones;
	private String query;
	
	public PacienteBean() {
		cliente = new Cliente();
		opciones="S";query="";
		enviarQuery();
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
		String username = StaticUtil.userLogged();
		if (query != null) {
			if (!query.isEmpty()) {
				return clientes;
			}
		}
	    clientes=clienteService.getClientesPacientesByUsuario(username);
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public String preDatosClinicosPaciente(Integer idCliente){
		cliente = clienteService.getClienteById(idCliente);
		return "datosClinicos?faces-redirect=true";
	}
	//---------------Esto es para la parte Web
	public void preDatosClinicosPaciente2(Integer idCliente){
		StaticUtil.Eleccion(opciones);
		cliente = clienteService.getClienteById(idCliente);
	}
	
	public void guardarDatosClinicos2() {
		RequestContext context = RequestContext.getCurrentInstance();
		if (clienteService.updateCliente(cliente)) {
		 	StaticUtil.correctMesage("Éxito", "Se ha actualizado los datos correctamente");
			StaticUtil.keepMessages();
			context.execute("PF('dlgDC').hide();");
			//return "consultarPacientes?faces-redirect=true";
		} else {
			StaticUtil.errorMessage("Error", "No se pudo actualizar");
			//return null;
		}
	}
	//-------------------------------------------------------
	public String guardarDatosClinicos() {
		if (clienteService.updateCliente(cliente)) {
		 	StaticUtil.correctMesage("Éxito", "Se ha actualizado los datos correctamente");
			StaticUtil.keepMessages();
			return "consultarPacientes?faces-redirect=true";
		} else {
			StaticUtil.errorMessage("Error", "No se pudo actualizar");
			return null;
		}
	}
	
	public String preEditarPaciente(Integer idCliente) {
		cliente = clienteService.getClienteById(idCliente);
		return "editarCliente?faces-redirect=true";
	}		

	public String editar() {
		if (clienteService.updateCliente(cliente)) {
		 	StaticUtil.correctMesage("Éxito", "Se ha actualizado correctamente");
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
		String username = StaticUtil.userLogged();
		clientes = clienteService.getClientesPacientesByUsuario(username);
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
		enviarQuery();
	}	
	public void enviarQuery()
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.getExternalContext().getSessionMap().put("query", query);
		System.out.println("Enviando query....");
	}
	public void actualizar()
	{
		getCliente();opciones="S";
		//clientes =clienteService.ordenar();
		//System.out.println("Fecha : "+clientes.get(0).getCliPersona().getDni());

	}

	public String getOpciones() {
		return opciones;
	}

	public void setOpciones(String opciones) {
		this.opciones = opciones;
	}

}
