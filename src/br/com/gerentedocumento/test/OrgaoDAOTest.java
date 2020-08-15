package br.com.gerentedocumento.test;

import org.junit.Test;

import br.com.gerentedocumento.dao.EnderecoDAO;
import br.com.gerentedocumento.dao.OrgaoDAO;
import br.com.gerentedocumento.domain.Endereco;
import br.com.gerentedocumento.domain.Orgao;

public class OrgaoDAOTest {

	@Test
	public void salvar(){
		Orgao orgao = new Orgao();
		orgao.setNome("Prefeitura Municipal Padrão");
		orgao.setCnpj("69.160.355/0001-44");
		orgao.setRepositorio("c:\\");
		
		EnderecoDAO edao = new EnderecoDAO();
		Endereco endereco = edao.buscarPorCodigo(1L);
		
		orgao.setEndereco(endereco);
		
		OrgaoDAO odao = new OrgaoDAO();
		odao.salvar(orgao);
	}
}
