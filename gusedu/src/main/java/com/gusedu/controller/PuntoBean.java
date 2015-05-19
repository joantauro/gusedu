package com.gusedu.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gusedu.model.Par;
import com.gusedu.model.Punto;
import com.gusedu.service.ParService;
import com.gusedu.service.PuntoService;
import com.gusedu.util.StaticUtil;

@Component
@Scope(value="session")
public class PuntoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	PuntoService puntoService;
	
	@Autowired
	ParService parService;

	private Punto punto;
	//private Par pars;
	private List<Punto> puntos;
	private List<Par> parcito;
	private List<Par> par;
	
	private String query;

	int asc;
	int desc;
	int goiz;

	private List<classPunto> lista;
	private List<classPar> listapar;
	
	public PuntoBean() {
		punto = new Punto();
		puntos = new ArrayList<>();
	}

	public Punto getPunto() {
		return punto;
	}

	public void setPunto(Punto punto) {
		this.punto = punto;
	}

	public List<Punto> getPuntos() {
		if (query != null) {
			if (!query.isEmpty()) {
				return puntos;
			}
		}
		if (asc != 0 || desc != 0 || goiz != 0) {
			return puntos;
		}
		return puntoService.getAllPuntosRastreables();
	}

	public void setPuntos(List<Punto> puntos) {
		this.puntos = puntos;
	}

	public String detallePunto(Integer id) {
		punto = puntoService.puntoById(id);
		return "pm:detallePunto?transition=flip";
	}

	public void cargarPunto(Integer id) {
		punto = puntoService.puntoById(id);
	}

	public void eliminarPunto() {
		puntoService.deletePunto(punto);
		punto = new Punto();
	}

	public String detallePuntoUpdate(Integer id) {
		punto = puntoService.puntoById(id);
		return "pm:gestionPunto?transition=flip";
	}

	public String backToIndex() {
		punto = new Punto();
		return "index?faces-redirect=true";
	}

	public String backToConsultar() {
		punto = new Punto();
		return "gestionPunto?faces-redirect=true";
	}

	public void cancelar() {
		punto = new Punto();
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String actualizarPunto() {
		if (puntoService.updatePunto(punto)) {
			punto = new Punto();
			StaticUtil.correctMesage("Éxito", "Se ha actualizado correctamente el punto");
			StaticUtil.keepMessages();
			return "pm:consultarPuntos?transition=flip";
		} else {
			StaticUtil.errorMessage("Error", "Hubo un error al actualizar los datos del punto");
			return null;
		}
	}

	public String añadirPunto() {
		if (esRepetido()) {
			StaticUtil.errorMessage("Error", "El nombre del punto ya existe");
			return null;
		}
		if (puntoService.savePunto(punto)) {
			punto = new Punto();
			StaticUtil.correctMesage("Éxito", "Se ha añadido correctamente el punto");
			StaticUtil.keepMessages();			
			return "pm:consultarPuntos?transition=flip";
		} else {
			StaticUtil.errorMessage("Error", "Hubo un error al añadir el punto");
			return null;
		}
	}

	public void añadirPunto2()
	{
		if (esRepetido()) {
			StaticUtil.errorMessage("Error", "El nombre del punto ya existe");
		}
		if (puntoService.savePunto(punto)) {
			punto = new Punto();
			StaticUtil.correctMesage("Éxito", "Se ha añadido correctamente el punto");
			StaticUtil.keepMessages();		
			//RequestContext context = RequestContext.getCurrentInstance();
			//context.execute("PF('AddP').toggle();");
		} else {
			StaticUtil.errorMessage("Error", "Hubo un error al añadir el punto");
		}
	}
	
	public String nuevoPunto() {
		punto = new Punto();
		return "pm:nuevoPunto?transition=flip";
	}

	public boolean esRepetido() {
		List<Punto> allPuntos = puntoService.getAllPuntos();
		for (Punto p : allPuntos) {
			if (p.getNombre().toLowerCase().equals(punto.getNombre())) {
				return true;
			}
		}
		return false;
	}	

	public void filtrarBusqueda() {
		puntos = puntoService.getAllPuntos();
		List<Punto> filtrados = new ArrayList<>();
		for (Punto p : puntos) {
			if (p.getNombre().toLowerCase().contains(query.toLowerCase())) {
				filtrados.add(p);
			}
		}
		puntos = filtrados;
	}

	public void orderAsc() {
		asc = 1;
		desc = 0;
		goiz = 0;
		puntos = puntoService.getAllOrdenAlfabeticoAsc();
	}

	public void orderDesc() {
		desc = 1;
		asc = 0;
		goiz = 0;
		puntos = puntoService.getAllOrdenAlfabeticoDesc();
	}

	public void orderGoiz() {
		desc = 0;
		asc = 0;
		goiz = 1;
		puntos = puntoService.getAllOrdenGoiz();
	}
	
	
	public List<Par> getParcito() {
		return parcito;
	}

	public void setParcito(List<Par> parcito) {
		this.parcito = parcito;
	}
	
	public void buscar(int p1)
	{
		punto.setIdPunto(p1);
		parcito=parService.paresByPunto(punto);
		System.out.println("Lista : "+parcito.size());
	}
	
	public void hola(){
		System.out.println(parcito.size());	
	}


	
	public List<Par> getPar() {
		return par;
	}

	public void setPar(List<Par> par) {
		this.par = par;
	}


	
	public List<classPunto> getLista() {
		lista = new ArrayList<>();
		List<Punto> ptos = new ArrayList<>();
		//Punto p= new Punto();
		ptos=puntoService.getAllPuntos();
		
		for(int i=0;i<50;i++)
		{
			if(i>=35)
			{
				ptos.add(new Punto());
			}
			lista.add(new classPunto(ptos.get(i),ptos.get(i+50),ptos.get(i+100),ptos.get(i+150)));
		}
		
		
		return lista;
	}

	public List<classPar> getListapar() {
		listapar = new ArrayList<>();
		List<Par> pares = new ArrayList<>();
		pares=parService.getAllPares();
		System.out.println(pares.size());
		for(int j=0;j<68;j++)
		{
			if(j>=65)
			{
				pares.add(new Par());
				//System.out.print( "SOS");
			}
			//System.out.println(j+"-"+(j+68)+"-"+(j+136)+"-"+(j+204));
			listapar.add(new classPar(pares.get(j), pares.get(j+68), pares.get(136+j), pares.get(j+204)));
		}
		return listapar;
	}

	public void setListapar(List<classPar> listapar) {
		this.listapar = listapar;
	}
	
}
