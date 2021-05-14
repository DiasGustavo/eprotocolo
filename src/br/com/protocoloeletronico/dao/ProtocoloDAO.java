package br.com.protocoloeletronico.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.protocoloeletronico.domain.Protocolo;
import br.com.protocoloeletronico.util.HibernateUtil;

public class ProtocoloDAO {

	public Long salvar(Protocolo protocolo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		Long codigo = null;
		try{
			transacao = sessao.beginTransaction();
			codigo = (Long) sessao.save(protocolo);
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
	public List<Protocolo> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Protocolo> listaProtocolos = null;
		try{
			Query consulta = sessao.getNamedQuery("Protocolo.listar");
			listaProtocolos = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return listaProtocolos;
	}
	
	public Protocolo buscarPorCodigo(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Protocolo protocolo = null;
		try{
			Query consulta = sessao.getNamedQuery("Protocolo.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			protocolo = (Protocolo) consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		
		return protocolo;
	}
	
	@SuppressWarnings("unchecked")
	public List<Protocolo> listarPorResponsavel(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Protocolo> listaDocumentos = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Protocolo.listarPorResponsavel");
			consulta.setLong("responsavel", codigo);
			listaDocumentos = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return listaDocumentos;
	}
	
	public void editar(Protocolo protocolo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.update(protocolo);
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
	
	public void excluir(Protocolo protocolo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(protocolo);
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
