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
import com.gusedu.model.Par;
import com.gusedu.model.Sintoma;
import com.gusedu.model.SintomaPar;

@Service
public class EnfermedadServiceImpl implements EnfermedadService {

	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
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
			em.remove(em.getReference(EnfermedadPar.class,
					enfermedadPar.getIdEnfermedadPar()));
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
			Query q = em
					.createQuery("SELECT exp FROM EnfermedadPar exp WHERE exp.expPar=:par "
							+ "AND expEnfermedad=:enfermedad");
			q.setParameter("par", par);
			q.setParameter("enfermedad", enfermedad);
			result = (EnfermedadPar) q.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@Transactional
	public boolean updateEnfermedad(Enfermedad enfermedad) {
		boolean resultado = false;
		try {
			em.merge(enfermedad);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;

	}

	@Transactional
	public boolean deleteEnfermedad(Enfermedad enfermedad) {
		boolean resultado = false;
		try {
			em.remove(em.getReference(Enfermedad.class,
					enfermedad.getIdEnfermedad()));
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public boolean saveSintomaPar(SintomaPar sintomaPar) {
		boolean resultado = false;
		try {
			em.persist(sintomaPar);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public boolean deleteSintomaPar(SintomaPar sintomaPar) {
		boolean resultado = false;
		try {
			em.remove(em.getReference(SintomaPar.class,
					sintomaPar.getIdSintomaPar()));
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public SintomaPar getByParameters(Sintoma sintoma, Par par) {
		SintomaPar result = null;
		try {
			Query q = em
					.createQuery("SELECT sxp FROM SintomaPar sxp WHERE sxp.sxpPar=:par "
							+ "AND sxp.sxpSintoma=:sintoma");
			q.setParameter("par", par);
			q.setParameter("sintoma", sintoma);
			result = (SintomaPar) q.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@Transactional
	public Enfermedad getByNombre(String nombre) {
		Enfermedad result = null;
		try {
			Query q = em
					.createQuery("SELECT e FROM Enfermedad e WHERE e.nombre=:nombre");
			q.setParameter("nombre", nombre);
			result = (Enfermedad) q.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}
}
