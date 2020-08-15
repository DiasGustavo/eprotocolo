package br.com.gerentedocumento.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.gerentedocumento.dao.DocumentoDAO;
import br.com.gerentedocumento.dao.FuncionarioDAO;
import br.com.gerentedocumento.domain.Documento;
import br.com.gerentedocumento.domain.Funcionario;

public class DocumentoDAOTest {

	@Test
	@Ignore
	public void salvar(){
		Documento documento = new Documento();
		documento.setProcesso("28790");
		documento.setObjeto("aquisição de material");
		documento.setValor(new BigDecimal(10.0));
		documento.setDataEntrada(new Date());
		//documento.setSecretaria("Finanças");
		documento.setDataSaida(new Date());
		
		FuncionarioDAO fdao = new FuncionarioDAO();
		Funcionario funcionario = fdao.buscarPorCodigo(2L);
		
		documento.setResponsavel(funcionario);
		
		DocumentoDAO ddao = new DocumentoDAO();
		ddao.salvar(documento);		
	}
	
	@Test
	@Ignore
	public void listar(){
		DocumentoDAO ddao = new DocumentoDAO();
		List<Documento> listaDocumentos = ddao.listar();
		
		for(Documento documento : listaDocumentos){
			System.out.println(documento);
		}
	}
	
	@Test
	
	public void listarPorMes(){
		DocumentoDAO ddao = new DocumentoDAO();
		List<Object[]> listaDocumentos = ddao.listarPorMes();
		
		for(Object [] teste : listaDocumentos){
			System.out.println("processos: " + teste[0]);
			System.out.println("mês: " + teste[1]);
			System.out.println("ano: " + teste[2]);
			//System.out.println("teste: " + teste[1]);
		}
		/*
		for(Documento documento : listaDocumentos){
			System.out.println(documento);
		}*/
	}
	
	@Test
	@Ignore
	public void listarPorResponsavel(){
		DocumentoDAO ddao= new DocumentoDAO();
		List<Documento> listaDocumentos = ddao.listarPorResponsavel(1L);
		
		for(Documento documento : listaDocumentos){
			System.out.println(documento);
		}
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo(){
		DocumentoDAO ddao = new DocumentoDAO();
		Documento documento = ddao.buscarPorCodigo(1L);
		
		System.out.println(documento);
	}
	
	@Test
	@Ignore
	public void editar(){
		DocumentoDAO ddao = new DocumentoDAO();
		Documento documento = ddao.buscarPorCodigo(1L);
		
		documento.setObservacao("observação padrão");
		
		ddao.editar(documento);
	}
	
	@Test
	@Ignore
	public void excluir(){
		DocumentoDAO ddao = new DocumentoDAO();
		Documento documento = ddao.buscarPorCodigo(193L);
		
		ddao.excluir(documento);
	}
}
