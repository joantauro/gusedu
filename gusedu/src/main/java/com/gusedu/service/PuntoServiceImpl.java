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
	
	@SuppressWarnings("unchecked")
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

	@Transactional
	public Punto puntoById(Integer id) {
		return em.find(Punto.class, id);
	}

	@Transactional
	public Boolean savePunto(Punto punto) {
		boolean resultado = false;
		try {
			em.persist(punto);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public Boolean updatePunto(Punto punto) {
		boolean resultado = false;
		try {
			em.merge(punto);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public Boolean deletePunto(Punto punto) {
		boolean resultado = false;
		try {
			em.remove(em.getReference(Punto.class, punto.getIdPunto()));
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public Punto puntoByNombre(String nombrePunto) {
		Punto result = null;
		try{					
			Query q = em.createQuery("SELECT p FROM Punto p WHERE p.nombre=:nombre");
			q.setParameter("nombre", nombrePunto);
			result = (Punto) q.getSingleResult();
		}
		catch(Exception e){
			System.out.println("ERROR: " + e.getMessage());
			result = null;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Punto> getAllOrdenAlfabeticoAsc() {
		List<Punto> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT p FROM Punto p WHERE (p.ordenGoiz>0) ORDER BY p.nombre ASC");
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Punto> getAllOrdenAlfabeticoDesc() {
		List<Punto> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT p FROM Punto p WHERE (p.ordenGoiz>0) ORDER BY p.nombre DESC");
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Punto> getAllOrdenGoiz() {
		List<Punto> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT p FROM Punto p WHERE (p.ordenGoiz>0) ORDER BY p.ordenGoiz ASC");
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Punto> getAllPuntosRastreables() {
		List<Punto> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT p FROM Punto p WHERE (p.ordenGoiz>0) ");
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}
	
	
}
