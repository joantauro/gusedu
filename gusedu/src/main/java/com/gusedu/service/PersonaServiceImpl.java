package com.gusedu.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.gusedu.model.Persona;

public class PersonaServiceImpl implements PersonaService {

	@PersistenceContext
	EntityManager em;

	@Override
	public boolean savePersona(Persona persona) {
		boolean resultado = false;
		try {
			em.persist(persona);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Override
	public boolean updatePersona(Persona persona) {
		boolean resultado = false;
		try {
			em.merge(persona);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Override
	public boolean deletePersona(Persona persona) {
		boolean resultado = false;
		try {
			em.remove(em.getReference(Persona.class, persona.getIdPersona()));
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Persona> getPersonas() {
		List<Persona> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT p FROM Persona p");
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;

	}

	@Override
	public Persona getPersonaById(Integer id) {
		return em.find(Persona.class, id);
	}

}
