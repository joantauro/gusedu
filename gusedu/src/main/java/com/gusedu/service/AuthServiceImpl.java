package com.gusedu.service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gusedu.model.Usuario;

@Service
public class AuthServiceImpl implements AuthService{

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public Usuario find(String username, String password) {
		Usuario result = null;
		try {
			Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.usuario=:usuario AND u.password=:password");
			q.setParameter("usuario", username);
			q.setParameter("password", password);
			result = (Usuario) q.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}				
		return result;
	}

}
