package br.com.gerentedocumento.test;

import org.junit.Test;

import br.com.gerentedocumento.dao.EnderecoDAO;
import br.com.gerentedocumento.domain.Endereco;

public class EnderecoDAOTest {

	@Test
	public void salvar(){
		Endereco endereco = new Endereco();
		endereco.setRua("Felizardo Leite");
		endereco.setNumero("30");
		endereco.setBairro("Centro");
		endereco.setCep("58700-030");
		endereco.setCidade("Patos");
		endereco.setEstado("Paraíba");
		
		EnderecoDAO edao = new EnderecoDAO();
		edao.salvar(endereco);
	}
}
