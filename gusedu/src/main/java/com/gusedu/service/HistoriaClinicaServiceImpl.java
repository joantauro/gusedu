package com.gusedu.service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gusedu.model.HistoriaClinica;
import com.gusedu.model.Visita;

@Service
public class HistoriaClinicaServiceImpl implements HistoriaClinicaService {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public boolean saveHistoriaClinica(HistoriaClinica historiaClinica) {
		boolean resultado = false;
		try {
			em.merge(historiaClinica);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Override
	public HistoriaClinica getHistoriaByVisita(Visita visita) {
		HistoriaClinica result = null;
		try{
			Query q = em.createQuery("SELECT hc FROM HistoriaClinica hc WHERE hc.hclVisita=:visita");
			q.setParameter("visita", visita);
			result = (HistoriaClinica) q.getSingleResult();
			
		}
		catch(NoResultException e){
			System.out.println("ERROR: "+e.getMessage());
		}
		return result;
	}

}
