package br.com.protocoloeletronico.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import br.com.protocoloeletronico.domain.TipoProcesso;
import br.com.protocoloeletronico.util.HibernateUtil;

public class TipoProcessoDAO {

	public void salvar(TipoProcesso tipo){
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
	public List<TipoProcesso> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<TipoProcesso> listaTipos = null;
		try{
			Query consulta = sessao.getNamedQuery("TipoProcesso.listar");
			listaTipos = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return listaTipos;
	}
	
	public TipoProcesso buscarPorCodigo(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		TipoProcesso tipo = null;
		try{
			Query consulta = sessao.getNamedQuery("TipoProcesso.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			tipo = (TipoProcesso)consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return tipo;
	}
	
	public void editar(TipoProcesso tipo){
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
	
	public void excluir(TipoProcesso tipo){
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
