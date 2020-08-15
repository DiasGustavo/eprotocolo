package br.com.gerentedocumento.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.gerentedocumento.domain.Documento;
import br.com.gerentedocumento.util.HibernateUtil;

public class DocumentoDAO {

	public Long salvar(Documento documento){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		Long codigo = null;
		try{
			transacao = sessao.beginTransaction();
			codigo = (Long)sessao.save(documento);
			transacao.commit();
		}catch(RuntimeException ex){
			if (transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
		return codigo;
	}
	
	@SuppressWarnings("unchecked")
	public List<Documento> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Documento> listaDocumentos = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Documento.listar");
			listaDocumentos = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return listaDocumentos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> listarPorMes(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Object[]> listaDocumentos = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Documento.listarPorMes");
			listaDocumentos = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return listaDocumentos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Documento> listarPorResponsavel(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Documento> listaDocumentos = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Documento.listarPorResponsavel");
			consulta.setLong("responsavel", codigo);
			listaDocumentos = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return listaDocumentos;
	}
	
	public Documento buscarPorCodigo(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Documento documento = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Documento.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			documento = (Documento)consulta.uniqueResult();
		}catch (RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return documento;
	}
	
	public Documento buscarPorProcessoSecretaria (Long codigo, String processo, Long secretaria){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Documento documento = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Documento.buscarPorProcessoSecretaria");
			consulta.setLong("codigo", codigo);
			consulta.setString("processo", processo);
			consulta.setLong("secretaria", secretaria);
			documento = (Documento)consulta.uniqueResult();
		}catch (RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return documento;
	}
	
	public void editar (Documento documento){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.update(documento);
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
	
	public void excluir (Documento documento){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(documento);
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
