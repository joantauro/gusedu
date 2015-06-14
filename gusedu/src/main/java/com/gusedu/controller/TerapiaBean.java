package com.gusedu.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gusedu.model.Cliente;
import com.gusedu.model.Enfermedad;
import com.gusedu.model.EnfermedadTerapia;
import com.gusedu.model.EnfermedadVisita;
import com.gusedu.model.Par;
import com.gusedu.model.Sintoma;
import com.gusedu.model.SintomaTerapia;
import com.gusedu.model.SintomaVisita;
import com.gusedu.model.Terapia;
import com.gusedu.model.TerapiaPar;
import com.gusedu.model.TipoTerapia;
import com.gusedu.model.Visita;
import com.gusedu.service.EnfermedadService;
import com.gusedu.service.ParService;
import com.gusedu.service.SintomaService;
import com.gusedu.service.TerapiaParService;
import com.gusedu.service.TerapiaService;
import com.gusedu.util.StaticUtil;

@Component
@Scope(value="session")
public class TerapiaBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	TerapiaService terapiaService;
	
	@Autowired
	TerapiaParService terapiaparService;

	@Autowired
	EnfermedadService enfermedadService;

	@Autowired
	SintomaService sintomaService;

	@Autowired
	ParService parService;

	private Terapia terapia;
	private List<Par> allPares;
	
	private List<EnfermedadTerapia> terEnfermedades;
	private List<SintomaTerapia> terSintomas;
	
	private List<Par> paresSugeridos;
	private List<Par> paresSeleccionados;	
	private List<Par> paresTerapia;

	private Enfermedad enfermedad;
	private Sintoma sintoma;

	private int index;
	private int sliderDolor;

	private int size;
	private String queryS;
	private String queryE;

	private Integer idTipoTerapia;
	//private List<Sintoma> listasintoma;
	//private List<Enfermedad> listaenfermedad;
	private List<SintomaVisita> listasintomaxvisita;
	private List<EnfermedadVisita> listaenfermedadvisita;
	
	private List<TerapiaPar> listarTerapiaPar;
	
	/**  Matriz de las terapias **/
	   private List<String> rowNames = new ArrayList<String>();
	   private List<String> colNames = new ArrayList<String>();
	   private ArrayList<ArrayList<ArrayList<String>>> data3D = new ArrayList<ArrayList<ArrayList<String>>>();
	   
	   private List<Terapia> listaterapia;
	   private List<TerapiaPar> listaterapiapar;
	   private List<Par> listapares;
	
	public TerapiaBean() {
		sliderDolor = 0;
		terapia = new Terapia();
		enfermedad = new Enfermedad();
		sintoma = new Sintoma();
		paresSeleccionados = new ArrayList<Par>();
		listarTerapiaPar = new ArrayList<>();
		queryS="";
		queryE="";
		//listasintoma = new ArrayList<>();
		//listaenfermedad = new ArrayList<>();
	}
	
	
	public void llenamatriz()
	{
		data3D = new ArrayList<ArrayList<ArrayList<String>>>();
		rowNames = new ArrayList<String>();
		colNames = new ArrayList<String>();
		FacesContext fc = FacesContext.getCurrentInstance();
		Cliente cli = (Cliente) fc.getExternalContext().getSessionMap()
				.get("cliente");
	
	/*	listaterapia = terapiaService.getAllTerapiabyCliente(cli);//----Consulta bd 
		Terapia terapia = terapiaService.lastTerapia(cli);        //----Consulta bd
		// terapia.setIdTerapia(87);
		listaterapiapar = terapiaService.getAllTerapiaParbyTerapia(terapia);//Consulta bd
		List<TerapiaPar> all = new ArrayList<>();
		all = terapiaService.getAllParbyCliente(cli);                       //Consulta bd
		*/
		listaterapia = new ArrayList<>();
		listapares = new ArrayList<>();
		
		List<TerapiaPar> all = new ArrayList<>();
		all = terapiaService.getAllParbyCliente(cli); 
		
		 for(int i=0;i<all.size();i++)
	      {
	          if(!listaterapia.contains(all.get(i).getTxpTerapia()))
	          {
	              listaterapia.add(all.get(i).getTxpTerapia());
	          }
	      }
	     for(int j=0;j<all.size();j++)
	      {
	          if(!listapares.contains(all.get(j).getTxpPar()))
	          {
	              listapares.add(all.get(j).getTxpPar());
	          }
	      }
		
	     for (int j = 0; j < listapares.size(); j++) {
				rowNames.add(listapares.get(j).getParPunto1()
						.getNombre()
						+ "-"
						+ listapares.get(j).getParPunto2()
								.getNombre());
			}
	/*	for (int j = 0; j < listaterapiapar.size(); j++) {
			rowNames.add(listaterapiapar.get(j).getTxpPar().getParPunto1()
					.getNombre()
					+ "-"
					+ listaterapiapar.get(j).getTxpPar().getParPunto2()
							.getNombre());
		}*/
		System.out.println(listaterapia.size());
		for (int i = 0; i < listaterapia.size(); i++) {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			colNames.add(format.format(listaterapia.get(i).getFechaRealizada())
					+ "");
		}

		for (int i = 0; i < rowNames.size(); i++) {
			data3D.add(new ArrayList<ArrayList<String>>());
			for (int j = 0; j < colNames.size(); j++) {
				data3D.get(i).add(new ArrayList<String>());
			}
		}
	        
	       // for(int i=0;i<listaterapiapar.size();i++)
		for(int i=0;i<listapares.size();i++)
	        {
	            for(int j=0;j<listaterapia.size();j++)
	            {
	            	for(int a=0;a<all.size();a++)
	                {
	            	if(Objects.equals(listaterapia.get(j).getIdTerapia(), all.get(a).getTxpTerapia().getIdTerapia()))
                    {
                       // if(Objects.equals(listaterapiapar.get(i).getTxpPar().getIdPar(), all.get(a).getTxpPar().getIdPar()))
	            		if(Objects.equals(listapares.get(i).getIdPar(), all.get(a).getTxpPar().getIdPar()))
	            		{
                            if(all.get(a).getTxpActivo())
                            {
                            	data3D.get(i).get(j).add("Si");
                            }else
                            {
                            	data3D.get(i).get(j).add("No");
                            }
                        	//data3D.get(i).get(j).add(all.get(a).getTxpActivo() + "");
                        }
                    }
	            	}
	            	 //data3D.get(i).get(j).add(terapiaService.getAllParbyAllTerapia(listaterapia.get(j), listaterapiapar.get(i).getTxpPar()));
	            }
	        }
	}
 
	
	public void clear()
	{
		terapia = new Terapia();
		enfermedad = new Enfermedad();
		sintoma = new Sintoma();
		//idTipoTerapia=0;
		//listarTerapiaPar.clear();
	}
	
	public void aceptar()
	{
		clear();idTipoTerapia=0;
		listarTerapiaPar.clear();
		FacesContext fc = FacesContext.getCurrentInstance();
 
		VisitaBean objetoTBean =(VisitaBean) fc.getExternalContext().getSessionMap().get("visitaBean");
		objetoTBean.probando();
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Terapia getTerapia() {
		return terapia;
	}

	public List<Par> getParesSugeridos() {
		return paresSugeridos;
	}

	public void setParesSugeridos(List<Par> paresSugeridos) {
		this.paresSugeridos = paresSugeridos;
	}

	public void setTerapia(Terapia terapia) {
		this.terapia = terapia;
	}

	public int getSliderDolor() {
		return sliderDolor;
	}

	public void setSliderDolor(int sliderDolor) {
		this.sliderDolor = sliderDolor;
	}

	public List<EnfermedadTerapia> getTerEnfermedades() {
		terEnfermedades = terapiaService.getEnfermedadesByTerapia(terapia);
		return terEnfermedades;
	}

	public void setTerEnfermedades(List<EnfermedadTerapia> terEnfermedades) {
		this.terEnfermedades = terEnfermedades;
	}

	public List<SintomaTerapia> getTerSintomas() {	
		terSintomas = terapiaService.getSintomasByTerapia(terapia);
		return terSintomas;
	}

	public void setTerSintomas(List<SintomaTerapia> terSintomas) {
		this.terSintomas = terSintomas;
	}

	public List<Par> getParesSeleccionados() {
		return paresSeleccionados;
	}

	public void setParesSeleccionados(List<Par> paresSeleccionados) {
		this.paresSeleccionados = paresSeleccionados;
	}

	public Enfermedad getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}

	public Sintoma getSintoma() {
		return sintoma;
	}

	public void setSintoma(Sintoma sintoma) {
		this.sintoma = sintoma;
	}

	public List<Par> getAllPares() {
		return allPares;
	}

	public void setAllPares(List<Par> allPares) {
		this.allPares = allPares;
	}
	public int getSize() {
		size = paresSeleccionados.size();
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<Par> getParesTerapia() {
		return paresTerapia;
	}

	public void setParesTerapia(List<Par> paresTerapia) {
		this.paresTerapia = paresTerapia;
	}
	
	//Método para cargar una terapia en específico segun el Id.
	public String cargarTerapiaEspecifica(int id) {
		//Cargar tab inicial: Enfermedad
		index = 0;		
		//Carga la terapia específica
		terapia = terapiaService.terapiaById(id);
		//Carga los pares realizados en la terapia
		paresTerapia = parService.paresByTerapia(terapia);
		//Carga las enfermedades de la terapia
		terEnfermedades = terapiaService.getEnfermedadesByTerapia(terapia);
		//Carga los sintomas de la terapia		
		terSintomas = terapiaService.getSintomasByTerapia(terapia);
		//Redirect de acuerdo a los pares en la terapia
		if (paresTerapia == null || paresTerapia.isEmpty()) {
			return "gestionTerapia?faces-redirect=true";
		} else {
			return "detalleTerapia?faces-redirect=true";
		}
	}

	//Método para agregar una nueva enfermedad a la terapia
	public String addEnfermedad() {
		//Crea una nueva EnfermedadTerapia
		EnfermedadTerapia enfermedadTerapia = new EnfermedadTerapia();
		enfermedadTerapia.setExtTerapia(terapia);
		enfermedadTerapia.setExtEnfermedad(enfermedad);
		//Verifica si existe
		if (enfermedadRepetida()) {
			StaticUtil.errorMessage("Error", "La enfermedad ya existe");
			return null;
		} else {
			if (enfermedadService.saveEnfermedadTerapia(enfermedadTerapia)) {
				StaticUtil.correctMesage("Éxito", "Se agregó correctamente la enfermedad");
				enfermedad = new Enfermedad();
				//Index de tab: enfermedad
				index = 0;
				return null;
			} else {
				StaticUtil.errorMessage("Error", "Hubo un error al guardar la enfermedad");
				return null;
			}
		}
	}

	//Método para agregar un nuevo síntoma a la terapia
	public String addSintoma() {
		//Crea un nuevo SintomaTerapia
		SintomaTerapia sintomaTerapia = new SintomaTerapia();
		sintomaTerapia.setSxtTerapia(terapia);
		sintomaTerapia.setSxtSintoma(sintoma);
		//Verifica si existe
		if (sintomaRepetido()) {
			StaticUtil.errorMessage("Error", "El sintoma ya existe");
			return null;
		} else {
			sintomaTerapia.setDolor(sliderDolor);
			if (sintomaService.saveSintomaTerapia(sintomaTerapia)) {
				StaticUtil.correctMesage("Éxito", "Se agregó correctamente el sintoma");
				sintoma = new Sintoma();
				sliderDolor = 0;
				//Index de tab: Sintoma				
				index = 1;								
				return null;
			} else {
				StaticUtil.errorMessage("Error", "Hubo un error al guardar el sintoma");
				return null;
			}
		}
	}

	//Método para autocompletar la búsqueda de Enfermedad
	public List<Enfermedad> autoCompletarEnfermedad(String query) {
		List<Enfermedad> allEnfermedades = enfermedadService.getAll();
		List<Enfermedad> enfFiltrados = new ArrayList<Enfermedad>();

		for (int i = 0; i < allEnfermedades.size(); i++) {
			Enfermedad enfermedad = allEnfermedades.get(i);
			if (enfermedad.getNombre().toLowerCase().startsWith(query)) {
				enfFiltrados.add(enfermedad);
			}
		}
		if(enfFiltrados.size()==0)
		{
			queryE = query;
			System.out.println("Enfermedad : "+queryE); 
		}
		return enfFiltrados;
	}
	
	//Método para autocompletar la búsqueda de Sintoma
	public List<Sintoma> autoCompletarSintoma(String query) {
		List<Sintoma> allSintomas = sintomaService.getAll();
		List<Sintoma> sinFiltrados = new ArrayList<Sintoma>();

		System.out.println("Lista : "+allSintomas.size());
		System.out.println("QUERY : "+query);
		
		for (int i = 0; i < allSintomas.size(); i++) {
			Sintoma sintoma = allSintomas.get(i);
			if (sintoma.getDescripcion().toLowerCase().startsWith(query)) {
				sinFiltrados.add(sintoma);//queryS="";
			}
		}
		
		if(sinFiltrados.size()==0)
		{
			queryS = query;
			System.out.println("Sintoma : "+queryS); 
		} /*else
		{
			queryS="";
		}*/
		
		return sinFiltrados;
	}

	//Método para verificar si la enfermedad ya existe 
	public boolean enfermedadRepetida() {
		List<EnfermedadTerapia> terEnf = terapiaService.getEnfermedadesByTerapia(terapia);
		for (EnfermedadTerapia e : terEnf) {
			if (e.getExtEnfermedad().getNombre().equalsIgnoreCase(enfermedad.getNombre())) {
				return true;
			}
		}
		return false;
	}

	//Método para verificar si el síntoma ya existe	
	public boolean sintomaRepetido() {
		List<SintomaTerapia> terSin = terapiaService.getSintomasByTerapia(terapia);
		for (SintomaTerapia e : terSin) {
			if (e.getSxtSintoma().getDescripcion().equalsIgnoreCase(sintoma.getDescripcion())) {
				return true;
			}
		}
		return false;
	}

	//Método para finalizar la edición de síntomas y enfermedades
	public String finalizarEdicion() {
		//Usa el método getSugeridos para cargar los pares según enfermedades y síntomas
		paresSugeridos = getSugeridos();
		//Obtiene pares de una terapia específica
		List<Par> paresDb = terapiaService.getTerapiaParesFromTerapia(terapia);
		//Verifica si existen pares en la terapia.
		if (paresDb != null) {
			paresSeleccionados = paresDb;
		} else {
			paresSeleccionados = new ArrayList<>();
		}
		allPares = parService.getAllParesOrderGoiz();
		return "gestionTerapiaDetalle?faces-redirect=true";
	}

	public void ListarPares(int idTerapia)
	{
		Terapia t = new Terapia();
		t.setIdTerapia(idTerapia);
		paresSeleccionados=terapiaService.getTerapiaParesFromTerapia(t);
		System.out.println("Cantidad : "+paresSeleccionados.size());
	}
	
	//Método para obtener la lista de pares sugeridos según las enfermedades y sintomas de la terapia.
	public List<Par> getSugeridos() {
		//Crea una nueva lista para guardar los pares sugeridos.
		List<Par> sugeridos = new ArrayList<>();
		//Carga las enfermedades y síntomas asociados a la terapia.
		terSintomas = terapiaService.getSintomasByTerapia(terapia);
		terEnfermedades = terapiaService.getEnfermedadesByTerapia(terapia);
		//Verifica si existen síntomas
		if (terSintomas != null) {
			for (SintomaTerapia sxt : terSintomas) {
				//Se obtiene los pares relacionados al síntoma específico según opciones de Biomagnetismo.
				List<Par> paresSintoma = parService.getParesBySintoma(sxt.getSxtSintoma());
				//Verifica si hay pares asociados al síntoma.
				if (paresSintoma != null) {
					for (Par par : paresSintoma) {
						boolean esExistente = false;
						//Verifica si la lista de sugeridos esta vacía.
						if (sugeridos != null) {
							if (sugeridos.isEmpty()) {
								//Si está vacía guarda el par sin verificar si ya está repetido.
								sugeridos.add(par);
							} else {
								//En caso no esté vacía verifica si el par ya ha sido agregado.
								for (Par sugerido : sugeridos) {
									if (sugerido.getIdPar() == par.getIdPar()) {
										//Si el par ya existe, se pasa al siguiente posible par sugerido.
										esExistente = true;
										break;
									}
								}
								if (esExistente == false) {
									//Si el par no existe, se agrega el par a los pares sugeridos.
									sugeridos.add(par);
								}
							}
						}
					}
				}
			}
		}

		//Verifica si existen síntomas		
		if (terEnfermedades != null) {
			for (EnfermedadTerapia ext : terEnfermedades) {
				//Se obtiene los pares relacionados a la enfermedad según opciones de Biomagnetismo.
				List<Par> paresEnfermedad = parService.getParesByEnfermedad(ext.getExtEnfermedad());
				//Verifica si hay pares asociados a la enfermedad.				
				if (paresEnfermedad != null) {
					for (Par par : paresEnfermedad) {
						boolean esExistente = false;
						//Verifica si la lista de sugeridos esta vacía.
						if (sugeridos != null) {
							if (sugeridos.isEmpty()) {
								//Si está vacía guarda el par sin verificar si ya está repetido.								
								sugeridos.add(par);
							} else {
								//En caso no esté vacía verifica si el par ya ha sido agregado.
								for (Par sugerido : sugeridos) {
									if (sugerido.getIdPar() == par.getIdPar()) {
										//Si el par ya existe, se pasa al siguiente posible par sugerido.
										esExistente = true;
										break;
									}
								}
								if (esExistente == false) {
									//Si el par no existe, se agrega el par a los pares sugeridos.
									sugeridos.add(par);
								}
							}
						}
					}
				}
			}
		}
		return sugeridos;
	}
	
	//Método para verificar si el botón de agregar a pares elegidos debe ser renderizado.
	public boolean shouldBeRendered(int idPar) {
		for (Par par : paresSeleccionados) {
			if (par.getIdPar() == idPar) {
				//Si el id del Par ya esta entre los pares seleccionados el botón no es renderizado.
				return false;
			}
		}
		return true;
	}

	//Método para pintar el row de la tabla de azul en caso sea un par sugerido.
	public String shouldBeColored(int idPar) {
		for (Par par : paresSugeridos) {
			if (par.getIdPar() == idPar) {
				//Si el id del Par está dentro de los pares sugeridos entonces se pinta la línea.
				return "colored";
			}
		}
		return null;
	}

	//Método para agregar el par de la lista del rastreo a la lista de elegidos.
	public String addParRastreo(int idPar) {
		//Carga el par según espcífico el id obtenido.
		Par toAdd = parService.parById(idPar);
		boolean existe = false;
		for (Par par : paresSeleccionados) {
			if (par.getIdPar() == idPar) {
				//En caso el par ya haya sido agregado se ignora.
				existe = true;
				break;
			}
		}
		if (!(existe)) {
			//Si el par de rastreo no ha sido asignado se agrega a la lista de seleccionados.
			paresSeleccionados.add(toAdd);
		}
		return null;
	}

	//Método para finalizar la edición de los pares en la terapia.
	public String finalizarTerapia() {
		//Crea un TerapiaPar por cada uno de los pares seleccionados.
		for (Par p : paresSeleccionados) {
			TerapiaPar toPersist = new TerapiaPar();
			toPersist.setTxpPar(p);
			toPersist.setTxpTerapia(terapia);
			//Se verifica si el TerapiaPar ya ha sido agregado antes
			if(terapiaService.TerapiaParByParAndTerapia(terapia, p)==null){
				//Si no ha sido agregado se agrega a la terapia.
				terapiaService.saveTerapiaPar(toPersist);
			}			
		}
		//Se recarga los pares asignados a la terapia.
		paresTerapia = parService.paresByTerapia(terapia);
		return "detalleTerapia?faces-redirect=true";
	}

	/*public List<Sintoma> getListasintoma() {
		return listasintoma;
	}

	public void setListasintoma(List<Sintoma> listasintoma) {
		this.listasintoma = listasintoma;
	}

	public void addSintoma2()
	{
		for(Sintoma s : listasintoma){
			if(s.getIdSintoma() == sintoma.getIdSintoma()){
				StaticUtil.errorMessage("Error", "El sintoma ya ha sido agregado");
				return;
			}
		}
		listasintoma.add(sintoma);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.getExternalContext().getSessionMap().put("listaSintoma", listasintoma);
		sintoma = new Sintoma();

	}*/
	
	public void addSintomaNuevo()
	{
		if(sintoma.getDescripcion().isEmpty())
		{
			sintoma.setDescripcion(queryS);
			sintomaService.saveSintoma(sintoma);
		}
		
	}
	
	public void addSintoma3()
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		//if(sintoma.getDescripcion().isEmpty())
		//{
		/*    sintoma = new Sintoma();
			sintoma.setDescripcion(queryS);
			sintomaService.saveSintoma(sintoma);*/
		//}
  
		
		/*	SintomaBean sin1 = new SintomaBean();
			sin1.add2("Hola :3");
	 */
		/*if(queryS!="")
		{
			    sintoma = new Sintoma();
				sintoma.setDescripcion(queryS);
				sintomaService.saveSintoma(sintoma);
				//queryS="";
		}*/
		
		/*if(queryS.equals(""))
		{
			StaticUtil.errorMessage("Error", "El campo Sintoma esta vacio");
			return ;
		}*/
		
		String cadena="";
		String c;
		System.out.println("QueryS : "+queryS);
	for(int i=0;i<queryS.length();i++){
	 
		c=queryS.charAt(i)+"";
		if(c.equals(" "))
		{
			cadena="";
		}
	 
	}
	/*System.out.println("Tamaño : "+cadena.length() +" Tam : "+queryS.length());
	if(cadena.equals(""))
	{
		StaticUtil.errorMessage("Error", "El campo sintoma esta vacio");
		return;
	}*/
	/*if(cadena.equals(""))
	{
		StaticUtil.errorMessage("Error", "Ingrese un Sintoma");
		return ;
	}*/
		
		if(sintoma==null)
		{
			if(queryS!="")
			{
			 	sintoma = new Sintoma();
				sintoma.setDescripcion(queryS);
				sintomaService.saveSintoma(sintoma);
			
				//sintoma = new Sintoma();
				//sintoma = sintomaService.lastSintoma();
				//System.out.println("Sintoma Desc : "+sintoma.getDescripcion() +" ID : "+sintoma.getIdSintoma());
			}else
			{
				StaticUtil.errorMessage("Error", "El campo sintoma esta vacio");
				return;
			}
			
		}
		for(SintomaVisita s : listasintomaxvisita)
		{
			if(s.getSxvSintoma().getIdSintoma() ==sintoma.getIdSintoma())
			{
				StaticUtil.errorMessage("Error", "El sintoma ya ha sido agregado");
				sintoma = new Sintoma();
				return;
			}
		}
		
	 
		Visita vis =(Visita) fc.getExternalContext().getSessionMap().get("ultimavisita");
		System.out.println("Visita : "+vis.getIdVisita() +" Sintoma : "+sintoma.getIdSintoma());
		SintomaVisita sinvis = new SintomaVisita();
		sinvis.setSxvSintoma(sintoma);
		sinvis.setSxvVisita(vis);
		terapiaService.saveSintomaVisita(sinvis);
		queryS="";
		sintoma = new Sintoma();
		listasintomaxvisita= terapiaService.getAllSintomaxVisita(vis);
	}
	public void listarsintomas(){
		FacesContext fc = FacesContext.getCurrentInstance();
		Visita vis =(Visita) fc.getExternalContext().getSessionMap().get("ultimavisita");
		/*if(listasintomaxvisita!=null)
		{
			listasintomaxvisita.clear();
		}*/
		listasintomaxvisita= terapiaService.getAllSintomaxVisita(vis);
	}
	
	public void listarenfermedades()
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		Visita vis =(Visita) fc.getExternalContext().getSessionMap().get("ultimavisita");
		listaenfermedadvisita= terapiaService.getAllEnfermedadxVisita(vis);
	}
	
	
	/*public void addEnfermedad2(){
		for(Enfermedad e : listaenfermedad){
			if(e.getIdEnfermedad() == enfermedad.getIdEnfermedad()){
				StaticUtil.errorMessage("Error", "La enfermedad ya ha sido agregada");
				return;
			}
		}
		listaenfermedad.add(enfermedad);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.getExternalContext().getSessionMap().put("listaEnfermedad", listaenfermedad);
		enfermedad = new Enfermedad();		
	}
	*/
	
	public void Update(TerapiaPar tp)
	{
		terapiaparService.updateTerapia(tp);
		/*if(terapiaService.updateTerapiaPar(tp))
			{
				System.out.println("Actualizo");
			}else
			{
				System.out.println("No actualizo");
			}*/
	}
	
	public void addPar2(Integer idpar)
	{
		if(ParExistente(idpar)==false)
		{
			FacesContext fc = FacesContext.getCurrentInstance();
			Terapia terapia =(Terapia) fc.getExternalContext().getSessionMap().get("terapia");
			if(terapia==null)
			{
				StaticUtil.errorMessage("Error", "Seleccione un tipo de Terapia");
				StaticUtil.keepMessages();
				 return;
			}
			Par par = new Par();
			par.setIdPar(idpar);
			TerapiaPar tp = new TerapiaPar();
			tp.setTxpPar(par);
			tp.setTxpTerapia(terapia);
			tp.setTxpActivo(true);
			terapiaService.saveTerapiaPar(tp);
			StaticUtil.correctMesage("Exito",
					"Se agregó el par");
			StaticUtil.keepMessages();
			listarTerapiaPar=  terapiaService.getAllTerapiaParbyTerapia(terapia);
			
			
			//terapiaService.getAllTerapiaParbyTerapia(terapia);
		}else
		{
			
		}
	}
	
	public void listado()
	{
		//if(terapia.getTerTipoTerapia()!=null)
		//{
	
		//}
		
		clear();
		FacesContext fc = FacesContext.getCurrentInstance();
		Visita visita = (Visita) fc.getExternalContext().getSessionMap().get("ultimavisita");
		List<Terapia> e=terapiaService.terapiasPorVisita(visita);//ACCESO A DATA
		if(e.size()>0)
		{
			terapia = terapiaService.terapiasPorVisita(visita).get(0);
			listarTerapiaPar=terapiaService.getAllTerapiaParbyTerapia(terapia);
		}
		//listarTerapiaPar=  terapiaService.getAllTerapiaParbyTerapia(terapia);
	}
	
	public void addPar3(TerapiaPar terp)
	{
		Par par = new Par();
		par.setIdPar(terp.getTxpPar().getIdPar());//--------Acceso a bd
		TerapiaPar tp = new TerapiaPar();
		tp.setTxpPar(par);
		tp.setTxpTerapia(terapia);
		tp.setTxpActivo(terp.getTxpActivo());
		terapiaService.saveTerapiaPar(tp);
	}
	
	public void addPar(Integer idpar,boolean estado)
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		Terapia terapi =(Terapia) fc.getExternalContext().getSessionMap().get("terapia");
		Par par = new Par();
		par.setIdPar(idpar);//--------Acceso a bd
		TerapiaPar tp = new TerapiaPar();
		tp.setTxpPar(par);
		tp.setTxpTerapia(terapi);
		tp.setTxpActivo(estado);
		terapiaService.saveTerapiaPar(tp); //-----------Acceso a bd     
		StaticUtil.correctMesage("Exito",
				"Se agregó el par");
		StaticUtil.keepMessages();
		//listarTerapiaPar=  terapiaService.getAllTerapiaParbyTerapia(terapia);
		//terapiaService.getAllTerapiaParbyTerapia(terapia);
		/*if(ParExistente(idpar)==false)
		{
			FacesContext fc = FacesContext.getCurrentInstance();
			Terapia terapia =(Terapia) fc.getExternalContext().getSessionMap().get("terapia");
			Par par = new Par();
			par=parService.parById(idpar);
			TerapiaPar tp = new TerapiaPar();
			tp.setTxpPar(par);
			tp.setTxpTerapia(terapia);
			terapiaService.saveTerapiaPar(tp);
			listarTerapiaPar=  terapiaService.getAllTerapiaParbyTerapia(terapia);
			//terapiaService.getAllTerapiaParbyTerapia(terapia);
		}else
		{
			
		}*/
	}
	
	public boolean ParExistente(int idpar)
	{
	 
		boolean valor=false;
		for(TerapiaPar s : listarTerapiaPar)
		{
			System.out.println(s.getTxpPar().getIdPar() +"-"+ idpar);
			if(s.getTxpPar().getIdPar() == idpar)
			{
				StaticUtil.errorMessage("Error", "El par ya ha sido agregado");
				return true;
			}
		}
		return valor;
	}
	
	public void addEnfermedad3()
	{
		
		/*if(queryE.equals(""))
		{
			StaticUtil.errorMessage("Error", "El campo Enfermedad esta vacio");
			return ;
		}
		*/
		String cadena="";
		String c;
	for(int i=0;i<queryE.length();i++){
	 
		c=queryE.charAt(i)+"";
		if(c.equals(" "))
		{
			cadena+="";
		}
	 
	}
/*	if(cadena.equals(""))
	{
		StaticUtil.errorMessage("Error", "Ingrese una Enfermedad");
		return ;
	}*/
		
		if(enfermedad==null)
		{
			if(queryE!="")
			{
			 	enfermedad= new Enfermedad(); 
			 	enfermedad.setNombre(queryE);
				enfermedadService.saveEnfermedad(enfermedad);
			
			
				//sintoma = new Sintoma();
				//sintoma = sintomaService.lastSintoma();
				//System.out.println("Sintoma Desc : "+sintoma.getDescripcion() +" ID : "+sintoma.getIdSintoma());
			}else
			{
				StaticUtil.errorMessage("Error", "El campo enfermedad esta vacio");
				return;
			}
			
		}
		for(EnfermedadVisita s : listaenfermedadvisita)
		{
		/*	System.out.println("Enfermedad de la lista : "+s.getExvEnfermedad().getIdEnfermedad()+
							   "Mi enfermedad : "+enfermedad.getIdEnfermedad());
			if(s.getExvEnfermedad().getIdEnfermedad() ==enfermedad.getIdEnfermedad())
			{
				System.out.println("Entro aqui :) ");
				StaticUtil.errorMessage("Error", "La Enfermedad ya ha sido agregada");
				enfermedad = new Enfermedad();
				return;
			}*/
			if(s.getExvEnfermedad().getNombre().equals(enfermedad.getNombre()))
			{
				//System.out.println("Entro aqui :3");
				StaticUtil.errorMessage("Error", "La Enfermedad ya ha sido agregada");
				enfermedad = new Enfermedad();
				return;
			}
		}
		
		for(SintomaVisita s : listasintomaxvisita)
		{
			if(s.getSxvSintoma().getIdSintoma() ==sintoma.getIdSintoma())
			{
				StaticUtil.errorMessage("Error", "El sintoma ya ha sido agregado");
				sintoma = new Sintoma();
				return;
			}
		}
		
		
		FacesContext fc = FacesContext.getCurrentInstance();
		Visita vis =(Visita) fc.getExternalContext().getSessionMap().get("ultimavisita");
		EnfermedadVisita enfvis= new EnfermedadVisita();
		enfvis = new EnfermedadVisita();
		enfvis.setExvVisita(vis);
		enfvis.setExvEnfermedad(enfermedad);
		terapiaService.saveEnfermedadVisita(enfvis);
		enfermedad = new Enfermedad();
		listaenfermedadvisita= terapiaService.getAllEnfermedadxVisita(vis);
		
	}
	
	
	/*public List<Enfermedad> getListaenfermedad() {
		return listaenfermedad;
	}

	public void setListaenfermedad(List<Enfermedad> listaenfermedad) {
		this.listaenfermedad = listaenfermedad;
	}*/

	
	
	public void confirmaTerapia()
	{
		clear();
		FacesContext fc = FacesContext.getCurrentInstance();
		Visita visita = (Visita) fc.getExternalContext().getSessionMap().get("ultimavisita");
		List<Terapia> e=terapiaService.terapiasPorVisita(visita);//ACCESO A DATA
		if(e.size()>0)
		{
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('confirm').show();");
		}else
		{
			//System.out.println("Registro de Terapia");
			addTerapia();
		}
	}
	
	public void usarTerapia()
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		Visita visita = (Visita) fc.getExternalContext().getSessionMap().get("ultimavisita");
		terapia = terapiaService.terapiasPorVisita(visita).get(0);
		listarTerapiaPar=terapiaService.getAllTerapiaParbyTerapia(terapia);
		fc.getExternalContext().getSessionMap().put("terapia", terapia);
		System.out.println(terapia.getIdTerapia());
	}
	
	public void addTerapia() {
		//RequestContext context = RequestContext.getCurrentInstance();
		FacesContext fc = FacesContext.getCurrentInstance();
		Visita visita = (Visita) fc.getExternalContext().getSessionMap().get("ultimavisita");
		VisitaBean objetoTBean =(VisitaBean) fc.getExternalContext().getSessionMap().get("visitaBean");
		// Se carga el tipoterapia segun la seleccion del combobox
		TipoTerapia tipoTerapia = new TipoTerapia();
		tipoTerapia=terapiaService.tteById(idTipoTerapia);
		// Se le añade el TipoTerapia a la Terapia y la fecha actal
		String usuarioCreacion = StaticUtil.userLogged();
		terapia.setUsuarioCreacion(usuarioCreacion);
		terapia.setTerTipoTerapia(tipoTerapia);
		terapia.setFechaRealizada(StaticUtil.getFechaActual());
		terapia.setTerVisita(visita);
		
		List<TerapiaPar> lista = null;
		// Se guarda la terapia en la base de datos
		if (terapiaService.saveTerapia(terapia)) {
			StaticUtil.correctMesage("Exito",
					"Se agregó correctamente la terapia");
			StaticUtil.keepMessages();
			// Se añade el costo de la terapia a la visita
			visita.setCostoTotal(visita.getCostoTotal()
					+ terapia.getTerTipoTerapia().getCosto());
			objetoTBean.updateVisita(visita);
			
			terapia=terapiaService.terapiasPorVisita(visita).get(0);
			fc.getExternalContext().getSessionMap().put("terapia", terapia);
			Visita v= (Visita) fc.getExternalContext().getSessionMap().get("penultimavisita");
			
			lista=terapiaService.getAllTerapiaParbyVisita(v);
			System.out.println("Lista de Pares: "+lista.size());
			for(int j=0;j<lista.size();j++)
			{
				addPar3(lista.get(j));
			}
			//listarTerapiaPar=terapiaService.getAllTerapiaParbyTerapia(terapia);		
			
			
			// Se limpian los datos guardados
			terapia  = new Terapia();
			idTipoTerapia=0;
			// Se añade los pares
			
			//context.execute("PF('dlgT').hide();");
			// Se cargan las terapias de la visita (añadiendo la actual)
			// terapiasDeVisita = terapiaService.terapiasPorVisita(visita);
			// Redireccion
			
		} else {
			StaticUtil.errorMessage("Error", "Hubo un error al agregar");

		}
	}
	
	
	public List<SintomaVisita> getListasintomaxvisita() {
		return listasintomaxvisita;
	}

	public void setListasintomaxvisita(List<SintomaVisita> listasintomaxvisita) {
		this.listasintomaxvisita = listasintomaxvisita;
	}

	public List<EnfermedadVisita> getListaenfermedadvisita() {
		return listaenfermedadvisita;
	}

	public void setListaenfermedadvisita(List<EnfermedadVisita> listaenfermedadvisita) {
		this.listaenfermedadvisita = listaenfermedadvisita;
	}
	
	public Integer getIdTipoTerapia() {
		return idTipoTerapia;
	}

	public void setIdTipoTerapia(Integer idTipoTerapia) {
		this.idTipoTerapia = idTipoTerapia;
	}

	public List<TerapiaPar> getListarTerapiaPar() {
		return listarTerapiaPar;
	}

	public void setListarTerapiaPar(List<TerapiaPar> listarTerapiaPar) {
		this.listarTerapiaPar = listarTerapiaPar;
	}
	
	
	/** **/
	 public List<TerapiaPar> getListaterapiapar() {
	        return listaterapiapar;
	    }

	    public List<Terapia> getListaterapia() {
	        return listaterapia;
	    }
	    


	    public List<String> getRowNames() {
	        return rowNames;
	    }

	    public void setRowNames(List<String> rowNames) {
	        this.rowNames = rowNames;
	    }

	    public List<String> getColNames() {
	        return colNames;
	    }

	    public void setColNames(List<String> colNames) {
	        this.colNames = colNames;
	    }

	    public ArrayList<ArrayList<ArrayList<String>>> getData3D() {
	        return data3D;
	    }

	    public void setData3D(ArrayList<ArrayList<ArrayList<String>>> data3D) {
	        this.data3D = data3D;
	    }


		public List<Par> getListapares() {
			return listapares;
		}


		public void setListapares(List<Par> listapares) {
			this.listapares = listapares;
		}


		public String getQueryS() {
			return queryS;
		}


		public void setQueryS(String queryS) {
			this.queryS = queryS;
		}


		public String getQueryE() {
			return queryE;
		}


		public void setQueryE(String queryE) {
			this.queryE = queryE;
		}

	
}

