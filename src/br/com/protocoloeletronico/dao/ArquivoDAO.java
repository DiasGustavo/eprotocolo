package br.com.protocoloeletronico.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.protocoloeletronico.domain.Arquivo;
import br.com.protocoloeletronico.util.HibernateUtil;

public class ArquivoDAO {

	public Long salvar(Arquivo arquivo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		Long codigo = null;
		try{
			transacao = sessao.beginTransaction();
			codigo = (Long) sessao.save(arquivo);
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
	public List<Arquivo> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Arquivo> listaArquivos = null;
		try{
			Query consulta = sessao.getNamedQuery("Arquivos.listar");
			listaArquivos = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return listaArquivos;
	}
	
	public Arquivo buscarPorCodigo(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Arquivo arquivo = null;
		try{
			Query consulta = sessao.getNamedQuery("Arquivo.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			arquivo = (Arquivo) consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return arquivo;
	}
	
	@SuppressWarnings("unchecked")
	public List<Arquivo> buscarPorCodigoProcesso(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Arquivo> arquivos = null;
		try{
			Query consulta = sessao.getNamedQuery("Arquivo.buscarPorCodigoProcesso");
			consulta.setLong("codigo", codigo);
			arquivos = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return arquivos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Arquivo> buscarListaSetPorCodigo(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		//Set<Arquivo> arquivos = null;
		List<Arquivo> listaArquivos = null;
		try{
			Query consulta = sessao.getNamedQuery("Arquivo.buscarArquivos");
			consulta.setLong("codigo", codigo);
			listaArquivos = consulta.list();
			/*arquivos = new HashSet<Arquivo>(listaArquivos);
			arquivos.addAll(listaArquivos);*/
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return listaArquivos;
	}
	
	@SuppressWarnings("unchecked")
	public Set<Arquivo> buscarListaArquivosPorCodigo(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Set<Arquivo> arquivos = null;
		List<Arquivo> listaArquivos = null;
		try{
			Query consulta = sessao.getNamedQuery("Arquivo.buscarArquivos");
			consulta.setLong("codigo", codigo);
			listaArquivos = consulta.list();
			arquivos = new HashSet<Arquivo>(listaArquivos);
			arquivos.addAll(listaArquivos);
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return arquivos;
	}
	
	public void editar(Arquivo arquivo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.update(arquivo);
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
	
	public void excluir(Arquivo arquivo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(arquivo);
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
