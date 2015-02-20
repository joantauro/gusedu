package com.gusedu.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gusedu.model.Cliente;
import com.gusedu.model.Persona;
import com.gusedu.model.Terapia;
import com.gusedu.model.TipoCliente;
import com.gusedu.model.Usuario;
import com.gusedu.util.StaticUtil;

@Service
public class PersonaServiceImpl implements PersonaService {

	@PersistenceContext
	EntityManager em;

	@Autowired
	ClienteService clienteService;

	@Transactional
	public boolean savePersona(Persona persona) {
		boolean resultado = false;
		try {
			em.persist(persona);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@SuppressWarnings("deprecation")
	@Transactional
	public boolean updatePersona(Persona persona) {
		boolean resultado = false;
		try {
			if(persona.getFechaNacimiento()!=null){
				persona.setSignoZodiacal(StaticUtil.signoZodiacal(persona.getFechaNacimiento().getMonth(), persona.getFechaNacimiento().getDate()));
			}
			em.merge(persona);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public boolean deletePersona(Persona persona) {
		boolean resultado = false;
		try {
			em.remove(em.getReference(Persona.class, persona.getIdPersona()));
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Persona> getPersonas() {
		List<Persona> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT p FROM Persona p");
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@Transactional
	public Persona getPersonaById(Integer id) {
		return em.find(Persona.class, id);
	}

	@SuppressWarnings("deprecation")
	@Transactional
	public boolean registroPaciente(Persona persona) {
		boolean resultado = false;
		try {
			if(persona.getFechaNacimiento()!=null){
				persona.setSignoZodiacal(StaticUtil.signoZodiacal(persona.getFechaNacimiento().getMonth(), persona.getFechaNacimiento().getDate()));
			}
	
			em.persist(persona);

			Cliente cliente = new Cliente();
			TipoCliente tipoCliente = new TipoCliente();
			Usuario usuario = new Usuario();
			String username = persona.getNombres().trim().substring(0, 1);
			username += persona.getApellidoPaterno().trim().replaceAll(" ", "");
			username += persona.getApellidoMaterno().trim().substring(0, 1);
			username = username.toLowerCase();
			int mes = persona.getFechaNacimiento().getMonth() + 1;
			int dia = persona.getFechaNacimiento().getDate();
			String mesPassword;
			String diaPassword;
			if (mes < 10) {
				mesPassword = "0" + mes;
			} else {
				mesPassword = mes + "";
			}

			if (dia < 10) {
				diaPassword = "0" + dia;
			} else {
				diaPassword = dia + "";
			}
			String password = diaPassword + mesPassword
					+ (persona.getFechaNacimiento().getYear() + 1900);

			usuario.setUsuario(username);
			usuario.setPassword(password);

			tipoCliente.setIdTipoCliente(1);

			cliente.setCliPersona(persona);
			cliente.setCliTipoCliente(tipoCliente);

			System.out.println(username);
			System.out.println(password);

			em.persist(cliente);

			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Terapia> terapiasPorPersona(Persona persona) {
		List<Terapia> result = new ArrayList<>();
		try {
			Query q = em
					.createQuery("SELECT t FROM Terapia t WHERE t.terVisita.visCliente.cliPersona=:persona");
			q.setParameter("persona", persona);
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@Override
	public Cliente buscarPorDni(String dni) {
		Cliente result = null;
		try {
			Integer intDni = Integer.parseInt(dni);

			Query q = em
					.createQuery("SELECT c FROM Cliente c WHERE c.cliPersona.dni=:dni");
			q.setParameter("dni", intDni);
			result = (Cliente) q.getSingleResult();
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			result = null;
		}
		return result;
	}	

}
