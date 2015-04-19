package com.gusedu.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gusedu.model.TipoUsuario;

@Service
public class TipoUsuarioServiceImpl implements TipoUsuarioService{

	
	@PersistenceContext
	EntityManager em;
	
	
	@Transactional
	public boolean saveTipoUsuario(TipoUsuario tipousuario) {
		boolean resultado = false;
		try {
			em.persist(tipousuario);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public boolean updateTipoUsuario(TipoUsuario tipousuario) {
		boolean resultado = false;
		try {
			em.merge(tipousuario);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public boolean deleteTipoUsuario(TipoUsuario tipousuario) {
		boolean resultado = false;
		try {
			em.remove(em.getReference(TipoUsuario.class, tipousuario.getIdTipoUsuario()));
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<TipoUsuario> getAll() {
		List<TipoUsuario> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT tu FROM TipoUsuario tu");
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@Override
	public TipoUsuario getUsuarioeById(Integer idTipoUsuario) {
		return em.find(TipoUsuario.class, idTipoUsuario);
	}

}
