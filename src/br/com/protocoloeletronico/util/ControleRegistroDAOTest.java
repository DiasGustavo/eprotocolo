package br.com.protocoloeletronico.util;

import org.junit.Ignore;
import org.junit.Test;

import br.com.protocoloeletronico.dao.ControleRegistroDAO;
import br.com.protocoloeletronico.domain.ControleRegistro;

public class ControleRegistroDAOTest {

	@Test
	@Ignore
	public void salvar(){
		ControleRegistro controle = new ControleRegistro();
		ControleRegistroDAO cdao = new ControleRegistroDAO();
		
		controle.setDescricao("parecer");
		controle.setValor(723);
		cdao.salvar(controle);
	}
	@Ignore
	@Test
	public void buscarPorDescricao(){
		String descricao = "parecer";
		ControleRegistroDAO cdao = new ControleRegistroDAO();
		System.out.println(cdao.buscarPorDescricao(descricao));
	}
	
	@Test
	public void editar(){
		ControleRegistro controle = new ControleRegistro();
		ControleRegistroDAO cdao = new ControleRegistroDAO();
		
		controle = cdao.buscarPorDescricao("parecer");
		controle.setValor(724);
		
		cdao.editar(controle);
	}
}
