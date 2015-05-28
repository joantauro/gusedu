package com.gusedu.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gusedu.model.Visita;
import com.gusedu.service.VisitaService;
import com.gusedu.util.StaticUtil;

@Component
@Scope(value="session")
public class HistorialVisitaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	VisitaService visitaService;
	
	private List<Visita> visitas;
	private double costo;
	private Date fechainicial;
	private Date fechafinal;
	private Date fechaactual;
	
	public HistorialVisitaBean()
	{
		Date fec= new Date();
		fechaactual=fec;
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
		String strFecha = "2014-05-21";
		Date fechax = null;
		try {

		fechax = formatoDelTexto.parse(strFecha);
		
		} catch (ParseException ex) {

		ex.printStackTrace();

		}
		fechainicial=fechax;
		fechafinal= fec;
	}

	@PostConstruct
	public void init()
	{
		String username = StaticUtil.userLogged();
		visitas = visitaService.getVisitabyFechas(username,fechainicial, fechafinal);
		costo=0;
		for(int i=0;i<visitas.size();i++)
		{
			costo+=visitas.get(i).getCostoTotal();
		}
	}
	@SuppressWarnings("deprecation")
	public void actualizar()
	{

		if(fechafinal.before(fechainicial)){
			StaticUtil.errorMessage("Error", "Ingreso de fechas incorrecto");
			return ;
		
		}
		fechafinal.setHours(23);
		fechafinal.setMinutes(59);
		fechafinal.setSeconds(59);
		String username = StaticUtil.userLogged();
	visitas = visitaService.getVisitabyFechas(username,fechainicial, fechafinal);
		costo=0;
		for(int i=0;i<visitas.size();i++)
		{
			costo+=visitas.get(i).getCostoTotal();
		}
		System.out.println("Fecha inicial : "+fechainicial);
		System.out.println("Fecha final : "+fechafinal);
		System.out.println("Costo : "+costo);
	}
	
	@SuppressWarnings("deprecation")
	public void today()
	{
		Date fecha1 = new Date();
		fecha1.setHours(0);
		fecha1.setMinutes(0);
		fecha1.setSeconds(0);
		fechainicial=fecha1;
		Date fecha2 = new Date();
		fecha2.setHours(23);
		fecha2.setMinutes(59);
		fecha2.setSeconds(59);
		fechafinal=fecha2;
		actualizar();
	}
	
	public List<Visita> getVisitas() {
		return visitas;
	}

	public void setVisitas(List<Visita> visitas) {
		this.visitas = visitas;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public Date getFechainicial() {
		return fechainicial;
	}

	public void setFechainicial(Date fechainicial) {
		this.fechainicial = fechainicial;
	}

	public Date getFechafinal() {
		return fechafinal;
	}

	public void setFechafinal(Date fechafinal) {
		this.fechafinal = fechafinal;
	}

	public Date getFechaactual() {
		return fechaactual;
	}

	public void setFechaactual(Date fechaactual) {
		this.fechaactual = fechaactual;
	}
	
}
