package br.com.gerentedocumento.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.gerentedocumento.dao.FuncionarioDAO;
import br.com.gerentedocumento.dao.OrgaoDAO;
import br.com.gerentedocumento.domain.Funcionario;
import br.com.gerentedocumento.domain.Orgao;

public class FuncionarioDAOTest {

	@Test
	
	public void salvar (){
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("administrador");
		funcionario.setMatricula("20545");
		funcionario.setEmail("administrador@gmail.com");
		funcionario.setTelefone("(83)999999999");
		funcionario.setLogin("administrador");
		funcionario.setSenha("200820e3227815ed1756a6b531e7e0d2");
		
		OrgaoDAO odao = new OrgaoDAO();
		Orgao orgao = odao.buscarPorCodigo(1L);
		
		funcionario.setOrgao(orgao);
		funcionario.setFuncao("administrador");
		
		FuncionarioDAO fdao = new FuncionarioDAO();
		fdao.salvar(funcionario);
	}
	
	@Test
	@Ignore
	public void listar(){
		FuncionarioDAO fdao = new FuncionarioDAO();
		List<Funcionario> funcionarios = fdao.listar();
		
		for (Funcionario funcionario : funcionarios){
			System.out.println(funcionario);
		}
	}
	
	@Test
	@Ignore
	public void buscarPorCódigo (){
		FuncionarioDAO fdao = new FuncionarioDAO();
		Funcionario funcionario = fdao.buscarPorCodigo(1L);
		
		System.out.println(funcionario);
	}
	
	@Test
	@Ignore
	public void editar(){
		FuncionarioDAO fdao = new FuncionarioDAO();
		Funcionario funcionario = fdao.buscarPorCodigo(1L);
		funcionario.setSenha("200820e3227815ed1756a6b531e7e0d2");
		
		fdao.editar(funcionario);
	}
	
	@Test
	@Ignore
	public void excluir(){
		FuncionarioDAO fdao = new FuncionarioDAO();
		Funcionario funcionario = fdao.buscarPorCodigo(1L);
		
		fdao.excluir(funcionario);
	}
	@Test
	@Ignore
	public void autenticar(){
		FuncionarioDAO fdao = new FuncionarioDAO();
		Funcionario funcionario = fdao.autenticar("gustavo", "qwe123");
		
		System.out.println(funcionario);
	}
}
