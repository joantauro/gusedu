package com.gusedu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gusedu.model.Enfermedad;
import com.gusedu.model.EnfermedadPar;
import com.gusedu.model.Grupo;
import com.gusedu.model.Par;
import com.gusedu.model.Punto;
import com.gusedu.model.Sintoma;
import com.gusedu.service.EnfermedadService;
import com.gusedu.service.GrupoService;
import com.gusedu.service.ParService;
import com.gusedu.service.PuntoService;
import com.gusedu.service.SintomaService;
import com.gusedu.util.StaticUtil;

@Controller
public class ParBean {

	@Autowired
	ParService parService;

	@Autowired
	PuntoService puntoService;
	
	@Autowired
	GrupoService grupoService;
	
	@Autowired
	EnfermedadService enfermedadService;
	
	@Autowired
	SintomaService sintomaService;

	private Par par;
	private List<Par> pares;
	private List<Grupo> grupos;

	private Punto punto1;
	private Punto punto2;
	
	private Grupo grupoSeleccionado;
	
	private Par parSeleccionado;
	private List<Enfermedad> enfermedadesPar;
	private List<Sintoma> sintomasPar;
	private Enfermedad enfermedadAdd;
	private Sintoma sintomaAdd;
	private List<Enfermedad> enfermedadesAll;
	private List<Sintoma> sintomaAll;	

	private EnfermedadPar expToDelete;
	
	public ParBean() {
		par = new Par();
		punto1 = new Punto();
		punto2 = new Punto();
		parSeleccionado = new Par();
		enfermedadAdd = new Enfermedad();
		sintomaAdd = new Sintoma();
		grupoSeleccionado = new Grupo();		
	}

	public Par getPar() {
		return par;
	}

	public void setPar(Par par) {
		this.par = par;
	}

	public Punto getPunto1() {
		return punto1;
	}

	public void setPunto1(Punto punto1) {
		this.punto1 = punto1;
	}

	public Punto getPunto2() {
		return punto2;
	}

	public void setPunto2(Punto punto2) {
		this.punto2 = punto2;
	}

	public List<Par> getPares() {
		pares = parService.getAllPares();
		return pares;
	}

	public void setPares(List<Par> pares) {
		this.pares = pares;
	}

	public ParService getParService() {
		return parService;
	}

	public void setParService(ParService parService) {
		this.parService = parService;
	}

	public PuntoService getPuntoService() {
		return puntoService;
	}

	public void setPuntoService(PuntoService puntoService) {
		this.puntoService = puntoService;
	}

	public Grupo getGrupoSeleccionado() {
		return grupoSeleccionado;
	}

	public void setGrupoSeleccionado(Grupo grupoSeleccionado) {
		this.grupoSeleccionado = grupoSeleccionado;
	}

	public List<Grupo> getGrupos() {
		grupos = grupoService.getAllGrupos();
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public Par getParSeleccionado() {
		return parSeleccionado;
	}

	public void setParSeleccionado(Par parSeleccionado) {
		this.parSeleccionado = parSeleccionado;
	}

	public List<Enfermedad> getEnfermedadesPar() {
		return enfermedadesPar;
	}

	public void setEnfermedadesPar(List<Enfermedad> enfermedadesPar) {
		this.enfermedadesPar = enfermedadesPar;
	}

	public List<Sintoma> getSintomasPar() {
		return sintomasPar;
	}

	public void setSintomasPar(List<Sintoma> sintomasPar) {
		this.sintomasPar = sintomasPar;
	}

	public Enfermedad getEnfermedadAdd() {
		return enfermedadAdd;
	}

	public void setEnfermedadAdd(Enfermedad enfermedadAdd) {
		this.enfermedadAdd = enfermedadAdd;
	}

	public Sintoma getSintomaAdd() {
		return sintomaAdd;
	}

	public void setSintomaAdd(Sintoma sintomaAdd) {
		this.sintomaAdd = sintomaAdd;
	}

	public List<Enfermedad> getEnfermedadesAll() {
		enfermedadesAll = enfermedadService.getAll();
		return enfermedadesAll;
	}

	public void setEnfermedadesAll(List<Enfermedad> enfermedadesAll) {
		this.enfermedadesAll = enfermedadesAll;
	}

	public List<Sintoma> getSintomaAll() {
		sintomaAll = sintomaService.getAll();
		return sintomaAll;
	}

	public void setSintomaAll(List<Sintoma> sintomaAll) {
		this.sintomaAll = sintomaAll;
	}

	public String backToConsultar() {
		par = new Par();
		punto1 = new Punto();
		punto2 = new Punto();
		grupoSeleccionado = new Grupo();
		par = new Par();
		par.setParPunto1(punto1);
		par.setParPunto2(punto2);
		par.setParGrupo(grupoSeleccionado);
		return "pm:consultarPares?transition=flip";
	}

	public String toRegistrar() {
		par = new Par();
		punto1 = new Punto();
		punto2 = new Punto();
		par = new Par();
		par.setParPunto1(punto1);
		par.setParPunto2(punto2);
		par.setParGrupo(grupoSeleccionado);
		return "pm:nuevoPar?transition=flip";
	}

	public List<Punto> autoCompletar(String query) {
		List<Punto> allPuntos = puntoService.getAllPuntos();
		List<Punto> puntosFiltrados = new ArrayList<Punto>();

		for (int i = 0; i < allPuntos.size(); i++) {
			Punto punto = allPuntos.get(i);
			if (punto.getNombre().toLowerCase().startsWith(query)) {
				puntosFiltrados.add(punto);
			}
		}
		return puntosFiltrados;
	}

	public String agregarPar() {
			par.setParGrupo(grupoSeleccionado);
			par.setParPunto1(punto1);
			par.setParPunto2(punto2);
		if (parService.savePar(par)) {
			grupoSeleccionado = new Grupo();
			StaticUtil.correctMesage("Éxito", "Se ha añadido correctamente el par");
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			context.getFlash().setKeepMessages(true);
			return "pm:agregarPar?transtion=flip";
		} else {
			StaticUtil.errorMessage("Error", "Hubo un error al añadir el par");
			return null;
		}
	}
	
	public String cargarPar(int id){
		parSeleccionado = parService.parById(id);
		enfermedadesPar = parService.getEnfermedades(parSeleccionado);
		sintomasPar = parService.getSintomas(parSeleccionado);
		return "pm:detallePar?transition=flip";
	}		
	
	public String backToDetalle(){
		enfermedadAdd = new Enfermedad();
		sintomaAdd = new Sintoma();
		enfermedadesPar = parService.getEnfermedades(parSeleccionado);
		sintomasPar = parService.getSintomas(parSeleccionado);
		par = new Par();
		par.setParPunto1(punto1);
		par.setParPunto2(punto2);
		par.setParGrupo(grupoSeleccionado);
		return "pm:detallePar?transition=flip";
	}
	
	public String addEnfermedad(){
		EnfermedadPar toAdd = new EnfermedadPar();
		toAdd.setExpEnfermedad(enfermedadAdd);
		toAdd.setExpPar(parSeleccionado);
		if(enfermedadService.saveEnfermedadPar(toAdd)){
			StaticUtil.correctMesage("Éxito", "Se ha añadido correctamente la enfermedad");
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			context.getFlash().setKeepMessages(true);
			return backToDetalle();
		}else{
			StaticUtil.correctMesage("Error", "No se pudo añadir la enfermedads");
			return null;
		}	
	}
	
	public void cargarPreRemove(int id){		
		enfermedadAdd = enfermedadService.getById(id);
	}

	public void removeEnfermedadPar(){
		expToDelete = enfermedadService.getByParameters(enfermedadAdd, parSeleccionado);
		enfermedadService.deleteEnfermedadPar(expToDelete);
		enfermedadAdd = new Enfermedad();
	}
	
	public void cancelar(){
		enfermedadAdd = new Enfermedad();		
	}
	
	public void cancelarPar(){
		parSeleccionado = new Par();		
	}
	
	public void cargarRemovePar(int id){
		parSeleccionado = parService.parById(id);
	}
	
	public void removePar(){
		parService.deletePar(parSeleccionado);
		parSeleccionado = new Par();
	}
	
	public String cargarUpdatePar(int id){
		par = parService.parById(id);
		return "pm:editarPar?transition=flip";
	}
	
	public String mergePar(){
		parService.updatePar(par);
		par = new Par();
		return "pm:consultarPares?transition=flip";
	}
	
}

