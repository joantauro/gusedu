package com.gusedu.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gusedu.model.Enfermedad;
import com.gusedu.model.Punto;
import com.gusedu.service.EnfermedadService;

@Service
public class EnfermedadConverter implements Converter {

	@Autowired
	EnfermedadService enfermedadService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		try {
			Enfermedad enfermedad = enfermedadService.getByNombre(value);
			return enfermedad;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La enfermedad elegida no es correcta"));
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value == null || value.equals("")
				|| ((Enfermedad) value).getIdEnfermedad() == null
				|| String.valueOf(((Enfermedad) value).getNombre()).equals("")) {
			return "";
		}
		return String.valueOf(((Enfermedad) value).getNombre());
	}

}
