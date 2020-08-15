package br.com.gerentedocumento.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.gerentedocumento.domain.Checklist;
import br.com.gerentedocumento.util.HibernateUtil;

public class ChecklistDAO {

	public void salvar(Checklist checklist){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.save(checklist);
			transacao.commit();
		}catch(RuntimeException ex){
			if (transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Checklist> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Checklist> listaChecklist = null;
		try{
			Query consulta = sessao.getNamedQuery("Checklist.listar");
			listaChecklist = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return listaChecklist;
	}
	
	public Checklist buscarPorCodigo(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Checklist checklist = null;
		try{
			Query consulta = sessao.getNamedQuery("Checklist.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			checklist = (Checklist) consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return checklist;
	}
	
	public Checklist buscarPorParecerAto(Long codigo, Long ato){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Checklist checklist = null;
		try{
			Query consulta = sessao.getNamedQuery("Checklist.buscarPorParecerAto");
			consulta.setLong("codigo", codigo);
			consulta.setLong("atosCodigo", ato);
						
			if (consulta.list().size() > 0){
				checklist = (Checklist) consulta.list().get(0);
			}
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return checklist;
	}
	
	@SuppressWarnings("unchecked")
	public List<Checklist> buscarPorParecer(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Checklist> listaChecklist = null;
		try{
			Query consulta = sessao.getNamedQuery("Checklist.buscarPorParecer");
			consulta.setLong("codigo", codigo);
			listaChecklist = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return listaChecklist;
	}
	
	@SuppressWarnings("unchecked")
	public List<Checklist> buscarPorParecer(Long codigo, String status){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Checklist> listaChecklist = null;
		try{
			Query consulta = sessao.getNamedQuery("Checklist.buscarPorParecerStatus");
			consulta.setLong("codigo", codigo);
			consulta.setString("status", status);
			listaChecklist = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return listaChecklist;
	}
	
	public void editar(Checklist checklist){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.update(checklist);
			transacao.commit();
		}catch(RuntimeException ex){
			if (transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
	}
	
	public void excluir(Checklist checklist){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(checklist);
			transacao.commit();
		}catch(RuntimeException ex){
			if (transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
	}
}
