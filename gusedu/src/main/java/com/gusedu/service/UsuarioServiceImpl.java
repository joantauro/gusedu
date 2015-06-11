package com.gusedu.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gusedu.model.TipoUsuario;
import com.gusedu.model.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	EntityManager em;

	
	
	@Transactional
	public boolean saveUsuario(Usuario usuario) {
		boolean resultado = false;
		try {
			usuario.setFechaCreacion(new Date());
			em.persist(usuario);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public boolean updateUsuario(Usuario usuario) {
		boolean resultado = false;
		try {
			em.merge(usuario);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public boolean deleteUsuario(Usuario usuario) {
		boolean resultado = false;
		try {
			em.remove(em.getReference(Usuario.class, usuario.getIdUsuario()));
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Usuario> getAll() {
		List<Usuario> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT u FROM Usuario u");
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@Transactional
	public Usuario getUsuarioeById(Integer idUsuario) {
		return em.find(Usuario.class, idUsuario);
	}

	 

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoUsuario> getAllTipoUsuarios() {
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
	public List<Usuario> getAllFinMembresia(int n) {
		List<Usuario> result = new ArrayList<>();
		List<Usuario> data = getAll();
		Date fecha = new Date();long diferencia ;
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
		for(int i=0;i<data.size();i++){
		 diferencia = ( data.get(i).getFechafinm().getTime() - fecha.getTime() )/MILLSECS_PER_DAY;
		 if(diferencia==n)
			{
				result.add(data.get(i));
			}
		}
		return result;
	}

	@Override
	public void updateUsuarioFinMembresia() {
		@SuppressWarnings("unused")
		List<Usuario> lista = getAllFinMembresia(2);
		Usuario usuario = new Usuario();
		usuario = getAllFinMembresia(2).get(0);
		usuario.setEmpresa("Joel");
		updateUsuario(usuario);
		/*for(int i=0;i<lista.size();i++)
		{
			System.out.println("Actualizo");
			usuario=lista.get(i);
			usuario.setEsActivo(false);
			try {
				updateUsuario(usuario);
			//em.merge(usuario);
			System.out.println("ID : " +usuario.getIdUsuario()+" Estado : "+usuario.getEsActivo());
			usuario = new Usuario();
				} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
 
				}
		}*/
 
	}

 
 

}
