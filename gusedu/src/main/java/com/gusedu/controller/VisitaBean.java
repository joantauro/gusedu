package com.gusedu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gusedu.model.Cliente;
import com.gusedu.model.HistoriaClinica;
import com.gusedu.model.Terapia;
import com.gusedu.model.TipoTerapia;
import com.gusedu.model.Visita;
import com.gusedu.service.ClienteService;
import com.gusedu.service.HistoriaClinicaService;
import com.gusedu.service.TerapiaService;
import com.gusedu.service.VisitaService;
import com.gusedu.util.StaticUtil;

@Controller
public class VisitaBean {

	@Autowired
	ClienteService clienteService;

	@Autowired
	VisitaService visitaService;

	@Autowired
	TerapiaService terapiaService;

	@Autowired
	HistoriaClinicaService historiaClinicaService;

	private List<Cliente> clientes;
	private List<Visita> visitasPaciente;
	private List<Terapia> terapiasDeVisita;
	private List<TipoTerapia> tipoTerapias;

	private Cliente cliente;
	private String query;
	private Boolean esPresencial;
	private Integer prioridad;
	private Integer idTipoTerapia;

	private Visita visita;
	private Terapia terapia;
	private TipoTerapia tipoTerapia;
	private HistoriaClinica historiaClinica;

	public VisitaBean() {
		cliente = new Cliente();
		visita = new Visita();
		terapia = new Terapia();
		tipoTerapia = new TipoTerapia();
		historiaClinica = new HistoriaClinica();
		query = "";
		esPresencial = null;
		prioridad = 1;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public HistoriaClinica getHistoriaClinica() {
		return historiaClinica;
	}

	public void setHistoriaClinica(HistoriaClinica historiaClinica) {
		this.historiaClinica = historiaClinica;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<TipoTerapia> getTipoTerapias() {
		tipoTerapias = terapiaService.getTipoTerapias();
		return tipoTerapias;
	}

	public void setTipoTerapias(List<TipoTerapia> tipoTerapias) {
		this.tipoTerapias = tipoTerapias;
	}

	public Integer getIdTipoTerapia() {
		return idTipoTerapia;
	}

	public void setIdTipoTerapia(Integer idTipoTerapia) {
		this.idTipoTerapia = idTipoTerapia;
	}

	public Boolean getEsPresencial() {
		return esPresencial;
	}

	public void setEsPresencial(Boolean esPresencial) {
		this.esPresencial = esPresencial;
	}

	public List<Visita> getVisitasPaciente() {
		return visitasPaciente;
	}

	public void setVisitasPaciente(List<Visita> visitasPaciente) {
		this.visitasPaciente = visitasPaciente;
	}

	public TipoTerapia getTipoTerapia() {
		return tipoTerapia;
	}

	public void setTipoTerapia(TipoTerapia tipoTerapia) {
		this.tipoTerapia = tipoTerapia;
	}

	public Integer getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
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

	public List<Terapia> getTerapiasDeVisita() {
		return terapiasDeVisita;
	}

	public void setTerapiasDeVisita(List<Terapia> terapiasDeVisita) {
		this.terapiasDeVisita = terapiasDeVisita;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String volverRegistroVisita() {
		cliente = new Cliente();
		return "pm:registroVisita?transition=flip";
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

	public String volver() {
		query = "";
		terapia = new Terapia();
		visita = new Visita();
		return "registrarVisita?faces-redirect=true";
	}

	public String backToRegistrarVisita() {
		cliente = new Cliente();
		visita = new Visita();
		terapia = new Terapia();
		esPresencial = null;
		prioridad = null;
		return "registrarVisita?faces-redirect=true";
	}

	public String backToIndex() {
		cliente = new Cliente();
		terapia = new Terapia();
		visita = new Visita();
		query = "";
		esPresencial = null;
		prioridad = null;
		return "index?faces-redirect=true";
	}

	public void filtrarPersonas() {
		clientes = clienteService.getClientesPacientes();
		List<Cliente> filtrados = new ArrayList<>();
		for (Cliente c : clientes) {
			if (c.getCliPersona().getDni().toString().toLowerCase()
					.contains(query.toLowerCase())
					|| c.getCliPersona().getApellidoPaterno().toLowerCase()
							.contains(query.toLowerCase())
					|| c.getCliPersona().getApellidoMaterno().toLowerCase()
							.contains(query.toLowerCase())
					|| c.getCliPersona().getNombres().toLowerCase()
							.contains(query.toLowerCase())) {
				filtrados.add(c);
			}
		}
		clientes = filtrados;
	}

	public String cargarPaciente(int idPersona) {
		cliente = clienteService.getClienteByIdPersona(idPersona);
		visitasPaciente = visitaService.getVisitasCliente(cliente);
		return "pm:registroVisita2?transition=flip";
	}

	public String registrarVisita() {
		visita.setEsPresencial(esPresencial);
		visita.setPrioridad(prioridad);
		visita.setEstado(1);
		visita.setVisCliente(cliente);
		Date fechaActual = StaticUtil.getFechaActual();
		visita.setFechaCreacion(fechaActual);
		if (visitaService.saveVisita(visita)) {
			terapiasDeVisita = terapiaService.terapiasPorVisita(visita);
			StaticUtil.correctMesage("Éxito",
					"Se ha registrado correctamente la visita");
			StaticUtil.keepMessages();
			return "gestionVisita?faces-redirect=true";
		} else {
			return null;
		}
	}

	public String cargarVisitas(int idCliente) {
		visitasPaciente = visitaService.getVisitasCliente(clienteService
				.getClienteById(idCliente));
		return "consultarVisitas";
	}

	public String cargarVisitaEspecifica(int idVisita) {
		visita = visitaService.getVisitaById(idVisita);
		terapiasDeVisita = terapiaService.terapiasPorVisita(visita);
		return "detalleVisita?faces-redirect=true";
	}

	// Historia clínica

	public String preNuevaHistoria() {
		if (historiaClinicaService.getHistoriaByVisita(visita) == null) {
			historiaClinica = new HistoriaClinica();
			//Trick
			visita = visitaService.getLastVisitaCliente(visita.getVisCliente());
			//
			historiaClinica.setHclVisita(visita);
		} else {
			historiaClinica = historiaClinicaService.getHistoriaByVisita(visita);
		}
		return "pm:historiaClinica";
	}

	public String nuevaHistoria() {
		if (historiaClinicaService.saveHistoriaClinica(historiaClinica)) {
			StaticUtil.correctMesage("Éxito", "Se han guardado los datos médicos");
			StaticUtil.keepMessages();
			return "pm:gestionVisita";
		} else {
			StaticUtil.errorMessage("Error", "Hubo un error al guardar los datos");
			return null;
		}

	}

	// Terapias

	public String preAdd() {
		terapia = new Terapia();
		terapia.setTerVisita(visita);
		idTipoTerapia = null;
		tipoTerapia = new TipoTerapia();
		return "pm:nuevaTerapia";

	}

	public String addTerapia() {
		tipoTerapia = terapiaService.tteById(idTipoTerapia);
		terapia.setTerTipoTerapia(tipoTerapia);
		terapia.setFechaRealizada(StaticUtil.getFechaActual());
		if (terapiaService.saveTerapia(terapia)) {
			tipoTerapia = new TipoTerapia();
			terapia = new Terapia();
			idTipoTerapia = null;
			tipoTerapia = new TipoTerapia();
			StaticUtil.correctMesage("Exito",
					"Se agregó correctamente la terapia");
			StaticUtil.keepMessages();
			terapiasDeVisita = terapiaService.terapiasPorVisita(visita);
			return "pm:gestionVisita";
		} else {
			StaticUtil.errorMessage("Error", "Hubo un error al agregar");
			return null;
		}

	}

	public String cancelar() {
		tipoTerapia = new TipoTerapia();
		terapia = new Terapia();
		return "pm:gestionVisita";
	}

}
