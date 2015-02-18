package com.gusedu.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gusedu.model.EnfermedadTerapia;
import com.gusedu.model.Par;
import com.gusedu.model.SintomaTerapia;
import com.gusedu.model.Terapia;
import com.gusedu.model.TerapiaPar;
import com.gusedu.model.TipoTerapia;
import com.gusedu.model.Visita;

@Service
public class TerapiaServiceImpl implements TerapiaService {

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

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Terapia> terapiasPorVisita(Visita visita) {
		List<Terapia> result = new ArrayList<>();
		try {
			Query q = em
					.createQuery("SELECT t FROM Terapia t WHERE t.terVisita=:visita");
			q.setParameter("visita", visita);
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<TipoTerapia> getTipoTerapias() {
		List<TipoTerapia> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT t FROM TipoTerapia t");			
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@Transactional
	public TipoTerapia tteById(Integer idTipoTerapia) {
		return em.find(TipoTerapia.class, idTipoTerapia);
	}

	@Transactional
	public Terapia terapiaById(Integer idTerapia) {
		return em.find(Terapia.class, idTerapia);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<EnfermedadTerapia> getEnfermedadesByTerapia(Terapia terapia) {
		List<EnfermedadTerapia> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT et FROM EnfermedadTerapia et WHERE et.extTerapia=:terapia");
			q.setParameter("terapia", terapia);
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<SintomaTerapia> getSintomasByTerapia(Terapia terapia) {
		List<SintomaTerapia> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT st FROM SintomaTerapia st WHERE st.sxtTerapia=:terapia");
			q.setParameter("terapia", terapia);
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@Transactional
	public boolean saveTerapiaPar(TerapiaPar terapiaPar) {
		boolean resultado = false;
		try {
			em.persist(terapiaPar);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Par> getTerapiaParesFromTerapia(Terapia terapia) {
		List<Par> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT tp.txpPar FROM TerapiaPar tp WHERE tp.txpTerapia=:terapia");
			q.setParameter("terapia", terapia);
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@Override
	public TerapiaPar TerapiaParByParAndTerapia(Terapia terapia, Par par) {
		TerapiaPar result = null;
		try {
			Query q = em.createQuery("SELECT tp FROM TerapiaPar tp WHERE tp.txpTerapia=:terapia AND tp.txpPar=:par");
			q.setParameter("terapia", terapia);
			q.setParameter("par", par);
			result = (TerapiaPar) q.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

}
