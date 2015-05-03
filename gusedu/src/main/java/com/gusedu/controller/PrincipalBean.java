package com.gusedu.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gusedu.model.Cliente;
import com.gusedu.model.Enfermedad;
import com.gusedu.model.EnfermedadVisita;
import com.gusedu.model.HistoriaClinica;
import com.gusedu.model.Par;
import com.gusedu.model.Producto;
import com.gusedu.model.Punto;
import com.gusedu.model.Sintoma;
import com.gusedu.model.SintomaVisita;
import com.gusedu.model.Terapia;
import com.gusedu.model.TerapiaPar;
import com.gusedu.model.TipoTerapia;
import com.gusedu.model.Visita;
import com.gusedu.service.ClienteService;
import com.gusedu.service.HistoriaClinicaService;
import com.gusedu.service.ParService;
import com.gusedu.service.PersonaService;
import com.gusedu.service.TerapiaService;
import com.gusedu.service.VisitaService;
import com.gusedu.util.StaticUtil;

@Component
@Scope(value = "session")
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

	@Autowired
	ParService parService;

	private boolean skip;// valor del wizard

	//private Persona persona;

	private Cliente cliente;
	private List<Cliente> clientes;
	private String opciones;
	private String query;

	// --------Visita
	private Visita visita;
	private Terapia terapia;
	private TipoTerapia tipoTerapia;
	private HistoriaClinica historiaClinica;
	private Producto producto;
	// --------Terapia
	private Integer idTipoTerapia;
	private List<Terapia> terapiasDeVisita;
	// --------Historia Clinica
	// ----------- Par
	private Par par;
	List<Par> result;
	private List<Par> npar;
	private List<Par> parcito;

	public int idPunto;

	public PrincipalBean() {
		 
		par = new Par();

		visita = new Visita();
		visita.setVisCliente(new Cliente());

		terapia = new Terapia();
		terapia.setTerTipoTerapia(new TipoTerapia());
		terapia.setTerVisita(new Visita());

		tipoTerapia = new TipoTerapia();

		historiaClinica = new HistoriaClinica();

		result = new ArrayList<>();
	}

	public void NuevoRegistro() {
		 

		visita = new Visita();
		visita.setVisCliente(new Cliente());

		terapia = new Terapia();
		terapia.setTerTipoTerapia(new TipoTerapia());
		terapia.setTerVisita(new Visita());

		tipoTerapia = new TipoTerapia();

		historiaClinica = new HistoriaClinica();
		opciones = "S";

	}

	public void Limpiarnpar() {
		npar.clear();
	}

	public void CancelarTerapia() {
		NuevoRegistro();
		Limpiarnpar();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlgT').hide();");
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
		clientes = clienteService.getClientesPacientesByUsuario(username);
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

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false; // reset in case user goes back
			return "confirm";
		} else {
			System.out.println("Siguiente : " + event.getNewStep());
			return event.getNewStep();
		}
	}

	// ----------------------------Gestion Paciente
	// -----------------------------
	public void preDatosClinicosPaciente2(Integer idCliente) {
		StaticUtil.Eleccion(opciones);
		cliente = clienteService.getClienteById(idCliente);
		terapiasDeVisita = terapiaService.terapiasPorCliente(cliente);
		if (opciones.equals("P")) {
			visita = visitaService.getLastVisitaCliente(cliente);
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.getExternalContext().getSessionMap().put("visita", visita);
		}
		System.out.println("Lista : " + terapiasDeVisita.size());
	}

	public void guardarDatosClinicos2() {
		RequestContext context = RequestContext.getCurrentInstance();
		if (clienteService.updateCliente(cliente)) {
			StaticUtil.correctMesage("Éxito",
					"Se ha actualizado los datos correctamente");
			StaticUtil.keepMessages();
			context.execute("PF('dlgDC').hide();");
			// return "consultarPacientes?faces-redirect=true";
		} else {
			StaticUtil.errorMessage("Error", "No se pudo actualizar");
			// return null;
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

	public void actualizar() {
		getCliente();
		opciones = "S";
	}

	// -------------------------------------------------------------------------

	// ----------------------------Visita

	@SuppressWarnings("unchecked")
	public void registrarSintoma() {
		List<Sintoma> s;
		SintomaVisita sinvis;
		FacesContext fc = FacesContext.getCurrentInstance();
		s = ((List<Sintoma>) fc.getExternalContext().getSessionMap()
				.get("listaSintoma"));

		for (int i = 0; i < s.size(); i++) {
			sinvis = new SintomaVisita();
			sinvis.setSxvVisita(visita);
			sinvis.setSxvSintoma(s.get(i));
			terapiaService.saveSintomaVisita(sinvis);
		}
	}

	@SuppressWarnings("unchecked")
	public void registrarEnfermedad() {
		List<Enfermedad> e;
		EnfermedadVisita enfvis;
		FacesContext fc = FacesContext.getCurrentInstance();
		e = ((List<Enfermedad>) fc.getExternalContext().getSessionMap()
				.get("listaEnfermedad"));
		System.out.println("Listan de Enfermedades : " + e.size());
		for (int j = 0; j < e.size(); j++) {
			enfvis = new EnfermedadVisita();
			enfvis.setExvVisita(visita);
			enfvis.setExvEnfermedad(e.get(j));
			terapiaService.saveEnfermedadVisita(enfvis);
		}

	}

	public void registrarVisitaPrincipal() {
		RequestContext context = RequestContext.getCurrentInstance();

		// cliente =clienteService.lastClient();
		Visita vis = visitaService.buscarVisita(cliente);
		if (vis == null) {

			//registrarVisita();
			visita = visitaService.getLastVisitaCliente(cliente);
			historiaClinica.setHclVisita(visita);
			historiaClinicaService.saveHistoriaClinica(historiaClinica);

			registrarSintoma();
			registrarEnfermedad();
			NuevoRegistro();
		} else {
			visita = vis;
			NuevoRegistro();
			// addTerapia();
		}
		context.execute("PF('dlgHEA').hide();");
	}

	// ------------- Registra Terapia
	public void registrarTerapias()

	{
		RequestContext context = RequestContext.getCurrentInstance();
		Visita vis = visitaService.buscarVisita(cliente);
		if (vis == null) {

		//	registrarVisita();
			visita = visitaService.getLastVisitaCliente(cliente);
			historiaClinica.setHclVisita(visita);
			historiaClinicaService.saveHistoriaClinica(historiaClinica);
			addTerapia();
			NuevoRegistro();
			Limpiarnpar();

		} else {
			visita = vis;
			addTerapia();
			NuevoRegistro();
		}
		context.execute("PF('dlgT').hide();");

	}

	// Métod para registrar la visita del cliente.
	/*public void registrarVisita() {

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
			// terapiasDeVisita = terapiaService.terapiasPorVisita(visita);
			StaticUtil.correctMesage("Éxito",
					"Se ha registrado correctamente la visita");
			StaticUtil.keepMessages();

			// Redirección
			// return "gestionVisita?faces-redirect=true";
		} else {
			// return null;
		}
	}*/

	// ----------------------------------Terapias----------------------------
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
			// Se añade el costo de la terapia a la visita
			visita.setCostoTotal(visita.getCostoTotal()
					+ terapia.getTerTipoTerapia().getCosto());
			visitaService.updateVisita(visita);
			// Se limpian los datos guardados

			// Se añade los pares
			TerapiaPar tp;
			for (int i = 0; i < result.size(); i++) {
				// paresSeleccionados
				tp = new TerapiaPar();
				tp.setTxpTerapia(terapia);
				tp.setTxpPar(result.get(i));
				terapiaService.saveTerapiaPar(tp);
				tp = new TerapiaPar();
			}

			tipoTerapia = new TipoTerapia();
			terapia = new Terapia();
			idTipoTerapia = null;
			tipoTerapia = new TipoTerapia();
			StaticUtil.correctMesage("Exito",
					"Se agregó correctamente la terapia");
			StaticUtil.keepMessages();
			context.execute("PF('dlgT').hide();");
			// Se cargan las terapias de la visita (añadiendo la actual)
			// terapiasDeVisita = terapiaService.terapiasPorVisita(visita);
			// Redireccion

		} else {
			StaticUtil.errorMessage("Error", "Hubo un error al agregar");

		}
	}

	// ----------------------------------Historial Clinico
	public void nuevaHistoria() {
		// Se guarda la nueva historia clínica en la base de datos
		if (historiaClinicaService.saveHistoriaClinica(historiaClinica)) {
			StaticUtil.correctMesage("Éxito",
					"Se han guardado los datos médicos");
			StaticUtil.keepMessages();
			// Redirección
			// return "pm:gestionVisita";
		} else {
			StaticUtil.errorMessage("Error",
					"Hubo un error al guardar los datos");
			// return null;
		}
	}

	public List<Terapia> getTerapiasDeVisita() {
		return terapiasDeVisita;
	}

	public void setTerapiasDeVisita(List<Terapia> terapiasDeVisita) {
		this.terapiasDeVisita = terapiasDeVisita;
	}

	public void Prueba() {
		System.out.println("Lista : "
				+ terapiaService.terapiasPorCliente(cliente).size());
	}

	public void ADD() {// System.out.println("ID : ");
						// re
		System.out
				.println("ID : " + clienteService.lastClient().getIdCliente());
	}

	public void insertarPar(Integer idpar) {

		par = parService.parById(idpar);

		result.add(par);
		npar = result;

		System.out.println(npar.size());
	}

	public Par getPar() {
		return par;
	}

	public void setPar(Par par) {
		this.par = par;
	}

	public List<Par> getNpar() {
		return npar;
	}

	public void setNpar(List<Par> npar) {
		this.npar = npar;
	}

	public List<Par> getParcito() {
		return parcito;
	}

	public void setParcito(List<Par> parcito) {
		this.parcito = parcito;
	}

	public void buscar(int p1) {
		Punto p = new Punto();
		p.setIdPunto(p1);
		idPunto = p1;
		parcito = parService.paresByPunto(p);
		System.out.println("Lista : " + parcito.size());
	}

	public void actualizarListaPar() {
		Punto p = new Punto();
		p.setIdPunto(idPunto);
		parcito = parService.paresByPunto(p);
		System.out.println("Cant. : " + getParcito().size());
	}

	public boolean shouldBeRendered(int idPar) {
		for (Par par : result) {
			if (par.getIdPar() == idPar) {
				// Si el id del Par ya esta entre los pares seleccionados el
				// botón no es renderizado.
				return false;
			}
		}
		return true;
	}

	public void actualizando() {
		System.out.println("Probando...");
	}
	/*
	 * public void prueba() {System.out.println("Probando");
	 * //System.out.println("¿Número? "+StaticUtil.esSoloNumero(query)); }
	 */
}
