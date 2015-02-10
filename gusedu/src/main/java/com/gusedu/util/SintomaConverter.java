package com.gusedu.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gusedu.model.Sintoma;
import com.gusedu.service.SintomaService;

@Service
public class SintomaConverter implements Converter{

	@Autowired
	SintomaService sintomaService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		try {			
			Sintoma sintoma = sintomaService.getByNombre(value);
			return sintoma;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El sintoma no es correcto"));
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value == null || value.equals("")
				|| ((Sintoma) value).getIdSintoma()== null
				|| String.valueOf(((Sintoma) value).getDescripcion()).equals("")) {
			return "";
		}
		return String.valueOf(((Sintoma) value).getDescripcion());
	}

	
	
}
