package com.gusedu.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gusedu.model.TerapiaPar;

@Service
public class TerapiaParServiceImpl implements TerapiaParService{

	
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public boolean saveTerapia(TerapiaPar terapia) {
		boolean resultado = false;
		try {
			em.persist(terapia);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public boolean updateTerapia(TerapiaPar Terapia) {
		boolean resultado = false;
		try {
			em.merge(Terapia);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public boolean deleteTerapia(TerapiaPar terapia) {
		boolean resultado = false;
		try {
			em.remove(terapia);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

}
