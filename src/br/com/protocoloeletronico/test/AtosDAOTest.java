package br.com.protocoloeletronico.test;

import org.junit.Ignore;
import org.junit.Test;

import br.com.protocoloeletronico.dao.AtosDAO;
import br.com.protocoloeletronico.domain.Atos;

public class AtosDAOTest {

	@Test
	@Ignore
	public void excluir(){
		AtosDAO adao = new AtosDAO();
		Atos ato = adao.buscarPorCodigo(12L);
		adao.excluir(ato);
	}
	
	@Test
	public void editar(){
		AtosDAO adao = new AtosDAO();
		Atos ato = adao.buscarPorCodigo(12L);
		ato.setDescricao("Teste");
		adao.editar(ato);
	}
}
