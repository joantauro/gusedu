package com.gusedu.service;

import com.gusedu.model.TerapiaPar;

public interface TerapiaParService {
	
	public boolean saveTerapia(TerapiaPar terapia);
	
	public boolean updateTerapia(TerapiaPar Terapia);
	
	public boolean deleteTerapia(TerapiaPar terapia);
}
