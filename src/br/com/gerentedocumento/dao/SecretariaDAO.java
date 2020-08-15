package br.com.gerentedocumento.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.gerentedocumento.domain.Secretaria;
import br.com.gerentedocumento.util.HibernateUtil;

public class SecretariaDAO {

	public void salvar(Secretaria secretaria){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.save(secretaria);
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
	public List<Secretaria> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Secretaria> listaSecretaria = null;
		try{
			Query consulta = sessao.getNamedQuery("Secretaria.listar");
			listaSecretaria = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return listaSecretaria;
	}
	
	public Secretaria buscarPorCodigo(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Secretaria secretaria = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Secretaria.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			secretaria = (Secretaria)consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return secretaria;
	}
	
	public void editar(Secretaria secretaria){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.update(secretaria);
			transacao.commit();
		}catch(RuntimeException ex){
			if(transacao != null){
				transacao.rollback();
			}
		}finally{
			sessao.close();
		}
	}
	
	public void excluir(Secretaria secretaria){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(secretaria);
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
