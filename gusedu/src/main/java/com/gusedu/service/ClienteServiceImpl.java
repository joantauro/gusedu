package com.gusedu.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.gusedu.model.Cliente;
import com.gusedu.model.Persona;

@Service
public class ClienteServiceImpl implements ClienteService{

	@PersistenceContext
	EntityManager em;
	
	@Override
	public boolean saveCliente(Cliente cliente) {
		boolean resultado = false;
		try {
			em.persist(cliente);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Override
	public boolean updateCliente(Cliente cliente) {
		boolean resultado = false;
		try {
			em.merge(cliente);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Override
	public boolean deleteCliente(Cliente cliente) {
		boolean resultado = false;
		try {
			em.remove(em.getReference(Cliente.class, cliente.getIdCliente()));
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Override
	public List<Cliente> getClientes() {
		List<Cliente> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT c FROM Cliente c");
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

}
