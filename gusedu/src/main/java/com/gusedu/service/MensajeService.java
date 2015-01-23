package com.gusedu.service;

import com.gusedu.model.Mensaje;

public interface MensajeService {

	public boolean saveMensaje(Mensaje mensaje);
	
	public boolean updateMensaje(Mensaje mensaje);
	
	public boolean deleteMensaje(Mensaje mensaje);
	
	
}
