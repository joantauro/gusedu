package com.gusedu.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gusedu.model.Sintoma;
import com.gusedu.service.SintomaService;
import com.gusedu.util.StaticUtil;

@Component
@Scope(value="session")
public class SintomaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Sintoma sintoma;
	private List<Sintoma> sintomas;

	private String query;
	
	@Autowired
	SintomaService sintomaService;

	public SintomaBean() {
		sintoma = new Sintoma();
	}

	public Sintoma getSintoma() {
		return sintoma;
	}

	public void setSintoma(Sintoma sintoma) {
		this.sintoma = sintoma;
	}

	public List<Sintoma> getSintomas() {
		if(query != null){
			if(!query.isEmpty()){
				return sintomas;
			}
		}
		return sintomaService.getAll();		
	}

	public void listarsintomas(){
		sintomas = sintomaService.getAll();
	}
	
	public void setSintomas(List<Sintoma> sintomas) {
		this.sintomas = sintomas;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String preAdd() {
		sintoma = new Sintoma();
		return "pm:agregarSintoma?transition=flip";
	}
	
	public void preAddWeb() {
		sintoma = new Sintoma();
 
	}

	public String add() {
		if (esRepetido()) {
			StaticUtil.errorMessage("Error", "El nombre est� duplicado");
			return null;
		}
		if (sintomaService.saveSintoma(sintoma)) {
			StaticUtil.correctMesage("�xito", "Se ha registrado correctamente el s�ntoma");
			return "pm:gestionSintoma";
		} else {
			StaticUtil.errorMessage("Error", "No se pudo registrar el s�ntoma");
			return null;
		}
	}

	
	public void addWeb() {
		if (esRepetido()) {
			StaticUtil.errorMessage("Error", "El nombre est� duplicado");
			sintoma = new Sintoma();
			return ;
		}
		if (sintomaService.saveSintoma(sintoma)) {
			StaticUtil.correctMesage("�xito", "Se ha registrado correctamente el s�ntoma");
			sintoma = new Sintoma();

		} else {
			StaticUtil.errorMessage("Error", "No se pudo registrar el s�ntoma");
		 
		}
	}
	
	public void prueba(){
		System.out.println("Prueba : Exito :3");
	}
	
	public void add2(String descripcion)
	{
		sintoma = new Sintoma();
		sintoma.setIdSintoma(10);
		sintoma.setDescripcion(descripcion);
		System.out.println("Descripcion Sintoma : "+sintoma.getDescripcion());
		if (sintomaService.saveSintoma(sintoma)) {
			StaticUtil.correctMesage("�xito", "Se ha registrado correctamente el s�ntoma");
			 
		} else {
			StaticUtil.errorMessage("Error", "No se pudo registrar el s�ntoma");
			 
		}
	}
	
	public String preUpdate(int id) {
		sintoma = sintomaService.getById(id);
		return "pm:editarSintoma?transition=flip";
	}
	
	public void cargaSintoma(Sintoma sin)
	{
		sintoma=sin;
	}

	public String update() {
		if (sintomaService.updateSintoma(sintoma)) {
			StaticUtil.correctMesage("�xito", "Se ha actualizado correctamente el sintoma");			
			return "pm:gestionSintoma";
		} else {
			StaticUtil.errorMessage("Error", "No se pudo actualizar el sintoma");
			return null;
		}
	}
	
	public void updateWeb() {
		if (sintomaService.updateSintoma(sintoma)) {
			StaticUtil.correctMesage("�xito", "Se ha actualizado correctamente el sintoma");	
			sintoma = new Sintoma();
			listarsintomas();
		} else {
			StaticUtil.errorMessage("Error", "No se pudo actualizar el sintoma");
		}
	}

	public void preDelete(int id) {
		sintoma = sintomaService.getById(id);
	}

	public void delete() {
		if(sintomaService.deleteSintoma(sintoma)){
			StaticUtil.correctMesage("�xito", "Se ha actualizado correctamente el sintoma");
			listarsintomas();
		}else{
			StaticUtil.errorMessage("Error", "No se pudo actualizar el s�ntoma");
		}
		sintoma = new Sintoma();
	}

	public void cancelar() {
		sintoma = new Sintoma();
	}

	public boolean esRepetido() {
		boolean repetido = false;
		if (sintoma.getDescripcion() == null) {
			return repetido;
		}
		for (Sintoma s : sintomaService.getAll()) {
			if (sintoma.getDescripcion().equalsIgnoreCase(s.getDescripcion())) {
				repetido = true;
				return repetido;
			}
		}
		return repetido;
	}

	public void filtrarBusqueda() {
		sintomas = sintomaService.getAll();
		List<Sintoma> filtrados = new ArrayList<>();
		for (Sintoma s : sintomas) {
			if (s.getDescripcion().toLowerCase().contains(query.toLowerCase())) {
				filtrados.add(s);
			}
		}
		sintomas = filtrados;
	}

	
}
