package br.com.gerentedocumento.dao;

import java.util.List;

import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.gerentedocumento.domain.NotaTecnica;
import br.com.gerentedocumento.util.HibernateUtil;

public class NotaTecnicaDAO {

	public Long Salvar(NotaTecnica notaTecnica){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		Long codigo = null;
		try{
			transacao = sessao.beginTransaction();
			codigo = (Long) sessao.save(notaTecnica);
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
	public List<NotaTecnica> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<NotaTecnica> listaNotas = null;
		try{
			Query consulta = sessao.getNamedQuery("NotaTecnica.listar");
			listaNotas = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return listaNotas;
	}
	
	public NotaTecnica buscarPorCodigo(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		NotaTecnica nota = null;
		try{
			Query consulta = sessao.getNamedQuery("NotaTecnica.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			nota = (NotaTecnica) consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return nota;
	}
	
	@SuppressWarnings("unchecked")
	public List<NotaTecnica> buscarPorResponsavel(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<NotaTecnica> listaNotas = null;
		try{
			Query consulta = sessao.getNamedQuery("NotaTecnica.buscarPorResponsavel");
			consulta.setLong("funcionario", codigo);
			listaNotas = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return listaNotas;
	}
	
	public void editar(NotaTecnica notaTecnica){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.update(notaTecnica);
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
	
	public void excluir(NotaTecnica notaTecnica){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(notaTecnica);
			transacao.commit();
		}catch(RuntimeException ex){
			if(transacao != null){
				transacao.rollback();
			}
			throw ex;
		}
	}
}
