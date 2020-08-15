package br.com.gerentedocumento.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.gerentedocumento.domain.Orgao;
import br.com.gerentedocumento.util.HibernateUtil;

public class OrgaoDAO {

	public void salvar(Orgao orgao){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.save(orgao);
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
	public List<Orgao> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Orgao> listaOrgaos = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Orgao.listar");
			listaOrgaos = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return listaOrgaos;
	}
	
	public Orgao buscarPorRegistro(String registro, Date data){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Orgao orgao = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Orgao.buscarPorRegistro");
			consulta.setString("registro", registro);
			consulta.setDate("dataTeste", data);
			orgao = (Orgao)consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return orgao;
	}
	
	public Orgao buscarPorCodigo(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Orgao orgao = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Orgao.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			orgao = (Orgao) consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return orgao;
	}
	
	public void editar (Orgao orgao){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.update(orgao);
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
	
	public void excluir (Orgao orgao){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(orgao);
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
