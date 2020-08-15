package br.com.gerentedocumento.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.gerentedocumento.dao.NotaEmpenhoDAO;
import br.com.gerentedocumento.domain.NotaEmpenho;

public class NotaEmpenhoDAOTest {

	@Test
	@Ignore
	public void listarPorDocumento(){
		NotaEmpenhoDAO nedao = new NotaEmpenhoDAO();
		List<NotaEmpenho> notas = nedao.buscarPorDocumento(131L);
		for(NotaEmpenho nota : notas){
			System.out.println(nota);
		}
	}
	
}
