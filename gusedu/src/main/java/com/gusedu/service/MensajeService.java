package com.gusedu.service;

import java.util.List;

import com.gusedu.model.Mensaje;
import com.gusedu.model.Persona;

public interface MensajeService {

	public boolean saveMensaje(Mensaje mensaje);
	
	public boolean updateMensaje(Mensaje mensaje);
	
	public boolean deleteMensaje(Mensaje mensaje);
	
	
}
