package com.gusedu.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gusedu.model.Mensaje;

@Service
public class MensajeServiceImpl implements MensajeService{

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public boolean saveMensaje(Mensaje mensaje) {
		boolean resultado = false;
		try {
			em.persist(mensaje);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public boolean updateMensaje(Mensaje mensaje) {
		boolean resultado = false;
		try {
			em.merge(mensaje);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public boolean deleteMensaje(Mensaje mensaje) {
		boolean resultado = false;
		try {
			em.remove(em.getReference(Mensaje.class, mensaje.getIdMensaje()));
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}


}
