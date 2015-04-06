package com.gusedu.service;

import com.gusedu.model.Usuario;

public interface AuthService{

	public Usuario find(String username, String password);
}
