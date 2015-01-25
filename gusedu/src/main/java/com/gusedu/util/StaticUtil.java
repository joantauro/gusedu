package com.gusedu.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class StaticUtil {

	public static void correctMesage(String titulo, String mensaje){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensaje));
	}
	
	public static void errorMessage(String titulo, String mensaje){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo, mensaje));
	}	
	
	
	
}
