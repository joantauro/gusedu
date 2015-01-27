package com.gusedu.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gusedu.model.Cliente;
import com.gusedu.model.Persona;
import com.gusedu.model.Visita;

@Service
public class ClienteServiceImpl implements ClienteService{

	@PersistenceContext
	EntityManager em;
	
	@Transactional
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

	@Transactional
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

	@Transactional
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

	@SuppressWarnings("unchecked")
	@Transactional
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

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Cliente> getClientesPacientes() {
		List<Cliente> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT c FROM Cliente c WHERE c.cliTipoCliente.descripcion=:paciente");
			q.setParameter("paciente", "Paciente");
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@Transactional
	public Cliente getClienteById(Integer idCliente) {
		return em.find(Cliente.class, idCliente);
	}

}
