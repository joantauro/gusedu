package com.gusedu.controller;

import java.io.Serializable;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gusedu.service.ClienteService;

@Component
@Scope(value="session")
public class OpcionTerapeutaBean implements Serializable{
	RequestContext context = RequestContext.getCurrentInstance();
	
	@Autowired
	ClienteService clienteService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String opcion;

	public OpcionTerapeutaBean()
	{
		opcion="S";
	}

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}
	
	public void Eleccion(String opcion)
	{
		if(opcion.equals("DC"))//---- Abre la pantalla de datos Clinicos
		{
			context.execute("PF('dlgDC').show();");
		}
		if(opcion.equals("P"))//---- Abre la pantalla de Productos
		{
			//context.execute("PF('dlgDC').show();");
		}
		if(opcion.equals("T"))//---- Abre la pantalla de terapias
		{
			//context.execute("PF('dlgDC').show();");
		}
		if(opcion.equals("De"))//---- Abre la pantalla de detalles de paciente
		{
			//context.execute("PF('dlgDC').show();");
		}
	}
	/*
	public String preDatosClinicosPaciente(Integer idCliente){
		cliente = clienteService.getClienteById(idCliente);
		return "datosClinicos?faces-redirect=true";
	}*/
}
