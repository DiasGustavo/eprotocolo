package br.com.gerentedocumento.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.gerentedocumento.domain.CadastraDocumento;
import br.com.gerentedocumento.util.HibernateUtil;

public class CadastraDocumentoDAO {

	public void salvar(CadastraDocumento cadastraDocumento){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		
		try{
			transacao = sessao.beginTransaction();
			sessao.save(cadastraDocumento);
			transacao.commit();
		}catch(RuntimeException ex){
			if (transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<CadastraDocumento> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<CadastraDocumento> listaCadastra = null;
		
		try{
			Query consulta = sessao.getNamedQuery("CadastraDocumento.listar");
			listaCadastra = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return listaCadastra;
	}
	
	public CadastraDocumento buscarPorCodigoDocumento(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		CadastraDocumento cadastra = null;
		
		try{
			Query consulta = sessao.getNamedQuery("CadastraDocumento.buscarPorCodigoDocumento");
			consulta.setLong("codigo", codigo);
			cadastra = (CadastraDocumento)consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return cadastra;
	}
	
	public void editar(CadastraDocumento cadastraDocumento){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.update(cadastraDocumento);
			transacao.commit();
		}catch(RuntimeException ex){
			if (transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
	}
	
	public void excluir (CadastraDocumento cadastraDocumento){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(cadastraDocumento);
			transacao.commit();
		}catch(RuntimeException ex){
			if (transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
	}
}
