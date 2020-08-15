package br.com.gerentedocumento.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.gerentedocumento.domain.ControleRegistro;
import br.com.gerentedocumento.util.FacesUtil;
import br.com.gerentedocumento.util.HibernateUtil;

public class ControleRegistroDAO {

	public void salvar(ControleRegistro controle){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.save(controle);
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
	public List<ControleRegistro> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<ControleRegistro> registros = null;
		try{
			Query consulta = sessao.getNamedQuery("controleRegistro.listar");
			registros = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return registros;	
	}
	
	public ControleRegistro buscarPorCodigo(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		ControleRegistro controle = null;
		try{
			Query consulta = sessao.getNamedQuery("controleRegistro.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			controle = (ControleRegistro) consulta.uniqueResult();
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao carregar o registro " + ex.getMessage());
		}finally{
			sessao.close();
		}
		return controle;
	}
	
	public ControleRegistro buscarPorDescricao(String descricao){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		ControleRegistro controle = null;
		
		try{
			Query consulta = sessao.getNamedQuery("controleRegistro.buscarPorDescricao");
			consulta.setString("descricao", descricao);
			controle = (ControleRegistro)consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return controle;
	}
	
	public void editar(ControleRegistro controle){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.update(controle);
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
	
	public void excluir(ControleRegistro controle){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(controle);
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
