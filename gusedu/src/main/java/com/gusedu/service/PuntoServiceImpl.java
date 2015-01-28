package com.gusedu.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gusedu.model.Punto;

@Service
public class PuntoServiceImpl implements PuntoService{

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public List<Punto> getAllPuntos() {
		List<Punto> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT p FROM Punto p");
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	
	
}
