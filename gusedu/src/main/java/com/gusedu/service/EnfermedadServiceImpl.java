package com.gusedu.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gusedu.model.Enfermedad;
import com.gusedu.model.EnfermedadPar;

@Service
public class EnfermedadServiceImpl implements EnfermedadService{

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public List<Enfermedad> getAll() {
		List<Enfermedad> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT e FROM Enfermedad e");
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@Transactional
	public Boolean saveEnfermedad(Enfermedad enfermedad) {
		boolean resultado = false;
		try {
			em.persist(enfermedad);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public Boolean saveEnfermedadPar(EnfermedadPar enfermedadPar) {
		boolean resultado = false;
		try {
			em.persist(enfermedadPar);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

}
