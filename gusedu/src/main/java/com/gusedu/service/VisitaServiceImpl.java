package com.gusedu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gusedu.model.Cliente;
import com.gusedu.model.Visita;

@Service
public class VisitaServiceImpl implements VisitaService{

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public boolean saveVisita(Visita visita) {
		boolean resultado = false;
		try {
			em.persist(visita);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public boolean updateVisita(Visita visita) {
		boolean resultado = false;
		try {
			em.merge(visita);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public boolean deleteVisita(Visita visita) {
		boolean resultado = false;
		try {
			em.remove(em.getReference(Visita.class, visita.getIdVisita()));
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Visita> getVisitasCliente(Cliente cliente) {
		List<Visita> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT v FROM Visita v WHERE v.visCliente=:cliente");
			q.setParameter("cliente", cliente);
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@Override
	public Visita getVisitaById(Integer idVisita) {
		return em.find(Visita.class, idVisita);
	}

	@Override
	public Visita getLastVisitaCliente(Cliente cliente) {
		Visita result = null;
		try {
			Query q = em.createQuery("SELECT v FROM Visita v WHERE v.visCliente=:cliente ORDER BY v.fechaCreacion DESC");
			q.setParameter("cliente", cliente);
			q.setMaxResults(1);
			result = (Visita) q.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Visita buscarVisita(Cliente cliente) {
		Visita result = null; 

		Date fecha=new Date();
		int mes=fecha.getMonth()+1;
		int a�o=(fecha.getYear()+1900);
		int dia=fecha.getDate();
        String mesM="";
        
        if(mes<10)
        {
             mesM="0";
        }
        String cadena=a�o+"-"+ mesM+mes+"-"+dia;
		try {

			Query q = em.createQuery("SELECT v  FROM Visita v WHERE SUBSTRING(v.fechaCreacion,1,10) =:fecha AND v.visCliente=:cliente");
			q.setParameter("fecha", cadena);
			q.setParameter("cliente", cliente);

			result = (Visita) q.getSingleResult();

		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

}
