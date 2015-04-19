package com.gusedu.service;

import java.util.List;

import com.gusedu.model.TipoUsuario;
import com.gusedu.model.Usuario;

public interface UsuarioService {
	
	// Usuario
	public boolean saveUsuario(Usuario usuario);
	
	public boolean updateUsuario(Usuario usuario);
	
	public boolean deleteUsuario(Usuario usuario);
	
	public List<Usuario> getAll();	
	
	public Usuario getUsuarioeById(Integer idUsuario);
	
	// Tipo Usuario
	
	public List<TipoUsuario> getAllTipoUsuarios();
	
	public List<Usuario> getAllFinMembresia(int n);
	
	public void updateUsuarioFinMembresia();
	


}
