package br.com.gerentedocumento.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.gerentedocumento.domain.Parecer;
import br.com.gerentedocumento.util.HibernateUtil;

public class ParecerDAO {

	public Long salvar(Parecer parecer){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		Long codigo = null;
		try{
			transacao = sessao.beginTransaction();
			codigo = (Long)sessao.save(parecer);
			transacao.commit();
		}catch(RuntimeException ex){
			if(transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
		return codigo;
	}
	
	@SuppressWarnings("unchecked")
	public List<Parecer> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Parecer> listaPareceres = null;
		try{
			Query consulta = sessao.getNamedQuery("Parecer.listar");
			listaPareceres = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return listaPareceres;
	}
	
	public Parecer buscarPorCodigo (Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Parecer parecer = null;
		try{
			Query consulta = sessao.getNamedQuery("Parecer.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			parecer = (Parecer)consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return parecer;
	}
	
	@SuppressWarnings("unchecked")
	public List<Parecer> buscarPorPeriodo (Date inicio, Date fim){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Parecer> listaPareceres = null;
		try{
			Query consulta = sessao.getNamedQuery("Parecer.buscarPorPeriodo");
			consulta.setDate("inicio", inicio);
			consulta.setDate("fim", fim);
			listaPareceres = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return listaPareceres;
	}
	
	@SuppressWarnings("unchecked")
	public List<Parecer> buscarPorResponsavel(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Parecer> listaPareceres = null;
		try{
			Query consulta = sessao.getNamedQuery("Parecer.buscarPorResponsavel");
			consulta.setLong("funcionario", codigo);
			listaPareceres = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return listaPareceres;
	}
	
	public void editar(Parecer parecer){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.update(parecer);
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
	
	public void excluir(Parecer parecer){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(parecer);
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
