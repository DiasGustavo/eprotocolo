package br.com.protocoloeletronico.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.protocoloeletronico.domain.Pessoa;
import br.com.protocoloeletronico.util.HibernateUtil;

public class PessoaDAO {

	public void salvar(Pessoa pessoa){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.save(pessoa);
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
	public List<Pessoa> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Pessoa> listaPessoas = null;
		try{
			Query consulta = sessao.getNamedQuery("Pessoa.listar");
			listaPessoas = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return listaPessoas;
	}
	
	public Pessoa buscarPorCodigo(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Pessoa pessoa = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Pessoa.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			pessoa = (Pessoa)consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return pessoa;
	}
	
	public void editar(Pessoa pessoa){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.update(pessoa);
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
	
	public void excluir(Pessoa pessoa){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(pessoa);
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
