package com.gusedu.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gusedu.model.Terapia;
import com.gusedu.model.Visita;

@Service
public class TerapiaServiceImpl implements TerapiaService{

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public boolean saveTerapia(Terapia terapia) {
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
	public boolean updateTerapia(Terapia terapia) {
		boolean resultado = false;
		try {
			em.merge(terapia);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;		
	}

	@Transactional
	public boolean deleteTerapia(Terapia terapia) {
		boolean resultado = false;
		try {
			em.remove(em.getReference(Terapia.class, terapia.getIdTerapia()));
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public List<Terapia> terapiasPorVisita(Visita visita) {
		List<Terapia> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT t FROM Terapia WHERE t.terVisita=:visita");
			q.setParameter("visita", visita);
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

}
