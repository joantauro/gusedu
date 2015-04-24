package com.gusedu.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gusedu.model.Cliente;
import com.gusedu.model.HistoriaClinica;
import com.gusedu.model.Producto;
import com.gusedu.model.ProductoVisita;
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
	
	
	private Cliente cliente;
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
	// (Elecci�n de persona)
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

	// M�todo que limpia las entidades y regresa a registrarVisita
	public String backToRegistrarVisita() {
		this.clearEntities();
		return "registrarVisita";
	}

	// M�todo que limpia las entidades y regresa al index
	public String backToIndex() {
		this.clearEntities();
		return "index?faces-redirect=true";
	}

	// M�todo que filtra las personas seg�n apellidos o nombres
	public void filtrarPersonas() {
		String empresa = StaticUtil.userLogged();
		// Obtener los clientes que son de tipo paciente
		clientes = clienteService.getClientesPacientesByUsuario(empresa);
		// Crea una lista vacia donde se guardar�n los clientes filtrados
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

	// M�todo para cargar un paciente en espec�fico seg�n el Id.
	public String cargarPaciente(int idPersona) {
		cliente = clienteService.getClienteByIdPersona(idPersona);
		visitasPaciente = visitaService.getVisitasCliente(cliente);
		return "pm:registroVisita2?transition=flip";
	}

	// M�tod para registrar la visita del cliente.
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
			// Carga las terapias de la visita que se abri�
			terapiasDeVisita = terapiaService.terapiasPorVisita(visita);
			StaticUtil.correctMesage("�xito", "Se ha registrado correctamente la visita");
			StaticUtil.keepMessages();
			// Redirecci�n
			return "gestionVisita?faces-redirect=true";
		} else {
			return null;
		}
	}

	// M�todo para cargar las visitas de un cliente espec�fico
	public String cargarVisitas(int idCliente) {
		visitasPaciente = visitaService.getVisitasCliente(clienteService.getClienteById(idCliente));
		// Redirecci�n
		return "consultarVisitas";
	}

	// M�todo para cargar una espec�fica visita seg�n el Id.
	public String cargarVisitaEspecifica(int idVisita) {
		visita = visitaService.getVisitaById(idVisita);
		// Carga las terapias de la visita seleccionada y los productos
		terapiasDeVisita = terapiaService.terapiasPorVisita(visita);
		productosDeVisita = productoService.getAllProductosByVisita(visita);
		// Redirecci�n
		return "detalleVisita?faces-redirect=true";
	}

	// M�todo previo a la creaci�n o modificaci�n de una historia cl�nica
	public String preNuevaHistoria() {
		// Verifica si a la visita ya se le asign� una historia cl�nica
		if (historiaClinicaService.getHistoriaByVisita(visita) == null) {
			// Si no se le asign�, se crea una nueva
			historiaClinica = new HistoriaClinica();
			// Se obtiene la �ltima visita creada del cliente en consulta
			visita = visitaService.getLastVisitaCliente(visita.getVisCliente());
			// Se asigna la nueva historia cl�nica a la visita
			historiaClinica.setHclVisita(visita);
		} else {
			// En caso ya exista una, se obtiene de la base de datos
			historiaClinica = historiaClinicaService.getHistoriaByVisita(visita);
		}
		// Redireccion
		return "pm:historiaClinica";
	}

	// M�todo para crear una nueva historia cl�nica
	public String nuevaHistoria() {
		// Se guarda la nueva historia cl�nica en la base de datos
		if (historiaClinicaService.saveHistoriaClinica(historiaClinica)) {
			StaticUtil.correctMesage("�xito", "Se han guardado los datos m�dicos");
			StaticUtil.keepMessages();
			// Redirecci�n
			return "pm:gestionVisita";
		} else {
			StaticUtil.errorMessage("Error", "Hubo un error al guardar los datos");
			return null;
		}
	}

	// M�todo previo a la creaci�n de una nueva terapia
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

	// M�todo previo a a�adir un nuevo producto a la terapia
	public String preAddProducto() {
		producto = new Producto();
		return "pm:nuevoProducto";
	}

	// M�todo para cargar el detalle de un producto
	public String cargarProducto(int idProducto) {
		producto = productoService.getProductoById(idProducto);
		return "pm:detalleProducto";
	}

	// M�todo previo a agregar un producto
	public String preAddProductoVisita(int idProducto) {
		producto = productoService.getProductoById(idProducto);
		return "pm:addProducto";
	}

	// M�todo para calcular el costo parcial de un producto
	public void calculaCostoParcial() {
		try {
			if (cantidadProducto > 0) {
				costoParcial = cantidadProducto * (producto.getCostoUnitario());
			} else {
				costoParcial = 0.0;
			}
		} catch (NumberFormatException ex) {
			System.out.print("Error, no se ha insertado un n�mero");
		}
	}

	// M�todo para agregar el ProductoVisita
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
			StaticUtil.correctMesage("�xito", "Se ha registrado correctamente el producto");
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

	// M�todo que filtra los productos seg�n nombre
	public void filtrarProductos() {
		// Obtener todos los productos
		allProductos = productoService.getAllProductos();
		// Crea una lista vacia donde se guardar�n los clientes filtrados
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

	// M�todo para agregar una nueva terapia a la visita
	public String addTerapia() {
		// Se carga el tipoterapia segun la seleccion del combobox
		tipoTerapia = terapiaService.tteById(idTipoTerapia);
		// Se le a�ade el TipoTerapia a la Terapia y la fecha actal
		terapia.setTerTipoTerapia(tipoTerapia);
		terapia.setFechaRealizada(StaticUtil.getFechaActual());
		// Se guarda la terapia en la base de datos
		if (terapiaService.saveTerapia(terapia)) {
			//Se a�ade el costo de la terapia a la visita
			visita.setCostoTotal(visita.getCostoTotal()+terapia.getTerTipoTerapia().getCosto());
			visitaService.updateVisita(visita);
			// Se limpian los datos guardados
			tipoTerapia = new TipoTerapia();
			terapia = new Terapia();
			idTipoTerapia = null;
			tipoTerapia = new TipoTerapia();
			StaticUtil.correctMesage("Exito", "Se agreg� correctamente la terapia");
			StaticUtil.keepMessages();
			// Se cargan las terapias de la visita (a�adiendo la actual)
			terapiasDeVisita = terapiaService.terapiasPorVisita(visita);
			// Redireccion
			return "pm:gestionVisita";
		} else {
			StaticUtil.errorMessage("Error", "Hubo un error al agregar");
			return null;
		}
	}

	// M�todo para cancelar la creaci�n de una nueva terapia
	public String cancelar() {
		// Se limpian los datos de terapia y nueva terapia
		tipoTerapia = new TipoTerapia();
		terapia = new Terapia();
		// Redirecci�n
		return "pm:gestionVisita";
	}

	// M�todo para limpiar algunas entidades usadas en el bean
	public void clearEntities() {
		cliente = new Cliente();
		terapia = new Terapia();
		visita = new Visita();
		query = "";
	}
	
	public String finalizarVisita(){
		visita.setEstado(2);
		visitaService.updateVisita(visita);
		StaticUtil.correctMesage("�xito", "Se ha finalizado exitosamente la visita");
		StaticUtil.keepMessages();
		visita = new Visita();
		return "registrarVisita?faces-redirect=true";
	}

}
