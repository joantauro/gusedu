package com.gusedu.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gusedu.model.Punto;
import com.gusedu.service.PuntoService;


@Service 
public class PuntoConverter implements Converter{
	
	@Autowired
	PuntoService puntoService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if(value==null || value.isEmpty()){
			return null;
		}
		try{				
			Punto punto = puntoService.puntoByNombre(value);
			return punto;
		}catch(Exception e){
			e.printStackTrace();
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El punto elegido no es válido"));
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(value==null || value.equals("") 
			|| ((Punto)value).getIdPunto()==null || String.valueOf(((Punto)value).getNombre()).equals("")){
			return "";
		}
		return String.valueOf(((Punto)value).getNombre());
	}

}
