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
import com.gusedu.model.EnfermedadTerapia;
import com.gusedu.model.EnfermedadVisita;
import com.gusedu.model.Par;
import com.gusedu.model.SintomaTerapia;
import com.gusedu.model.SintomaVisita;
import com.gusedu.model.Terapia;
import com.gusedu.model.TerapiaPar;
import com.gusedu.model.TipoTerapia;
import com.gusedu.model.Visita;

@Service
public class TerapiaServiceImpl implements TerapiaService {

	@PersistenceContext
	EntityManager em;

	@Transactional
	public boolean saveTerapia(Terapia terapia) {
		boolean resultado = false;
		try {
			em.persist(terapia);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public boolean updateTerapia(Terapia terapia) {
		boolean resultado = false;
		try {
			em.merge(terapia);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public boolean deleteTerapia(Terapia terapia) {
		boolean resultado = false;
		try {
			em.remove(em.getReference(Terapia.class, terapia.getIdTerapia()));
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Terapia> terapiasPorVisita(Visita visita) {
		List<Terapia> result = new ArrayList<>();
		try {
			Query q = em
					.createQuery("SELECT t FROM Terapia t WHERE t.terVisita=:visita order by t.fechaRealizada desc");
			q.setParameter("visita", visita);
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<TipoTerapia> getTipoTerapias() {
		List<TipoTerapia> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT t FROM TipoTerapia t");			
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@Transactional
	public TipoTerapia tteById(Integer idTipoTerapia) {
		return em.find(TipoTerapia.class, idTipoTerapia);
	}

	@Transactional
	public Terapia terapiaById(Integer idTerapia) {
		return em.find(Terapia.class, idTerapia);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<EnfermedadTerapia> getEnfermedadesByTerapia(Terapia terapia) {
		List<EnfermedadTerapia> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT et FROM EnfermedadTerapia et WHERE et.extTerapia=:terapia");
			q.setParameter("terapia", terapia);
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<SintomaTerapia> getSintomasByTerapia(Terapia terapia) {
		List<SintomaTerapia> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT st FROM SintomaTerapia st WHERE st.sxtTerapia=:terapia");
			q.setParameter("terapia", terapia);
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@Transactional
	public boolean saveTerapiaPar(TerapiaPar terapiaPar) {
		boolean resultado = false;
		try {
			em.persist(terapiaPar);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Par> getTerapiaParesFromTerapia(Terapia terapia) {
		List<Par> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT tp.txpPar FROM TerapiaPar tp WHERE tp.txpTerapia=:terapia");
			q.setParameter("terapia", terapia);
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@Override
	public TerapiaPar TerapiaParByParAndTerapia(Terapia terapia, Par par) {
		TerapiaPar result = null;
		try {
			Query q = em.createQuery("SELECT tp FROM TerapiaPar tp WHERE tp.txpTerapia=:terapia AND tp.txpPar=:par");
			q.setParameter("terapia", terapia);
			q.setParameter("par", par);
			result = (TerapiaPar) q.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Terapia> terapiasPorCliente(Cliente cliente) {
		List<Terapia> result = new ArrayList<>();
		try {
			Query q = em
					.createQuery("SELECT t FROM Terapia t WHERE t.terVisita.visCliente=:cliente order by t.fechaRealizada desc");
			q.setParameter("cliente", cliente);
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@Transactional
	public boolean saveSintomaVisita(SintomaVisita sintomavista) {
		boolean resultado = false;
		try {
			em.persist(sintomavista);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public boolean saveEnfermedadVisita(EnfermedadVisita enfermedadvista) {
		boolean resultado = false;
		try {
			em.persist(enfermedadvista);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SintomaVisita> getAllSintomaxVisita(Visita vis) {
		List<SintomaVisita> result = new ArrayList<>();
		try {
			Query q = em
					.createQuery("SELECT t FROM SintomaVisita t WHERE t.sxvVisita=:visita");
			q.setParameter("visita", vis);
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR de getAllSintomaxVisita: " + e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EnfermedadVisita> getAllEnfermedadxVisita(Visita vis) {
		List<EnfermedadVisita> result = new ArrayList<>();
		try {
			Query q = em
					.createQuery("SELECT t FROM EnfermedadVisita t WHERE t.exvVisita=:visita");
			q.setParameter("visita", vis);
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TerapiaPar> getAllTerapiaParbyTerapia(Terapia terapia) {
		List<TerapiaPar> result = new ArrayList<>();
		try {
			Query q = em
					.createQuery("SELECT t FROM TerapiaPar t WHERE t.txpTerapia=:terapia");
			q.setParameter("terapia", terapia);
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TerapiaPar> getAllTerapiaParbyVisita(Visita visita) {
		List<TerapiaPar> result = new ArrayList<>();
		try {
			Terapia terapia= new Terapia();
			terapia.setIdTerapia(0);
			if(terapiasPorVisita(visita).size()!=0)
			{
				terapia=terapiasPorVisita(visita).get(0);
			}
			Query q = em
					.createQuery("SELECT t FROM TerapiaPar t WHERE t.txpTerapia=:terapia ORDER BY t.txpTerapia.fechaRealizada DESC LIMIT 1");
			q.setParameter("terapia", terapia);
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@Transactional
	public String getAllParbyAllTerapia(Terapia terapia, Par par) {
		String resul="";
		
		try
		{
			Query q = em.createQuery("select t from TerapiaPar  t where t.txpPar=:par and t.txpTerapia=:terapia");
			q.setParameter("par", par);
			q.setParameter("terapia", terapia);
			TerapiaPar terap=  (TerapiaPar) q.getSingleResult();
			if(terap!=null)
			{
				if(terap.getTxpActivo())
				{
					resul="Si";
				}else
				{
					resul="No";
				}
			}
		}
		catch(NoResultException e)
		{
			System.out.println("ERROR: " + e.getMessage());
		}
        return resul;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Terapia> getAllTerapiabyCliente(Cliente cli) {
		List<Terapia> result = new ArrayList<>();
		try {
			Query q = em
					.createQuery("SELECT t FROM Terapia t WHERE t.terVisita.visCliente=:cliente order by t.fechaRealizada asc");
			q.setParameter("cliente", cli);
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@Override
	public Terapia lastTerapia(Cliente cliente) {
		Terapia result = null;
		try {
			Query q = em.createQuery("SELECT  t FROM Terapia t WHERE t.terVisita.visCliente=:cliente ORDER BY t.fechaRealizada DESC");
			q.setParameter("cliente", cliente);
			q.setMaxResults(1);
			result = (Terapia) q.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("ERROR de VisitaService: " + e.getMessage());
		}
		return result;
	}

	@Transactional(noRollbackFor = Exception.class)
	public boolean updateTerapiaPar(TerapiaPar terapiapar) {
		boolean resultado = false;
		try {
			em.persist(terapiapar);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TerapiaPar> getAllParbyCliente(Cliente cliente) {
		List<TerapiaPar> result = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT t FROM TerapiaPar t WHERE t.txpTerapia.terVisita.visCliente=:cliente");
			q.setParameter("cliente", cliente);
		 
			result = q.getResultList();
		} catch (NoResultException e) {
			System.out.println("ERROR : " + e.getMessage());
		}
		return result;
	}

}
