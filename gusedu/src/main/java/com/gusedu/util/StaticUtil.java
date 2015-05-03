package com.gusedu.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import com.gusedu.model.Usuario;

public class StaticUtil {
	public static final String PATTERN_NUMEROS = ".*[^0-9].*";
	public static void correctMesage(String titulo, String mensaje) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				titulo, mensaje));
	}

	public static void errorMessage(String titulo, String mensaje) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
				titulo, mensaje));
	}
	
	public static String userLogged(){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		String username = ((Usuario)(request.getSession().getAttribute("userLogged"))).getEmpresa();
		return username;
	}

	public static Date getFechaActual() {
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		Date date = new Date(stamp.getTime());
		return date;
	}

	public static void keepMessages(){
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		context.getFlash().setKeepMessages(true);
	}
	
	public static String signoZodiacal(int mes, int dia) {
		switch (mes+1) {
		case 1:
			if (dia >= 21)
				return "Acuario";
			else
				return "Capricornio";
		case 2:
			if (dia >= 20)
				return "Piscis";
			else
				return "Acuario";
		case 3:
			if (dia >= 21)
				return "Aries";
			else
				return "Piscis";
		case 4:
			if (dia >= 21)
				return "Tauro";
			else
				return "Aries";
		case 5:
			if (dia >= 21)
				return "Géminis";
			else
				return "Tauro";

		case 6:
			if (dia >= 21)
				return "Cáncer";
			else
				return "Géminis";
		case 7:
			if (dia >= 23)
				return "Leo";
			else
				return "Cáncer";
		case 8:
			if (dia >= 23)
				return "Virgo";
			else
				return "Leo";
		case 9:
			if (dia >= 23)
				return "Libra";
			else
				return "Virgo";
		case 10:
			if (dia >= 23)
				return "Escorpio";
			else
				return "Libra";
		case 11:
			if (dia >= 23)
				return "Sagitario";
			else
				return "Escorpio";
		case 12:
			if (dia >= 22)
				return "Capricornio";
			else
				return "Sagitario";
		default:
			return null;
		}
	}
	
	public static Date sumarRestarDiasFecha(Date fecha, int mes,String tiempo){
	      Calendar calendar = Calendar.getInstance();
	      calendar.setTime(fecha); // Configuramos la fecha que se recibe
	      if(tiempo.equals("Dias"))
	      {
	    	  calendar.add(Calendar.DAY_OF_YEAR, mes);  // numero de mes a añadir, o restar en caso de días<0
	      }else
	      {
	    	  calendar.add(Calendar.MONTH, mes);
	      }
	      return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
	 } 
	
	@SuppressWarnings("deprecation")
	public static long diasRestantes(Date fecha) {
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
		Date hoy = new Date();
		hoy.setHours(0);
		hoy.setMinutes(0);
		hoy.setSeconds(0);
		fecha.setHours(23);
		fecha.setMinutes(59);
		fecha.setSeconds(59);
		long diferencia = ((fecha.getTime() - hoy.getTime()) / MILLSECS_PER_DAY);
		if (diferencia <0) {
			diferencia = 0;
		}
		return diferencia;

	}
	
    public static boolean esSoloNumero(String texto) {
        Pattern pattern = Pattern.compile(PATTERN_NUMEROS);
        Matcher matcher = pattern.matcher(texto);
        return matcher.find() != true ? true : false;
    }
	
    public static void execute(String dialogVar){
    	RequestContext context = RequestContext.getCurrentInstance();
    	context.execute("PF('"+dialogVar+"').show();");
    }
    
	public static void Eleccion(String opcion)
	{
		RequestContext context = RequestContext.getCurrentInstance();
		if(opcion.equals("DC"))//---- Abre la pantalla de datos Clinicos
		{
			context.execute("PF('dlgDC').show();");
		}
		if(opcion.equals("P"))//---- Abre la pantalla de Productos
		{
			context.execute("PF('dlgP').show();");
		}
		if(opcion.equals("T"))//---- Abre la pantalla de terapias
		{
			context.execute("PF('dlgT').show();");
		}
		if(opcion.equals("De"))//---- Abre la pantalla de detalles de paciente
		{
			//context.execute("PF('dlgDC').show();");
		}
		if(opcion.equals("DM"))
		{
			context.execute("PF('dlgHEA').show();");
		}
	}
}
