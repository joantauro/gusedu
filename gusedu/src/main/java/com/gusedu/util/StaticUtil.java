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
	
}
