package br.com.gerentedocumento.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.gerentedocumento.domain.ChecklistNota;
import br.com.gerentedocumento.util.HibernateUtil;

public class ChecklistNotaDAO {
	
	public void salvar(ChecklistNota checklist){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.save(checklist);
			transacao.commit();
		}catch(RuntimeException ex){
			if(transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ChecklistNota> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<ChecklistNota> listaChecklist = null;
		try{
			Query consulta = sessao.getNamedQuery("ChecklistNota.listar");
			listaChecklist = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return listaChecklist;
	}
	
	public ChecklistNota buscarPorCodigo(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		ChecklistNota checklist = null;
		try{
			Query consulta = sessao.getNamedQuery("ChecklistNota.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			checklist = (ChecklistNota) consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return checklist;
	}
	
	public ChecklistNota buscarPorNotaAto (Long codigo, Long ato){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		ChecklistNota checklist = null;
		try{
			Query consulta = sessao.getNamedQuery("ChecklistNota.buscarPorNotaAto");
			consulta.setLong("codigo", codigo);
			consulta.setLong("atosCodigo", ato);
			
			if(consulta.list().size() >0){
				checklist = (ChecklistNota) consulta.list().get(0);
			}
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return checklist;
	}
	
	@SuppressWarnings("unchecked")
	public List<ChecklistNota> buscarPorNota (Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<ChecklistNota> listaChecklist = null;
		try{
			Query consulta = sessao.getNamedQuery("ChecklistNota.buscarPorNota");
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
	public List<ChecklistNota> buscarPorNotaTecnica(Long codigo, String status){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<ChecklistNota> listaChecklist = null;
		try{
			Query consulta = sessao.getNamedQuery("ChecklistNota.buscarPorNotaStatus");
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
	
	public void editar(ChecklistNota checklist){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.update(checklist);
			transacao.commit();
		}catch(RuntimeException ex){
			if(transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
	}
	
	public void excluir(ChecklistNota checklist){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(checklist);
			transacao.commit();
		}catch(RuntimeException ex){
			if(transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
	}
}
