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
import com.gusedu.model.Par;
import com.gusedu.model.Sintoma;

@Service
public class ParServiceImpl implements ParService{

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public List<Par> getAllPares() {
		List<Par> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT p FROM Par p");
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@Transactional
	public Par parById(Integer id) {
		return em.find(Par.class, id);
	}

	@Transactional
	public Boolean savePar(Par par) {
		boolean resultado = false;
		try {
			em.persist(par);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public Boolean updatePar(Par par) {
		boolean resultado = false;
		try {
			em.merge(par);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public Boolean deletePar(Par par) {
		boolean resultado = false;
		try {
			em.remove(em.getReference(Par.class, par.getIdPar()));
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public List<Enfermedad> getEnfermedades(Par par) {
		List<Enfermedad> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT e.expEnfermedad FROM EnfermedadPar e WHERE e.expPar=:par");
			q.setParameter("par", par);
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@Transactional
	public List<Sintoma> getSintomas(Par par) {
		List<Sintoma> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT s.sxpSintoma FROM SintomaPar s WHERE s.sxpPar=:par");
			q.setParameter("par", par);
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}
	
}
