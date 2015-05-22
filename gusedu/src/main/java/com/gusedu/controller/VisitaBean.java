package com.gusedu.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gusedu.model.Cliente;
import com.gusedu.model.Enfermedad;
import com.gusedu.model.EnfermedadVisita;
import com.gusedu.model.HistoriaClinica;
import com.gusedu.model.Producto;
import com.gusedu.model.ProductoVisita;
import com.gusedu.model.Sintoma;
import com.gusedu.model.SintomaVisita;
import com.gusedu.model.Terapia;
import com.gusedu.model.TipoTerapia;
import com.gusedu.model.Visita;
import com.gusedu.service.ClienteService;
import com.gusedu.service.HistoriaClinicaService;
import com.gusedu.service.ProductoService;
import com.gusedu.service.TerapiaService;
import com.gusedu.service.VisitaService;
import com.gusedu.util.StaticUtil;

@Component
@Scope(value="session")
public class VisitaBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	ClienteService clienteService;

	@Autowired
	VisitaService visitaService;

	@Autowired
	TerapiaService terapiaService;

	@Autowired
	HistoriaClinicaService historiaClinicaService;

	@Autowired
	ProductoService productoService;

	private List<Cliente> clientes;
	private List<Visita> visitasPaciente;
	
	private List<Terapia> terapiasDeVisita;
	private List<ProductoVisita> productosDeVisita;
	
	private List<TipoTerapia> tipoTerapias;
	private List<Producto> allProductos;
	
	
	private Cliente cliente;private Integer idcli;
	private String query;
	private Integer idTipoTerapia;
	private String queryProducto;

	private Visita visita;
	private Terapia terapia;
	private TipoTerapia tipoTerapia;
	private HistoriaClinica historiaClinica;
	private Producto producto;

	private Double cantidadProducto;
	private Double costoParcial;

	private List<ProductoVisita> lista;
	
	private boolean valor;
	
	private Integer mostrarFormProducto;
	
	private String descripcionIMC;
	private String opciones;
	
	private Date fechaactual;
	
	public VisitaBean() {
		
		mostrarFormProducto = -1;
		cliente = new Cliente();
		visita = new Visita();
		terapia = new Terapia();
		tipoTerapia = new TipoTerapia();
		historiaClinica = new HistoriaClinica();
		query = ""; lista = new ArrayList<>();
		valor=false;descripcionIMC="";opciones="S";
		fechaactual = new Date();
	}

	
	public Integer getMostrarFormProducto() {
		return mostrarFormProducto;
	}

	public void setMostrarFormProducto(Integer mostrarFormProducto) {
		this.mostrarFormProducto = mostrarFormProducto;
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

	public List<ProductoVisita> getProductosDeVisita() {		
		return productosDeVisita;
	}

	public void setProductosDeVisita(List<ProductoVisita> productosDeVisita) {
		this.productosDeVisita = productosDeVisita;
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

	public Double getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(Double cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	public Double getCostoParcial() {
		return costoParcial;
	}

	public void setCostoParcial(Double costoParcial) {
		this.costoParcial = costoParcial;
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

	public List<Producto> getAllProductos() {
		if (queryProducto != null) {
			if (!queryProducto.isEmpty()) {
				return allProductos;
			}
		}
		return productoService.getAllProductos();
	}

	public void setAllProductos(List<Producto> allProductos) {
		this.allProductos = allProductos;
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

	// Metodo para volver a la primera parte del registro de una visita
	// (Elección de persona)
	public String volverRegistroVisita() {
		cliente = new Cliente();
		return "pm:registroVisita?transition=flip";
	}

	public String getQueryProducto() {
		return queryProducto;
	}

	public void setQueryProducto(String queryProducto) {
		this.queryProducto = queryProducto;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public List<Cliente> getClientes() {
		String empresa = StaticUtil.userLogged();
		if (query != null) {
			if (!query.isEmpty()) {
				return clientes;
			}
		}
		return clienteService.getClientesPacientesByUsuario(empresa);
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	// Método que limpia las entidades y regresa a registrarVisita
	public String backToRegistrarVisita() {
		this.clearEntities();
		return "registrarVisita";
	}

	// Método que limpia las entidades y regresa al index
	public String backToIndex() {
		this.clearEntities();
		return "index?faces-redirect=true";
	}

	// Método que filtra las personas según apellidos o nombres
	public void filtrarPersonas() {
		String empresa = StaticUtil.userLogged();
		// Obtener los clientes que son de tipo paciente
		clientes = clienteService.getClientesPacientesByUsuario(empresa);
		// Crea una lista vacia donde se guardarán los clientes filtrados
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
				// En caso se encuentre coincidencia se agrega a la lista
				filtrados.add(c);
			}
		}
		clientes = filtrados;
	}

	// Método para cargar un paciente en específico según el Id.
	public String cargarPaciente(int idPersona) {
		cliente = clienteService.getClienteByIdPersona(idPersona);
		visitasPaciente = visitaService.getVisitasCliente(cliente);
		return "pm:registroVisita2?transition=flip";
	}
	//-----------------------------------------------------------
	public void cargarPaciente2(int idCliente)
	{
		NuevaVisita();
		cliente = clienteService.getClienteById(idCliente);
		FacesContext fc = FacesContext.getCurrentInstance();
		visitasPaciente = visitaService.getVisitasCliente(cliente);
		fc.getExternalContext().getSessionMap().put("cliente", cliente);		
	}
	public void registrarVisita2() {
		FacesContext fc = FacesContext.getCurrentInstance();
		cliente =((Cliente) fc.getExternalContext().getSessionMap().get("cliente"));
		// Asigna datos a la visita
		visita.setEsPresencial(true);
		visita.setPrioridad(2);
		visita.setEstado(1);
		visita.setVisCliente(cliente);
		String usuarioCreacion = StaticUtil.userLogged();
		visita.setUsuarioCreacion(usuarioCreacion);
		visita.setCostoTotal(0.0);
		Date fechaActual = StaticUtil.getFechaActual();
		visita.setFechaCreacion(fechaActual);
		// Guarda la visita en la base de datos
		if (visitaService.saveVisita(visita)) {
			// Carga las terapias de la visita que se abrió
			//terapiasDeVisita = terapiaService.terapiasPorVisita(visita);
			StaticUtil.correctMesage("Éxito", "Se ha registrado correctamente la visita");
			StaticUtil.keepMessages();
			
			Visita vis=visitaService.getLastVisitaCliente(cliente);

			fc.getExternalContext().getSessionMap().put("visita", vis);
			fc.getExternalContext().getSessionMap().put("ultimavisita", vis);
			//fc.getExternalContext().getSessionMap().put("cliente", cliente);
			clearEntities(); 
			RequestContext.getCurrentInstance().update("formulario");

			// Redirección
			//RequestContext context = RequestContext.getCurrentInstance();
			//context.execute("PF('dlgVi').show();");
		}
	}
	
	public void registrarVisita3() {
		

		
		
		FacesContext fc = FacesContext.getCurrentInstance();
		Visita visit = (Visita) fc.getExternalContext().getSessionMap().get("vis");
		
		if(visit==null)
		{
			cliente =((Cliente) fc.getExternalContext().getSessionMap().get("cliente"));
			// Asigna datos a la visita
			visita.setEsPresencial(true);
			visita.setPrioridad(2);
			visita.setEstado(1);
			visita.setVisCliente(cliente);
			String usuarioCreacion = StaticUtil.userLogged();
			visita.setUsuarioCreacion(usuarioCreacion);
			visita.setCostoTotal(0.0);
			 
			visita.setFechaCreacion(fechaactual);
			// Guarda la visita en la base de datos
			if (visitaService.saveVisita(visita)) {
				// Carga las terapias de la visita que se abrió
				//terapiasDeVisita = terapiaService.terapiasPorVisita(visita);
				StaticUtil.correctMesage("Éxito", "Se ha registrado correctamente la visita");
				StaticUtil.keepMessages();
				clearEntities(); 
				RequestContext.getCurrentInstance().update("formulario");
			}
		}else
		{
			StaticUtil.errorMessage("Error", "Ya se registro una visita el día de hoy");
			StaticUtil.keepMessages();
		}
		
	}
	public void updateVisita(Visita visita)
	{
		visitaService.updateVisita(visita);
	}
	
	public void carga(Cliente cli)
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		cliente = cli;
		fc.getExternalContext().getSessionMap().put("cliente", cliente);
		Visita vis =visitaService.buscarVisita(cliente);
		Visita ultimavisita=new Visita();
		if(vis==null){
			/*Visita vs= new Visita();
			vs.setIdVisita(0);
			ultimavisita=vs;*/
			//System.out.println("No hay visitas el dia de hoy :/");
		}else
		{
			ultimavisita=visitaService.getLastVisitaCliente(cliente);
			//System.out.println("Ultima Visita :"+ultimavisita.getIdVisita());
		}
		fc.getExternalContext().getSessionMap().put("ultimavisita", ultimavisita);
	}
	
	public void nuevovalidador(Cliente client)
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		clearEntities();
		if(client==null)
		{
			StaticUtil.errorMessage("Error", "Seleccione un paciente");
			StaticUtil.keepMessages();
			 return;
		}
		cliente = client;
		//visitasPaciente = visitaService.getVisitasCliente(cliente);
		fc.getExternalContext().getSessionMap().put("cliente", cliente);	
		Visita vis =visitaService.buscarVisita(cliente);
		 
		if(vis==null)
		{
			registrarVisita2();
			//preNuevaHistoria2();
		}else
		{
			StaticUtil.errorMessage("Error", "Ya se registro una visita el día de hoy");
			StaticUtil.keepMessages();
		}
	}
	public void probando()
	{
		opciones="S";
	}
	
	public void AbrirPopup()
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		Visita vis = (Visita) fc.getExternalContext().getSessionMap().get("ultimavisita");
		if(vis==null)
		{
			StaticUtil.errorMessage("Error", "Por favor seleccione un paciente");
			StaticUtil.keepMessages();
			return;
		}else
		{
			if(vis.getIdVisita()==null)
			{
				StaticUtil.EleccionUnica(opciones);
				
			}else
			{
				visita=vis;
				//System.out.println("ID Visita : "+vis.getIdVisita());
				preNuevaHistoria2();
				StaticUtil.Eleccion(opciones);
				Prueba();
			}
		}
		/*if(vis.getIdVisita()==null)
		{
			StaticUtil.EleccionUnica();
		}else
		{
			visita=vis;
			System.out.println("ID Visita : "+vis.getIdVisita());
			preNuevaHistoria2();
			StaticUtil.Eleccion(opciones);
			Prueba();
		}*/
		//StaticUtil.Eleccion(opciones);
	}
	
	public void Prueba()
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		Cliente cli =((Cliente) fc.getExternalContext().getSessionMap().get("cliente"));
		Visita vis1=visitaService.getLastVisitaCliente2(cli);
		if(vis1==null)
		{
			Visita vs= new Visita();
			vs.setIdVisita(0);
			vis1=vs;
		}
		fc.getExternalContext().getSessionMap().put("penultimavisita", vis1);
	}
	
	public void validador()
	{	
		FacesContext fc = FacesContext.getCurrentInstance();
		Cliente cli =((Cliente) fc.getExternalContext().getSessionMap().get("cliente"));
		Visita vis =visitaService.buscarVisita(cli);
 
		if(vis==null)
		{
			registrarVisita2();
			preNuevaHistoria2();
		}else
		{
			StaticUtil.errorMessage("Error", "Ya se registro una visita el día de hoy");
			StaticUtil.keepMessages();
		}
	}
	
	public boolean rendered(Cliente client)
	{
		boolean result=true;
		Visita vis = visitaService.buscarVisita(client);
		if(vis!=null)
		{
			result=false;
		}
		return result;
	}
	public boolean renderedV(Cliente client)
	{
		boolean result=false;
		Visita vis = visitaService.buscarVisita(client);
		if(vis!=null)
		{
			result=true;
		}
		return result;
	}
	
	 public void lastvisita(Cliente client)
	 {
		    FacesContext fc = FacesContext.getCurrentInstance();
		    VisitaBean objetoBean =(VisitaBean) fc.getExternalContext().getSessionMap().get("visitaBean");
		    TerapiaBean objetoTBean =(TerapiaBean) fc.getExternalContext().getSessionMap().get("terapiaBean");
		    Visita vis = visitaService.buscarVisita(client);
		    fc.getExternalContext().getSessionMap()
			.put("vis", vis);
		    visita = new Visita();
		    if(vis==null)
		    {
		    	System.out.println("No hay visita, gg");
		    	if(opciones.equals("CV"))
		    	{
		    		registrarVisita2();
		    	}
		    }else
		    {
		    	if(opciones.equals("DM"))
				{
							
							Visita ultimavisita = new Visita();
						 
								ultimavisita = vis;
								visita=ultimavisita;
								fc.getExternalContext().getSessionMap()
								.put("ultimavisita", ultimavisita);
								preNuevaHistoria2();
								
								objetoTBean.listarsintomas();
								objetoTBean.listarenfermedades();
								
								RequestContext.getCurrentInstance().update("frame2");
							//	RequestContext.getCurrentInstance().execute("PF('dlgHEA').show();");
						
				}
				if(opciones.equals("T"))
				{
					//Visita vis = visitaService.buscarVisita(client);
					Visita ultimavisita = new Visita();
				 
						ultimavisita = vis;
						visita=ultimavisita;
						fc.getExternalContext().getSessionMap()
						.put("ultimavisita", ultimavisita);
						preNuevaHistoria2();
						
						//objetoTBean.llenamatriz();
						objetoBean.Prueba();
						
						RequestContext.getCurrentInstance().update("frame3");
				 
				}
				/*if(opciones.equals("P"))
				{
					//objetoBean.listar();
					//RequestContext.getCurrentInstance().update("frame3");
				}*/
		    }
		    if(opciones.equals("HT"))
			{
				objetoTBean.llenamatriz();
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('dlgHTe').show();");
				RequestContext.getCurrentInstance().update("frame5");
			}
			if(opciones.equals("HV"))
			{
				objetoBean.ListarVisitas();
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('dlgHV').show();");
				RequestContext.getCurrentInstance().update("frame4");
			}
			if(opciones.equals("DP"))
			{
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('dlgDP').show();");
			}
			if(opciones.equals("P"))
			{
				Visita ultimavisita = new Visita();
				 
				ultimavisita = vis;
				visita=ultimavisita;
				fc.getExternalContext().getSessionMap()
				.put("ultimavisita", ultimavisita);
			}
			
			
			
	 }

	public void validadorUnico(Integer idCliente)
	{
		idcli=idCliente;
		FacesContext fc = FacesContext.getCurrentInstance();
		//fc.getExternalContext().getSessionMap().put("idCliente", idCliente);
		
		StaticUtil.Eleccion(opciones);
		clearEntities();
		cliente = clienteService.getClienteById(idCliente);
		Visita vis =visitaService.buscarVisita(cliente);		
		Visita ultimavisita = new Visita();
		ultimavisita=visitaService.getLastVisitaCliente(cliente);	
		
		if(ultimavisita==null)
		{
			Visita vs= new Visita();
			vs.setIdVisita(0);
			ultimavisita=vs;
		}
		
		fc.getExternalContext().getSessionMap().put("ultimavisita", ultimavisita);
		if(vis==null)
		{
			registrarVisita2();
			visita=((Visita) fc.getExternalContext().getSessionMap().get("visita"));
			preNuevaHistoria2();		
		}else{
			visita= vis;
			//terapiasDeVisita = terapiaService.terapiasPorCliente(cliente);
			//visitasPaciente = visitaService.getVisitasCliente(cliente);
			fc.getExternalContext().getSessionMap().put("visita", visita);
			TerapiaBean objetoTBean =(TerapiaBean) fc.getExternalContext().getSessionMap().get("terapiaBean");
			objetoTBean.listarsintomas();
			objetoTBean.listarenfermedades();
			preNuevaHistoria2();//Aca hay errores , ojo revisar
		}
		
	}
	
	public void ListarTerapias()
	{
		
		terapiasDeVisita = terapiaService.terapiasPorCliente(cliente);
	}
	public void ListarVisitas()
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		Cliente client =((Cliente) fc.getExternalContext().getSessionMap().get("cliente"));
		visitasPaciente = visitaService.getVisitasCliente(client);
		System.out.println(visitasPaciente.size());
	}
	
	
	public void ultimavisita()
	{
		//FacesContext fc = FacesContext.getCurrentInstance();
		//fc.getExternalContext().getSessionMap().get("visita");
		visita=visitaService.getLastVisitaCliente(cliente);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.getExternalContext().getSessionMap().put("visita", visita);
		preNuevaHistoria2();
	}
	//--------------------------------------------------------------------------------
	// Métod para registrar la visita del cliente.
	public String registrarVisita() {
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
			terapiasDeVisita = terapiaService.terapiasPorVisita(visita);
			StaticUtil.correctMesage("Éxito", "Se ha registrado correctamente la visita");
			StaticUtil.keepMessages();
			// Redirección
			return "gestionVisita?faces-redirect=true";
		} else {
			return null;
		}
	}

	// Método para cargar las visitas de un cliente específico
	public String cargarVisitas(int idCliente) {
		visitasPaciente = visitaService.getVisitasCliente(clienteService.getClienteById(idCliente));
		// Redirección
		return "consultarVisitas";
	}

	// Método para cargar una específica visita según el Id.
	public String cargarVisitaEspecifica(int idVisita) {
		visita = visitaService.getVisitaById(idVisita);
		// Carga las terapias de la visita seleccionada y los productos
		terapiasDeVisita = terapiaService.terapiasPorVisita(visita);
		productosDeVisita = productoService.getAllProductosByVisita(visita);
		// Redirección
		return "detalleVisita?faces-redirect=true";
	}

	public void listar()
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		Visita vis = ((Visita) fc.getExternalContext().getSessionMap().get("ultimavisita"));
		productosDeVisita = productoService.getAllProductosByVisita(vis);
		System.out.println("Productos de Visita : "+productosDeVisita.size());
	}
	
	// Método previo a la creación o modificación de una historia clínica
	public String preNuevaHistoria() {
		// Verifica si a la visita ya se le asignó una historia clínica
		if (historiaClinicaService.getHistoriaByVisita(visita) == null) {
			// Si no se le asignó, se crea una nueva
			historiaClinica = new HistoriaClinica();
			// Se obtiene la última visita creada del cliente en consulta
			visita = visitaService.getLastVisitaCliente(visita.getVisCliente());
			// Se asigna la nueva historia clínica a la visita
			historiaClinica.setHclVisita(visita);
		} else {
			// En caso ya exista una, se obtiene de la base de datos
			historiaClinica = historiaClinicaService.getHistoriaByVisita(visita);
		}
		// Redireccion
		return "pm:historiaClinica";
	}
	
	public void preNuevaHistoria2() {
		// Verifica si a la visita ya se le asignó una historia clínica
		FacesContext fc = FacesContext.getCurrentInstance();
		Visita vis= ((Visita) fc.getExternalContext().getSessionMap().get("ultimavisita"));
		System.out.println(vis);
		if (historiaClinicaService.getHistoriaByVisita(vis) == null) {
			// Si no se le asignó, se crea una nueva
			historiaClinica = new HistoriaClinica();
			// Se obtiene la última visita creada del cliente en consulta
			//visita = visitaService.getLastVisitaCliente(vis.getVisCliente());
			// Se asigna la nueva historia clínica a la visita
			historiaClinica.setHclVisita(vis);
		} else {
			// En caso ya exista una, se obtiene de la base de datos
			historiaClinica = historiaClinicaService.getHistoriaByVisita(visita);
			System.out.println(historiaClinica.getIdHistoriaClinica());
		}
	}

	// Método para crear una nueva historia clínica
	public String nuevaHistoria() {
		// Se guarda la nueva historia clínica en la base de datos
		if (historiaClinicaService.saveHistoriaClinica(historiaClinica)) {
			StaticUtil.correctMesage("Éxito", "Se han guardado los datos médicos");
			StaticUtil.keepMessages();
			// Redirección
			return "pm:gestionVisita";
		} else {
			StaticUtil.errorMessage("Error", "Hubo un error al guardar los datos");
			return null;
		}
	}
	public void nuevaHistoria2() {
		// Se guarda la nueva historia clínica en la base de datos
		if (historiaClinicaService.saveHistoriaClinica(historiaClinica)) {
			registrarSintoma();
			registrarEnfermedad();
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('dlgHEA').hide();");
			clearEntities();
			StaticUtil.correctMesage("Éxito", "Se han guardado los datos médicos");
			StaticUtil.keepMessages();
			
			// Redirección
			
		} else {
			StaticUtil.errorMessage("Error", "Hubo un error al guardar los datos");
			
		}
	}

	// Método previo a la creación de una nueva terapia
	public String preAdd() {
		// Se crea un objeto de la clase Terapia
		terapia = new Terapia();
		// Se asigna la visita actual a la terapia
		terapia.setTerVisita(visita);
		String usuarioCreacion = StaticUtil.userLogged();		
		terapia.setUsuarioCreacion(usuarioCreacion);
		// Se limpia el tipoterapia que se haya seleccionado previamente
		idTipoTerapia = null;
		tipoTerapia = new TipoTerapia();
		//Se carga la lista de tipos de terapia
		tipoTerapias = terapiaService.getTipoTerapias();
		// Redireccion
		return "pm:nuevaTerapia";
	}

	// Método previo a añadir un nuevo producto a la terapia
	public String preAddProducto() {
		producto = new Producto();
		return "pm:nuevoProducto";
	}
	
	// Método para cargar el detalle de un producto
	public String cargarProducto(int idProducto) {
		producto = productoService.getProductoById(idProducto);
		return "pm:detalleProducto";
	}

	// Método previo a agregar un producto
	public String preAddProductoVisita(int idProducto) {
		producto = productoService.getProductoById(idProducto);
		return "pm:addProducto";
	}

	// Método para calcular el costo parcial de un producto
	public void calculaCostoParcial() {
		try {
			if (cantidadProducto > 0) {
				costoParcial = cantidadProducto * (producto.getCostoUnitario());
			} else {
				costoParcial = 0.0;
			}
		} catch (NumberFormatException ex) {
			System.out.print("Error, no se ha insertado un número");
		}
	}
	
	public void preAddProductoWeb(Integer idProducto){
		producto = productoService.getProductoById(idProducto);
		mostrarFormProducto = 1;
	}
	
	public void calculaCostoParcialWeb(Integer idProducto) {
		producto = productoService.getProductoById(idProducto);
		try {
			if (cantidadProducto > 0) {
				costoParcial = cantidadProducto * (producto.getCostoUnitario());
			} else {
				costoParcial = 0.0;
			}
		} catch (NumberFormatException ex) {
			System.out.print("Error, no se ha insertado un número");
		}
	}

	// Método para agregar el ProductoVisita
	public String addProductoToVisita() {
		if(cantidadProducto<=0){
			return null;
		}
		ProductoVisita toAdd = new ProductoVisita();
		toAdd.setCantidad(cantidadProducto);
		toAdd.setCostoParcial(costoParcial);
		toAdd.setPxvProducto(producto);
		toAdd.setPxvVisita(visita);		
		if (productoService.saveProductoVisita(toAdd)) {
			StaticUtil.correctMesage("Éxito", "Se ha registrado correctamente el producto");
			//Actualizar el stock de existencias de producto
			producto.setExistencias(producto.getExistencias()-cantidadProducto);
			productoService.updateProducto(producto);
			//Actualizar el costo total de la visita
			visita.setCostoTotal(visita.getCostoTotal()+toAdd.getCostoParcial());
			visitaService.updateVisita(visita);
			//Limpiar los datos ingresados
			costoParcial = 0.0; cantidadProducto = 0.0;			
		} else {
			return null;
		}
		productosDeVisita = productoService.getAllProductosByVisita(visita);
		return "gestionVisita";
	}

	public void addProductoToVisitaWeb() {
		if(cantidadProducto<=0){
			return;
		}
		FacesContext fc = FacesContext.getCurrentInstance();
		Visita vis = ((Visita) fc.getExternalContext().getSessionMap().get("ultimavisita"));
		ProductoVisita toAdd = new ProductoVisita();
		toAdd.setCantidad(cantidadProducto);
		toAdd.setCostoParcial(costoParcial);
		toAdd.setPxvProducto(producto);
		toAdd.setPxvVisita(vis);		
		if (productoService.saveProductoVisita(toAdd)) {
			StaticUtil.correctMesage("Éxito", "Se ha registrado correctamente el producto");
			//listar();
			//Actualizar el stock de existencias de producto
			producto.setExistencias(producto.getExistencias()-cantidadProducto);
			productoService.updateProducto(producto);
			//Actualizar el costo total de la visita
			vis.setCostoTotal(vis.getCostoTotal()+toAdd.getCostoParcial());
			visitaService.updateVisita(vis);
			//Limpiar los datos ingresados
			costoParcial = 0.0; cantidadProducto = 0.0;
			mostrarFormProducto = -1;
		} else {
			//System.out.println("ERROR, DEBUGEAR.");
		}				
	}
	
	// Método que filtra los productos según nombre
	public void filtrarProductos() {
		// Obtener todos los productos
		allProductos = productoService.getAllProductos();
		// Crea una lista vacia donde se guardarán los clientes filtrados
		List<Producto> filtrados = new ArrayList<>();
		for (Producto p : allProductos) {
			if (p.getDescripcionMedia().toLowerCase()
					.contains(queryProducto.toLowerCase())) {
				// En caso se encuentre coincidencia se agrega a la lista
				filtrados.add(p);
			}
		}
		allProductos = filtrados;
	}

	// Método para agregar una nueva terapia a la visita
	public String addTerapia() {
		// Se carga el tipoterapia segun la seleccion del combobox
		tipoTerapia = terapiaService.tteById(idTipoTerapia);
		// Se le añade el TipoTerapia a la Terapia y la fecha actal
		terapia.setTerTipoTerapia(tipoTerapia);
		terapia.setFechaRealizada(StaticUtil.getFechaActual());
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
			// Se cargan las terapias de la visita (añadiendo la actual)
			terapiasDeVisita = terapiaService.terapiasPorVisita(visita);
			// Redireccion
			return "pm:gestionVisita";
		} else {
			StaticUtil.errorMessage("Error", "Hubo un error al agregar");
			return null;
		}
	}

	// Método para cancelar la creación de una nueva terapia
	public String cancelar() {
		// Se limpian los datos de terapia y nueva terapia
		tipoTerapia = new TipoTerapia();
		terapia = new Terapia();
		// Redirección
		return "pm:gestionVisita";
	}

	// Método para limpiar algunas entidades usadas en el bean
	public void clearEntities() {
		//cliente = new Cliente();
		visita = new Visita();
		terapia = new Terapia();
		tipoTerapia = new TipoTerapia();
		historiaClinica = new HistoriaClinica();
		query = ""; 
		lista = new ArrayList<>();
		valor=false;descripcionIMC="";
		opciones="S";
	}
	
	public String finalizarVisita(){
		visita.setEstado(2);
		visitaService.updateVisita(visita);
		StaticUtil.correctMesage("Éxito", "Se ha finalizado exitosamente la visita");
		StaticUtil.keepMessages();
		visita = new Visita();
		return "registrarVisita?faces-redirect=true";
	}

	public List<ProductoVisita> getLista() {
		return lista;
	}

	public void setLista(List<ProductoVisita> lista) {
		this.lista = lista;
	}

	public void addProducto(int idProducto){
		producto = productoService.getProductoById(idProducto);
		ProductoVisita toAdd = new ProductoVisita();
		FacesContext fc = FacesContext.getCurrentInstance();
		Visita vis ;
		vis= ((Visita) fc.getExternalContext().getSessionMap().get("visita"));
		toAdd.setCantidad(cantidadProducto);
		toAdd.setCostoParcial(costoParcial);
		toAdd.setPxvProducto(producto);
		toAdd.setPxvVisita(vis);	
		lista.add(toAdd);
		if(productoService.saveProductoVisita(toAdd)){
			producto.setExistencias(producto.getExistencias()-toAdd.getCantidad());
			productoService.updateProducto(producto);
			vis.setCostoTotal(vis.getCostoTotal()+ (producto.getCostoUnitario()*toAdd.getCantidad()));
			visitaService.updateVisita(vis);
		}
	}

	public boolean isValor() {
		return valor;
	}

	public void setValor(boolean valor) {
		this.valor = valor;
	}
	
	public void NuevaVisita()
	{
		if(valor)
		{
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('dlgVi').show();");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void registrarSintoma() {
		List<Sintoma> s;
		SintomaVisita sinvis;
		FacesContext fc = FacesContext.getCurrentInstance();
		s = ((List<Sintoma>) fc.getExternalContext().getSessionMap()
				.get("listaSintoma"));
		if(s!=null)
		{
			for (int i = 0; i < s.size(); i++) {
				sinvis = new SintomaVisita();
				sinvis.setSxvVisita(visita);
				sinvis.setSxvSintoma(s.get(i));
				terapiaService.saveSintomaVisita(sinvis);
			}
		}
		 
		
	}

	
	public void calcularIMC(){
		
		double peso=0.0;
		double talla=0.0;
		if(historiaClinica.getPeso()!= null && historiaClinica.getTalla()!=null)
		{
			if(historiaClinica.getTalla()<2.50)
			{
				talla =Math.pow(historiaClinica.getTalla(), 2);
			}else
			{
				talla =Math.pow(historiaClinica.getTalla()/100, 2);
			}
			
			peso =historiaClinica.getPeso();
			double imc= peso/talla;
			
			descripcionIMC=StaticUtil.calculoIMC(imc);
			historiaClinica.setImc(imc);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void registrarEnfermedad() {
		List<Enfermedad> e;
		EnfermedadVisita enfvis;
		FacesContext fc = FacesContext.getCurrentInstance();
		e = ((List<Enfermedad>) fc.getExternalContext().getSessionMap()
				.get("listaEnfermedad"));
		//System.out.println("Listan de Enfermedades : " + e.size());
		if(e!=null)
		{
			for (int j = 0; j < e.size(); j++) {
				enfvis = new EnfermedadVisita();
				enfvis.setExvVisita(visita);
				enfvis.setExvEnfermedad(e.get(j));
				terapiaService.saveEnfermedadVisita(enfvis);
			}
		}
		

	}

	public String getDescripcionIMC() {
		return descripcionIMC;
	}

	public void setDescripcionIMC(String descripcionIMC) {
		this.descripcionIMC = descripcionIMC;
	}

	public String getOpciones() {
		return opciones;
	}

	public void setOpciones(String opciones) {
		this.opciones = opciones;
	}

	public Integer getIdcli() {
		return idcli;
	}

	public void setIdcli(Integer idcli) {
		this.idcli = idcli;
	}


	public Date getFechaactual() {
		return fechaactual;
	}


	public void setFechaactual(Date fechaactual) {
		this.fechaactual = fechaactual;
	}
}
