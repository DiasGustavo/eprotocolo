package br.com.gerentedocumento.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.gerentedocumento.domain.Funcionario;
import br.com.gerentedocumento.util.HibernateUtil;

public class FuncionarioDAO {

	public Long salvar(Funcionario funcionario){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		Long codigo = null;
		
		try{
			transacao = sessao.beginTransaction();
			codigo = (Long) sessao.save(funcionario);
			transacao.commit();
			
			
		}catch (RuntimeException ex){
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		}finally {
			sessao.close();
		}
		return codigo;
	}
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Funcionario> listaFuncionarios = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Funcionario.listar");
			listaFuncionarios = consulta.list();
		}catch (RuntimeException ex){
			throw ex;
		}finally {
			sessao.close();
		}
		return listaFuncionarios;
	}
	
	public Funcionario buscarPorCodigo(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Funcionario funcionario = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Funcionario.buscarPorCodigo");
			consulta.setLong("codigo",codigo);
			funcionario = (Funcionario) consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return funcionario;
	}
	
	public void editar(Funcionario funcionario){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.update(funcionario);
			transacao.commit();
		}catch (RuntimeException ex){
			if (transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}	
	}
	
	public void excluir (Funcionario funcionario){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(funcionario);
			transacao.commit();
		}catch (RuntimeException ex){
			if (transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
	}
	
	public Funcionario autenticar (String login, String senha){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Funcionario funcionario = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Funcionario.autenticar");
			consulta.setString("login",login);
			consulta.setString("senha", senha);
			
			funcionario = (Funcionario) consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return funcionario;
	}
}
