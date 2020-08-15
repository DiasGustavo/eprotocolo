package br.com.gerentedocumento.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.gerentedocumento.domain.TipoLicitacao;
import br.com.gerentedocumento.util.HibernateUtil;

public class TipoLicitacaoDAO {

	public void salvar(TipoLicitacao tipo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.save(tipo);
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
	public List<TipoLicitacao> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<TipoLicitacao> listaTipos = null;
		try{
			Query consulta = sessao.getNamedQuery("TipoLicitacao.listar");
			listaTipos = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return listaTipos;
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoLicitacao> buscarPorStatus(String status){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<TipoLicitacao> listaTipos = null;
		try{
			Query consulta = sessao.getNamedQuery("TipoLicitacao.buscarPorStatus");
			consulta.setString("status", status);
			listaTipos = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return listaTipos;
	}
	
	public TipoLicitacao buscarPorCodigo(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		TipoLicitacao tipo = null;
		try{
			Query consulta = sessao.getNamedQuery("TipoLicitacao.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			tipo = (TipoLicitacao)consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return tipo;
	}
	
	public void editar(TipoLicitacao tipo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.update(tipo);
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
	
	public void excluir(TipoLicitacao tipo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(tipo);
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
