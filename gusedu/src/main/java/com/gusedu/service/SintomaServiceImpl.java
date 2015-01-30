package com.gusedu.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gusedu.model.Sintoma;

@Service
public class SintomaServiceImpl implements SintomaService{

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public List<Sintoma> getAll() {
		List<Sintoma> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT s FROM Sintoma s");
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

}
