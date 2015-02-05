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
import com.gusedu.model.Grupo;
import com.gusedu.model.Par;
import com.gusedu.model.Punto;
import com.gusedu.model.Sintoma;
import com.gusedu.model.SintomaPar;

@Service
public class ParServiceImpl implements ParService{

	@PersistenceContext
	EntityManager em;
	
	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
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

	@Transactional
	public Par parByPuntos(Punto p1, Punto p2, Grupo g) {
		Par result = null;
		try{
			Query q = em.createQuery("SELECT p FROM Par p WHERE p.parPunto1=:p1 AND p.parPunto2=:p2 AND p.parGrupo=:g");
			q.setParameter("p1", p1);
			q.setParameter("p2", p2);
			q.setParameter("g", g);
			result = (Par) q.getSingleResult();
			
		}
		catch(NoResultException e){
			System.out.println("ERROR: "+e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Par> getAllParesOrderByP1() {
		List<Par> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT p FROM Par p ORDER BY p.parPunto1.nombre ASC");
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;	
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Par> getAllParesOrderByP2() {
		List<Par> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT p FROM Par p ORDER BY p.parPunto2.nombre ASC");
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Par> getAllParesOrderGoiz() {
		List<Par> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT p FROM Par p ORDER BY p.parPunto1.ordenGoiz ASC");
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<EnfermedadPar> parByEnfermedad(Enfermedad enfermedad) {
		List<EnfermedadPar> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT p FROM EnfermedadPar p WHERE p.expEnfermedad=:enfermedad");
			q.setParameter("enfermedad", enfermedad);
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SintomaPar> parBySintoma(Sintoma sintoma) {
		List<SintomaPar> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT p FROM SintomaPar p WHERE p.sxpSintoma=:sintoma");
			q.setParameter("sintoma", sintoma);
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}
	
}
