package com.gusedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gusedu.model.Punto;
import com.gusedu.service.PuntoService;

@Controller
public class PuntoBean {

	@Autowired
	PuntoService puntoService;
	
	private Punto punto;
	private List<Punto> puntos;
	
	public PuntoBean(){
		punto = new Punto();
	}

	public Punto getPunto() {
		return punto;
	}

	public void setPunto(Punto punto) {
		this.punto = punto;
	}

	public List<Punto> getPuntos() {
		puntos = puntoService.getAllPuntos();
		return puntos;
	}

	public void setPuntos(List<Punto> puntos) {
		this.puntos = puntos;
	}
		
	public String detallePunto(Integer id){
		punto = puntoService.puntoById(id);
		return "pm:detallePunto?transition=flip";
	}		
	
	public void cargarPunto(Integer id){
		punto = puntoService.puntoById(id);
	}
	
	public void eliminarPunto(){		
		puntoService.deletePunto(punto);
		punto = new Punto();		
	}
	
	public void cancelar(){
		punto = new Punto();	
	}
	
}
