package com.gusedu.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gusedu.model.Grupo;

@Service
public class GrupoServiceImpl implements GrupoService{

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public List<Grupo> getAllGrupos() {
		List<Grupo> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT g FROM Grupo g");
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	
	
}
