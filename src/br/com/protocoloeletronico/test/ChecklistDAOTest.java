package br.com.protocoloeletronico.test;

import org.junit.Ignore;
import org.junit.Test;

import br.com.protocoloeletronico.dao.AtosDAO;
import br.com.protocoloeletronico.dao.ChecklistDAO;
import br.com.protocoloeletronico.dao.ParecerDAO;
import br.com.protocoloeletronico.domain.Atos;
import br.com.protocoloeletronico.domain.Checklist;
import br.com.protocoloeletronico.domain.Parecer;

public class ChecklistDAOTest {

	@Test
	@Ignore
	public void editar(){
		Checklist check = new Checklist();
		ChecklistDAO cdao = new ChecklistDAO();
		
		check = cdao.buscarPorCodigo(33L);
		check.setCateoria("obra");
		
		cdao.editar(check);
	}
	@Test
	public void salvar(){
		AtosDAO adao = new AtosDAO();
		Atos ato = new Atos();		
		ato = adao.buscarPorCodigo(12L);
		
		ParecerDAO pdao = new ParecerDAO();
		Parecer parecer = new Parecer();
		
		parecer = pdao.buscarPorCodigo(136L);
		
		
		Checklist checklist = new Checklist();
		checklist.setAtos(ato);
		checklist.setCateoria(ato.getCategoria());
		checklist.setStatus("I");
		checklist.setParecer(parecer);
		ChecklistDAO cdao = new ChecklistDAO();
		cdao.salvar(checklist);
	}
}
