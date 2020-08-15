package br.com.gerentedocumento.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.gerentedocumento.domain.ModalidadeLicitacao;
import br.com.gerentedocumento.util.HibernateUtil;

public class ModalidadeLicitacaoDAO {

	public void salvar(ModalidadeLicitacao modalidade){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.save(modalidade);
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
	public List<ModalidadeLicitacao> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<ModalidadeLicitacao> listaModalidades = null;
		try{
			Query consulta = sessao.getNamedQuery("ModalidadeLicitacao.listar");
			listaModalidades = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return listaModalidades;
	}
	
	@SuppressWarnings("unchecked")
	public List<ModalidadeLicitacao> buscarPorStatus(String status){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<ModalidadeLicitacao> listaModalidades = null;
		try{
			Query consulta = sessao.getNamedQuery("ModalidadeLicitacao.buscarPorStatus");
			consulta.setString("status", status);
			listaModalidades = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return listaModalidades;
	}
	
	public ModalidadeLicitacao buscarPorCodigo(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		ModalidadeLicitacao modalidade = null;
		try{
			Query consulta = sessao.getNamedQuery("ModalidadeLicitacao.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			modalidade = (ModalidadeLicitacao)consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return modalidade;
	}
	
	public void editar(ModalidadeLicitacao modalidade){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.update(modalidade);
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
	
	public void excluir(ModalidadeLicitacao modalidade){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(modalidade);
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
