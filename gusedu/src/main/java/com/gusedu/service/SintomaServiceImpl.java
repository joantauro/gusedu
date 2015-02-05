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
import com.gusedu.model.Sintoma;

@Service
public class SintomaServiceImpl implements SintomaService{

	@PersistenceContext
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Sintoma> getAll() {
		List<Sintoma> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT s FROM Sintoma s");
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@Transactional
	public boolean saveSintoma(Sintoma sintoma) {
		boolean resultado = false;
		try {
			em.persist(sintoma);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public boolean updateSintoma(Sintoma sintoma) {
		boolean resultado = false;
		try {
			em.merge(sintoma);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public boolean deleteSintoma(Sintoma sintoma) {
		boolean resultado = false;
		try {
			em.remove(em.getReference(Sintoma.class, sintoma.getIdSintoma()));
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public Sintoma getById(Integer id) {
		return em.find(Sintoma.class, id);
	}

	@Transactional
	public Sintoma getByNombre(String nombre) {
		Sintoma result = null;
		try {
			Query q = em
					.createQuery("SELECT s FROM Sintoma s WHERE s.descripcion=:nombre");
			q.setParameter("nombre", nombre);
			result = (Sintoma) q.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;				
	}

}
