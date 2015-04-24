package com.gusedu.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gusedu.model.Cliente;
import com.gusedu.model.HistoriaClinica;
import com.gusedu.model.Persona;
import com.gusedu.model.Producto;
import com.gusedu.model.Terapia;
import com.gusedu.model.TipoTerapia;
import com.gusedu.model.Visita;
import com.gusedu.service.ClienteService;
import com.gusedu.service.HistoriaClinicaService;
import com.gusedu.service.PersonaService;
import com.gusedu.service.TerapiaService;
import com.gusedu.service.VisitaService;
import com.gusedu.util.StaticUtil;

@Component
@Scope(value="session")
public class PrincipalBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	PersonaService personaService;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	VisitaService visitaService;
	
	@Autowired
	TerapiaService terapiaService;
	
	@Autowired
	HistoriaClinicaService historiaClinicaService;
	
	private boolean skip;//valor del wizard

	
	private Persona persona;
	
	private Cliente cliente;
	private List<Cliente> clientes;
	private String opciones;
	private String query;
	
	
	//--------Visita
	private Visita visita;
	private Terapia terapia;
	private TipoTerapia tipoTerapia;
	private HistoriaClinica historiaClinica;
	private Producto producto;
	//--------Terapia
	private Integer idTipoTerapia;
	private List<Terapia> terapiasDeVisita;
	
	//--------Historia Clinica
	
	
	public PrincipalBean()
	{
		
		persona = new Persona();
		
				
		visita = new Visita();
		visita.setVisCliente(new Cliente());
		
		terapia = new Terapia();
		terapia.setTerTipoTerapia(new TipoTerapia());
		terapia.setTerVisita(new Visita());
		
		historiaClinica = new HistoriaClinica();
	}
	
	public void NuevoRegistro()
	{
		persona = new Persona();
		
		
		visita = new Visita();
		visita.setVisCliente(new Cliente());
		
		terapia = new Terapia();
		terapia.setTerTipoTerapia(new TipoTerapia());
		terapia.setTerVisita(new Visita());
		
		historiaClinica = new HistoriaClinica();
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
	public String getOpciones() {
		return opciones;
	}

	public void setOpciones(String opciones) {
		this.opciones = opciones;
	}
	
	public Visita getVisita() {
		return visita;
	}
	
	public void setVisita(Visita visita) {
		this.visita = visita;
	}
	
	public Terapia getTerapia() {
		return terapia;
	}

	public void setTerapia(Terapia terapia) {
		this.terapia = terapia;
	}

	public TipoTerapia getTipoTerapia() {
		return tipoTerapia;
	}

	public void setTipoTerapia(TipoTerapia tipoTerapia) {
		this.tipoTerapia = tipoTerapia;
	}

	public HistoriaClinica getHistoriaClinica() {
		return historiaClinica;
	}

	public void setHistoriaClinica(HistoriaClinica historiaClinica) {
		this.historiaClinica = historiaClinica;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public Integer getIdTipoTerapia() {
		return idTipoTerapia;
	}

	public void setIdTipoTerapia(Integer idTipoTerapia) {
		this.idTipoTerapia = idTipoTerapia;
	}
	
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}
	
    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
        	System.out.println("Siguiente : "+ event.getNewStep());
            return event.getNewStep();
        }
    }
	
	//-------------------Registro de Datos de Persona
	public void registroPacienteV2(){		
		String empresa = StaticUtil.userLogged();
		
		  
		//Guarda la persona en la base de datos
		if(personaService.registroPaciente(persona, empresa)){
			registrarVisitaPrincipal();
			//Crea nuevamente la instancia de persona
			
			//NuevoRegistro();
			//actualizar();
		
		}else{			
		//	return null;
		}
	}
	
	
	//----------------------------Gestion Paciente -----------------------------
	public void preDatosClinicosPaciente2(Integer idCliente){
		StaticUtil.Eleccion(opciones);
		cliente = clienteService.getClienteById(idCliente);
		terapiasDeVisita=terapiaService.terapiasPorCliente(cliente);
		System.out.println("Lista : "+terapiasDeVisita.size());
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
	}	
	public void actualizar()
	{
		getCliente();opciones="S";
	}
	//-------------------------------------------------------------------------
	
	
	//----------------------------Visita
	
	public void registrarVisitaPrincipal()
	
	{   cliente =clienteService.lastClient();
		Visita vis =visitaService.buscarVisita(cliente);
		if(vis==null)
		{
			
			registrarVisita();
			visita =visitaService.getLastVisitaCliente(cliente);
			historiaClinica.setHclVisita(visita);
			historiaClinicaService.saveHistoriaClinica(historiaClinica);
			
			NuevoRegistro();
			//addTerapia();
		}else
		{
			visita=vis;
			//addTerapia();
		}
	}
	
	
	// Métod para registrar la visita del cliente.
	public void registrarVisita() {
		
		// Asigna datos a la visita
		visita.setEsPresencial(true);
		visita.setPrioridad(2);
		visita.setEstado(1);
		visita.setVisCliente(cliente);
		visita.setCostoTotal(0.0);
		Date fechaActual = StaticUtil.getFechaActual();
		visita.setFechaCreacion(fechaActual);
		// Guarda la visita en la base de datos
		if (visitaService.saveVisita(visita)) {
			// Carga las terapias de la visita que se abrió
			//terapiasDeVisita = terapiaService.terapiasPorVisita(visita);
			StaticUtil.correctMesage("Éxito", "Se ha registrado correctamente la visita");
			StaticUtil.keepMessages();
			
			// Redirección
			//return "gestionVisita?faces-redirect=true";
		} else {
			//return null;
		}
	}
	
	
	//----------------------------------Terapias----------------------------
	public void addTerapia() {
		RequestContext context = RequestContext.getCurrentInstance();
		// Se carga el tipoterapia segun la seleccion del combobox
		tipoTerapia = terapiaService.tteById(idTipoTerapia);
		// Se le añade el TipoTerapia a la Terapia y la fecha actal
		terapia.setTerTipoTerapia(tipoTerapia);
		terapia.setFechaRealizada(StaticUtil.getFechaActual());
		terapia.setTerVisita(visita);
		// Se guarda la terapia en la base de datos
		if (terapiaService.saveTerapia(terapia)) {
			//Se añade el costo de la terapia a la visita
			visita.setCostoTotal(visita.getCostoTotal()+terapia.getTerTipoTerapia().getCosto());
			visitaService.updateVisita(visita);
			// Se limpian los datos guardados
			tipoTerapia = new TipoTerapia();
			terapia = new Terapia();
			idTipoTerapia = null;
			tipoTerapia = new TipoTerapia();
			StaticUtil.correctMesage("Exito", "Se agregó correctamente la terapia");
			StaticUtil.keepMessages();
			context.execute("PF('dlgT').hide();");
			// Se cargan las terapias de la visita (añadiendo la actual)
			//terapiasDeVisita = terapiaService.terapiasPorVisita(visita);
			// Redireccion
		
		} else {
			StaticUtil.errorMessage("Error", "Hubo un error al agregar");

		}
	}

	//----------------------------------Historial Clinico
	public void nuevaHistoria() {
		// Se guarda la nueva historia clínica en la base de datos
		if (historiaClinicaService.saveHistoriaClinica(historiaClinica)) {
			StaticUtil.correctMesage("Éxito", "Se han guardado los datos médicos");
			StaticUtil.keepMessages();
			// Redirección
			//return "pm:gestionVisita";
		} else {
			StaticUtil.errorMessage("Error", "Hubo un error al guardar los datos");
			//return null;
		}
	}
	
	public List<Terapia> getTerapiasDeVisita() {
		return terapiasDeVisita;
	}

	public void setTerapiasDeVisita(List<Terapia> terapiasDeVisita) {
		this.terapiasDeVisita = terapiasDeVisita;
	}
	
	public void Prueba()
	{
		System.out.println("Lista : "+terapiaService.terapiasPorCliente(cliente).size());
	}
	
	public void ADD()
	{//System.out.println("ID : ");
		//re
		System.out.println("ID : "+clienteService.lastClient().getIdCliente());
	}
}
