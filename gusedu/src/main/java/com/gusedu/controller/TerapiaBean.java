package com.gusedu.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gusedu.model.Enfermedad;
import com.gusedu.model.EnfermedadTerapia;
import com.gusedu.model.Par;
import com.gusedu.model.Sintoma;
import com.gusedu.model.SintomaTerapia;
import com.gusedu.model.Terapia;
import com.gusedu.model.TerapiaPar;
import com.gusedu.service.EnfermedadService;
import com.gusedu.service.ParService;
import com.gusedu.service.SintomaService;
import com.gusedu.service.TerapiaService;
import com.gusedu.util.StaticUtil;

@Component
@Scope(value="session")
public class TerapiaBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	TerapiaService terapiaService;

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

	private List<Sintoma> listasintoma;
	private List<Enfermedad> listaenfermedad;
	
	
	public TerapiaBean() {
		sliderDolor = 0;
		terapia = new Terapia();
		enfermedad = new Enfermedad();
		sintoma = new Sintoma();
		paresSeleccionados = new ArrayList<Par>();
		
		listasintoma = new ArrayList<>();
		listaenfermedad = new ArrayList<>();
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
		return enfFiltrados;
	}
	
	//Método para autocompletar la búsqueda de Sintoma
	public List<Sintoma> autoCompletarSintoma(String query) {
		List<Sintoma> allSintomas = sintomaService.getAll();
		List<Sintoma> sinFiltrados = new ArrayList<Sintoma>();

		for (int i = 0; i < allSintomas.size(); i++) {
			Sintoma sintoma = allSintomas.get(i);
			if (sintoma.getDescripcion().toLowerCase().startsWith(query)) {
				sinFiltrados.add(sintoma);
			}
		}
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

	public List<Sintoma> getListasintoma() {
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

	}

	public void addEnfermedad2(){
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
	public List<Enfermedad> getListaenfermedad() {
		return listaenfermedad;
	}

	public void setListaenfermedad(List<Enfermedad> listaenfermedad) {
		this.listaenfermedad = listaenfermedad;
	}
	
}
