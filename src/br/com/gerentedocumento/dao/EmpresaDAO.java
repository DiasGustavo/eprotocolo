package br.com.gerentedocumento.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.gerentedocumento.domain.Empresa;
import br.com.gerentedocumento.util.HibernateUtil;

public class EmpresaDAO {

	public void salvar(Empresa empresa){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.save(empresa);
			transacao.commit();
		}catch(RuntimeException ex){
			if (transacao != null){
				transacao.rollback();
			}
			throw ex;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Empresa> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Empresa> listaEmpresas = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Empresa.listar");
			listaEmpresas = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}
		return listaEmpresas;
	}
	
	public Empresa buscarPorCodigo (Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Empresa empresa = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Empresa.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			empresa = (Empresa)consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}
		return empresa;
	}
	
		
	public void editar(Empresa empresa){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.update(empresa);
			transacao.commit();
		}catch(RuntimeException ex){
			if (transacao != null){
				transacao.rollback();
			}
			throw ex;
		}
	}
	
	public void excluir (Empresa empresa){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(empresa);
			transacao.commit();
		}catch(RuntimeException ex){
			if (transacao != null){
				transacao.rollback();
			}
			throw ex;
		}
	}
}
