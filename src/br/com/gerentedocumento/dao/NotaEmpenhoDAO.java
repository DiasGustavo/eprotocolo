package br.com.gerentedocumento.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.gerentedocumento.domain.NotaEmpenho;
import br.com.gerentedocumento.util.HibernateUtil;

public class NotaEmpenhoDAO {

	public void salvar(NotaEmpenho nota){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.save(nota);
			transacao.commit();
		}catch(RuntimeException ex){
			if (transacao != null){
				transacao.rollback();
			}
		}finally{
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<NotaEmpenho> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<NotaEmpenho> listaNotas = null;
		
		try{
			Query consulta = sessao.getNamedQuery("NotaEmpenho.listar");
			listaNotas = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		
		return listaNotas;
	}
	
	public NotaEmpenho buscarPorCodigo(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		NotaEmpenho nota = null;
		
		try{
			Query consulta = sessao.getNamedQuery("NotaEmpenho.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			nota = (NotaEmpenho)consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return nota;
	}
	
	@SuppressWarnings("unchecked")
	public List<NotaEmpenho> buscarPorDocumento(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<NotaEmpenho> notas = null;
		
		try{
			Query consulta = sessao.getNamedQuery("NotaEmpenho.buscarPorDocumento");
			consulta.setLong("documento", codigo);
			notas = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return notas;
	}	
	@SuppressWarnings("unchecked")
	public List<NotaEmpenho> buscarPorFuncionario(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<NotaEmpenho> notas = null;
		
		try{
			Query consulta = sessao.getNamedQuery("NotaEmpenho.buscarPorFuncionario");
			consulta.setLong("funcionario", codigo);
			notas = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return notas;
	}	
	
	
	@SuppressWarnings("unchecked")
	public List<NotaEmpenho> buscarPorResponsavel(String responsavel){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<NotaEmpenho> notas = null;
		
		try{
			Query consulta = sessao.getNamedQuery("NotaEmpenho.buscarPorResponsavel");
			consulta.setString("responsavel", responsavel);
			notas = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return notas;
	}	
	
	public void editar(NotaEmpenho nota){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.update(nota);
			transacao.commit();
		}catch(RuntimeException ex){
			if (transacao != null){
				transacao.rollback();
			}
		}finally{
			sessao.close();
		}
	}
	
	public void excluir (NotaEmpenho nota){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(nota);
			transacao.commit();
		}catch(RuntimeException ex){
			if (transacao != null){
				transacao.rollback();
			}
		}finally{
			sessao.close();
		}
	}
}
