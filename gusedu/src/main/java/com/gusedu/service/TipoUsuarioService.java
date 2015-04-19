package com.gusedu.service;

import java.util.List;

import com.gusedu.model.TipoUsuario;

public interface TipoUsuarioService {
	// Tipo Usuario
	public boolean saveTipoUsuario(TipoUsuario tipousuario);
	
	public boolean updateTipoUsuario(TipoUsuario tipousuario);
	
	public boolean deleteTipoUsuario(TipoUsuario tipousuario);
	
	public List<TipoUsuario> getAll();
	
	public TipoUsuario getUsuarioeById(Integer idTipoUsuario);
}
