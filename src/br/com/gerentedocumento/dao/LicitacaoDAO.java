package br.com.gerentedocumento.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.gerentedocumento.domain.Licitacao;
import br.com.gerentedocumento.util.HibernateUtil;

public class LicitacaoDAO {

	public void salvar(Licitacao licitacao){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.save(licitacao);
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
	public List<Licitacao> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Licitacao> licitacoes = null;
		try{
			Query consulta = sessao.getNamedQuery("Licitacao.listar");
			licitacoes = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return licitacoes;
	}
	
	public Licitacao buscarPorCodigo(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Licitacao licitacao = null;
		try{
			Query consulta = sessao.getNamedQuery("Licitacao.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			licitacao = (Licitacao) consulta.uniqueResult();
		}catch(RuntimeException ex){
			
		}finally{
			sessao.close();
		}
		return licitacao;
	}
	
	public void editar (Licitacao licitacao){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.update(licitacao);
			transacao.commit();
		}catch(RuntimeException ex){
			if(transacao != null){
				transacao.rollback();
			}
		}finally{
			sessao.close();
		}
	}
	
	public void excluir(Licitacao licitacao){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(licitacao);
			transacao.commit();
		}catch(RuntimeException ex){
			if(transacao != null){
				transacao.rollback();
			}
		}finally{
			sessao.close();
		}
	}
}
