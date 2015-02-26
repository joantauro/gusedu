package com.gusedu.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gusedu.model.Producto;
import com.gusedu.model.Punto;

@Service
public class ProductoServiceImpl implements ProductoService{

	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Producto> getAllProductos() {
		List<Producto> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT p FROM Producto p");
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@Transactional
	public Producto getProductoById(Integer idProducto) {
		return em.find(Producto.class, idProducto);
	}
	
}
