package com.gusedu.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gusedu.model.Cliente;
import com.gusedu.model.Enfermedad;
import com.gusedu.model.EnfermedadPar;
import com.gusedu.model.Par;

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
	public boolean saveEnfermedad(Enfermedad enfermedad) {
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
	public boolean saveEnfermedadPar(EnfermedadPar enfermedadPar) {
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

	@Transactional
	public boolean deleteEnfermedadPar(EnfermedadPar enfermedadPar) {
		boolean resultado = false;
		try {
			em.remove(em.getReference(EnfermedadPar.class, enfermedadPar.getIdEnfermedadPar()));
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public Enfermedad getById(Integer idEnfermedad) {
		return em.find(Enfermedad.class, idEnfermedad);
	}
	
	@Transactional
	public EnfermedadPar getByIdPar(Integer idEnfermedadPar) {
		return em.find(EnfermedadPar.class, idEnfermedadPar);
	}

	@Transactional
	public EnfermedadPar getByParameters(Enfermedad enfermedad, Par par) {
		EnfermedadPar result = null;
		try {
			Query q = em.createQuery("SELECT exp FROM EnfermedadPar exp WHERE exp.expPar=:par " +
					"AND expEnfermedad=:enfermedad");
			q.setParameter("par", par);
			q.setParameter("enfermedad", enfermedad);
			result = (EnfermedadPar) q.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}	

}
