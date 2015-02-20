package com.gusedu.util;

import java.sql.Timestamp;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class StaticUtil {

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
	
}
