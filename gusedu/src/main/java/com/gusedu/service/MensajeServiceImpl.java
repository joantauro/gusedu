package com.gusedu.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.gusedu.model.Mensaje;
import com.gusedu.model.Persona;

@Service
public class MensajeServiceImpl implements MensajeService{

	@PersistenceContext
	EntityManager em;
	
	@Override
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

	@Override
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

	@Override
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
