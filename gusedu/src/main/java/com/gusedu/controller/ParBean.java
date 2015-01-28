package com.gusedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gusedu.model.Par;
import com.gusedu.service.ParService;

@Controller
public class ParBean {

	@Autowired
	ParService parService;
	
	private Par par;
	private List<Par> pares;
	
	public ParBean(){
		
	}

	public Par getPar() {
		return par;
	}

	public void setPar(Par par) {
		this.par = par;
	}

	public List<Par> getPares() {
		pares = parService.getAllPares();
		return pares;
	}

	public void setPares(List<Par> pares) {
		this.pares = pares;
	}
		
	
}
