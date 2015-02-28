package com.gusedu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gusedu.model.Sintoma;
import com.gusedu.model.SintomaPar;
import com.gusedu.service.ParService;
import com.gusedu.service.SintomaService;

@Component
@Scope(value="session")
public class SintomaParBean {
	
	@Autowired
	SintomaService sintomaService;
	
	@Autowired
	ParService parService;
	
	private Sintoma sintoma;
	private List<Sintoma> sintomas;
	private List<SintomaPar> sintomasPar;
	
	public SintomaParBean(){
		sintoma = new Sintoma();
	}

	public Sintoma getSintoma() {
		return sintoma;
	}

	public void setSintoma(Sintoma sintoma) {
		this.sintoma = sintoma;
	}

	public List<Sintoma> getSintomas() {
		return sintomas;
	}

	public void setSintomas(List<Sintoma> sintomas) {
		this.sintomas = sintomas;
	}

	public List<SintomaPar> getSintomasPar() {
		return sintomasPar;
	}

	public void setSintomasPar(List<SintomaPar> sintomasPar) {
		this.sintomasPar = sintomasPar;
	}

	public List<Sintoma> autoComplete(String query){
		List<Sintoma> allSintomas = sintomaService.getAll();
		List<Sintoma> sintomasFiltrados = new ArrayList<>();
		
		for (int i = 0; i < allSintomas.size(); i++) {
			Sintoma sin = allSintomas.get(i);
			if (sin.getDescripcion().toLowerCase().startsWith(query)) {
				sintomasFiltrados.add(sin);
			}
		}
		return sintomasFiltrados;
	}

	public String cargarSintoma(){
		sintomasPar = parService.parBySintoma(sintoma);
		return "pm:mostrarPares?transition=flip";
	}		
	
	public String toSintomaPar(){
		sintoma = new Sintoma();
		return "pm:sintomaPar?transition=flip";
	}

	
	
}
