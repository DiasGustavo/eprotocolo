package br.com.gerentedocumento.test;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.gerentedocumento.dao.CadastraDocumentoDAO;
import br.com.gerentedocumento.dao.DocumentoDAO;
import br.com.gerentedocumento.dao.FuncionarioDAO;
import br.com.gerentedocumento.domain.CadastraDocumento;
import br.com.gerentedocumento.domain.Documento;
import br.com.gerentedocumento.domain.Funcionario;

public class CadastraDocumentoDAOTest {
	
	@Test
	
	public void salvar(){
		FuncionarioDAO fdao = new FuncionarioDAO();
		Funcionario funcionario = fdao.buscarPorCodigo(2L);
		
		DocumentoDAO ddao = new DocumentoDAO();
		Documento documento = ddao.buscarPorCodigo(2L);
		
		CadastraDocumentoDAO cddao = new CadastraDocumentoDAO();
		CadastraDocumento cadastraDocumento = new CadastraDocumento();
		
		cadastraDocumento.setCodFuncionario(funcionario.getId());
		cadastraDocumento.setCodDocumento(documento.getId());
		cadastraDocumento.setDataCadastro(new Date());
		
		cddao.salvar(cadastraDocumento);
	}

	@Test
	@Ignore
	public void listar(){
		CadastraDocumentoDAO cddao = new CadastraDocumentoDAO();
		List<CadastraDocumento> documentos = cddao.listar();
		
		for(CadastraDocumento doc : documentos){
			System.out.println(doc);
		}
	}
	@Test
	@Ignore
	public void buscarPorCodigo(){
		CadastraDocumentoDAO cddao = new CadastraDocumentoDAO();
		CadastraDocumento documento = cddao.buscarPorCodigoDocumento(1L);
		
		System.out.println(documento);
	}
}
