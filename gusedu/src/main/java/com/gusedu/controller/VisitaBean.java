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
	//Metodo para volver a la primera parte del registro de una visita (Elecci�n de persona)
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

	//M�todo que limpia las entidades y regresa a registrarVisita
	public String backToRegistrarVisita() {
		this.clearEntities();
		return "registrarVisita";
	}

	//M�todo que limpia las entidades y regresa al index
	public String backToIndex() {
		this.clearEntities();
		return "index?faces-redirect=true";
	}

	//M�todo que filtra las personas seg�n apellidos o nombres
	public void filtrarPersonas() {
		//Obtener los clientes que son de tipo paciente
		clientes = clienteService.getClientesPacientes();
		//Crea una lista vacia donde se guardar�n los clientes filtrados
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
				//En caso se encuentre coincidencia se agrega a la lista
				filtrados.add(c);
			}
		}
		clientes = filtrados;
	}

	//M�todo para cargar un paciente en espec�fico seg�n el Id.
	public String cargarPaciente(int idPersona) {
		cliente = clienteService.getClienteByIdPersona(idPersona);
		visitasPaciente = visitaService.getVisitasCliente(cliente);
		return "pm:registroVisita2?transition=flip";
	}

	//M�tod para registrar la visita del cliente.
	public String registrarVisita() {
		//Asigna datos a la visita
		visita.setEsPresencial(true);
		visita.setPrioridad(2);
		visita.setEstado(1);
		visita.setVisCliente(cliente);
		Date fechaActual = StaticUtil.getFechaActual();
		visita.setFechaCreacion(fechaActual);
		//Guarda la visita en la base de datos
		if (visitaService.saveVisita(visita)) {
			//Carga las terapias de la visita que se abri�
			terapiasDeVisita = terapiaService.terapiasPorVisita(visita);
			StaticUtil.correctMesage("�xito", "Se ha registrado correctamente la visita");
			StaticUtil.keepMessages();
			//Redirecci�n
			return "gestionVisita?faces-redirect=true";
		} else {
			return null;
		}
	}

	//M�todo para cargar las visitas de un cliente espec�fico
	public String cargarVisitas(int idCliente) {
		visitasPaciente = visitaService.getVisitasCliente(clienteService.getClienteById(idCliente));
		//Redirecci�n
		return "consultarVisitas";
	}

	//M�todo para cargar una espec�fica visita seg�n el Id.
	public String cargarVisitaEspecifica(int idVisita) {
		visita = visitaService.getVisitaById(idVisita);
		//Carga las terapias de la visita seleccionada
		terapiasDeVisita = terapiaService.terapiasPorVisita(visita);
		//Redirecci�n
		return "detalleVisita?faces-redirect=true";
	}

	//M�todo previo a la creaci�n o modificaci�n de una historia cl�nica
	public String preNuevaHistoria() {
		//Verifica si a la visita ya se le asign� una historia cl�nica
		if (historiaClinicaService.getHistoriaByVisita(visita) == null) {
			//Si no se le asign�, se crea una nueva		
			historiaClinica = new HistoriaClinica();
			//Se obtiene la �ltima visita creada del cliente en consulta
			visita = visitaService.getLastVisitaCliente(visita.getVisCliente());
			//Se asigna la nueva historia cl�nica a la visita
			historiaClinica.setHclVisita(visita);
		} else {
			//En caso ya exista una, se obtiene de la base de datos
			historiaClinica = historiaClinicaService.getHistoriaByVisita(visita);
		}
		//Redireccion
		return "pm:historiaClinica";
	}

	//M�todo para crear una nueva historia cl�nica
	public String nuevaHistoria() {
		//Se guarda la nueva historia cl�nica en la base de datos
		if (historiaClinicaService.saveHistoriaClinica(historiaClinica)) {
 			StaticUtil.correctMesage("�xito", "Se han guardado los datos m�dicos");
			StaticUtil.keepMessages();
			//Redirecci�n
			return "pm:gestionVisita";
		} else {
			StaticUtil.errorMessage("Error", "Hubo un error al guardar los datos");
			return null;
		}
	}

	//M�todo previo a la creaci�n de una nueva terapia
	public String preAdd() {
		//Se crea un objeto de la clase Terapia
		terapia = new Terapia();
		//Se asigna la visita actual a la terapia
		terapia.setTerVisita(visita);
		//Se limpia el tipoterapia que se haya seleccionado previamente
		idTipoTerapia = null;		
		tipoTerapia = new TipoTerapia();
		//Redireccion
		return "pm:nuevaTerapia";
	}

	//M�todo para agregar una nueva terapia a la visita
	public String addTerapia() {
		//Se carga el tipoterapia segun la seleccion del combobox
		tipoTerapia = terapiaService.tteById(idTipoTerapia);
		//Se le a�ade el TipoTerapia a la Terapia y la fecha actal
		terapia.setTerTipoTerapia(tipoTerapia);		
		terapia.setFechaRealizada(StaticUtil.getFechaActual());
		//Se guarda la terapia en la base de datos
		if (terapiaService.saveTerapia(terapia)) {
			//Se limpian los datos guardados
			tipoTerapia = new TipoTerapia();
			terapia = new Terapia();
			idTipoTerapia = null;
			tipoTerapia = new TipoTerapia();
			StaticUtil.correctMesage("Exito", "Se agreg� correctamente la terapia");
			StaticUtil.keepMessages();
			//Se cargan las terapias de la visita (a�adiendo la actual)
			terapiasDeVisita = terapiaService.terapiasPorVisita(visita);
			//Redireccion
			return "pm:gestionVisita";
		} else {
			StaticUtil.errorMessage("Error", "Hubo un error al agregar");
			return null;
		}
	}

	//M�todo para cancelar la creaci�n de una nueva terapia
	public String cancelar() {
		//Se limpian los datos de terapia y nueva terapia
		tipoTerapia = new TipoTerapia();
		terapia = new Terapia();
		//Redirecci�n
		return "pm:gestionVisita";
	}

	//M�todo para limpiar algunas entidades usadas en el bean
	public void clearEntities() {
		cliente = new Cliente();
		terapia = new Terapia();
		visita = new Visita();
		query = "";
	}

}
