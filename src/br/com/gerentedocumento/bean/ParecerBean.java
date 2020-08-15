package br.com.gerentedocumento.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.gerentedocumento.dao.AtosDAO;
import br.com.gerentedocumento.dao.ChecklistDAO;
import br.com.gerentedocumento.dao.ControleRegistroDAO;
import br.com.gerentedocumento.dao.DocumentoDAO;
import br.com.gerentedocumento.dao.FuncionarioDAO;
import br.com.gerentedocumento.dao.ParecerDAO;
import br.com.gerentedocumento.dao.SecretariaDAO;
import br.com.gerentedocumento.domain.Atos;
import br.com.gerentedocumento.domain.Checklist;
import br.com.gerentedocumento.domain.ControleRegistro;
import br.com.gerentedocumento.domain.Documento;
import br.com.gerentedocumento.domain.Funcionario;
import br.com.gerentedocumento.domain.Parecer;
import br.com.gerentedocumento.domain.Secretaria;
import br.com.gerentedocumento.util.FacesUtil;
import br.com.gerentedocumento.util.GeraParecer;
import br.com.gerentedocumento.bean.AutenticacaoBean;
import br.com.gerentedocumento.constante.Status;

@ManagedBean
@ViewScoped
public class ParecerBean {

	private Parecer parecerCadastro;

	private List<Parecer> listaPareceres;
	private List<Parecer> listaPareceresFiltrados;
	private List<Funcionario> listaFuncionarios;
	private List<Atos> listaAtos;
	private List<Atos> listaAtosFiltrados;
	private List<Atos> listaAtosSelecionados;
	private List<Checklist> listaAtosNaoSelecionados;
	private List<Documento> listaDocumentos;
	private List<Checklist> listaChecklists;
	private List<Secretaria> listaSecretarias;

	// private CategoriaAto[] categorias;

	private String acao;
	private Long codigo;
	private Date dataCriacao;

	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;

	public Parecer getParecerCadastro() {
		if (parecerCadastro == null) {
			parecerCadastro = new Parecer();
			dataCriacao = new Date();
		}
		return parecerCadastro;
	}

	public void setParecerCadastro(Parecer parecerCadastro) {
		this.parecerCadastro = parecerCadastro;
	}

	public List<Parecer> getListaPareceres() {
		return listaPareceres;
	}

	public void setListaPareceres(List<Parecer> listaPareceres) {
		this.listaPareceres = listaPareceres;
	}

	public List<Parecer> getListaPareceresFiltrados() {
		return listaPareceresFiltrados;
	}

	public void setListaPareceresFiltrados(List<Parecer> listaPareceresFiltrados) {
		this.listaPareceresFiltrados = listaPareceresFiltrados;
	}

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public List<Atos> getListaAtos() {
		if (listaAtos == null) {
			listaAtos = new ArrayList<>();
		}
		return listaAtos;
	}

	public void setListaAtos(List<Atos> listaAtos) {
		this.listaAtos = listaAtos;
	}

	public List<Atos> getListaAtosFiltrados() {
		return listaAtosFiltrados;
	}

	public void setListaAtosFiltrados(List<Atos> listaAtosFiltrados) {
		this.listaAtosFiltrados = listaAtosFiltrados;
	}

	public List<Documento> getListaDocumentos() {
		return listaDocumentos;
	}

	public void setListaDocumentos(List<Documento> listaDocumentos) {
		this.listaDocumentos = listaDocumentos;
	}

	public List<Atos> getListaAtosSelecionados() {
		return listaAtosSelecionados;
	}

	public void setListaAtosSelecionados(List<Atos> listaAtosSelecionados) {
		this.listaAtosSelecionados = listaAtosSelecionados;
	}

	public List<Checklist> getListaAtosNaoSelecionados() {
		if (listaAtosNaoSelecionados == null) {
			listaAtosNaoSelecionados = new ArrayList<>();
		}
		return listaAtosNaoSelecionados;
	}

	public void setListaAtosNaoSelecionados(List<Checklist> listaAtosNaoSelecionados) {
		this.listaAtosNaoSelecionados = listaAtosNaoSelecionados;
	}

	public List<Checklist> getListaChecklists() {
		if (listaChecklists == null) {
			listaChecklists = new ArrayList<>();
		}
		return listaChecklists;
	}

	public void setListaChecklists(List<Checklist> listaChecklists) {
		this.listaChecklists = listaChecklists;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public void novo() {
		parecerCadastro = new Parecer();
	}

	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}

	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}

	public Status[] getStatus() {
		return Status.values();
	}
	

	public List<Secretaria> getListaSecretarias() {
		return listaSecretarias;
	}

	public void setListaSecretarias(List<Secretaria> listaSecretarias) {
		this.listaSecretarias = listaSecretarias;
	}

	public void salvar() {
		try {
			ParecerDAO pdao = new ParecerDAO();
			// numero do parecer automático
			ControleRegistroDAO crdao = new ControleRegistroDAO();
			ControleRegistro registro = crdao.buscarPorDescricao("parecer");
			int numero = registro.getValor();
			String numeroParecer = String.valueOf(numero);
			if (numeroParecer != null) {
				parecerCadastro.setNumero(numeroParecer);
				registro.setValor(registro.getValor() + 1);
				crdao.editar(registro);
			} else {

			}

			Long codigoParecer = pdao.salvar(parecerCadastro);
			Parecer parecerFK = pdao.buscarPorCodigo(codigoParecer);

			for (Checklist checklist : listaChecklists) {
				checklist.setParecer(parecerFK);

				ChecklistDAO cdao = new ChecklistDAO();
				cdao.salvar(checklist);
			}
			// adicionando os inativos
			for (Checklist checklist : listaAtosNaoSelecionados) {
				checklist.setParecer(parecerFK);

				ChecklistDAO cdao = new ChecklistDAO();
				cdao.salvar(checklist);
			}
			// fim dos inativos
			FacesUtil.addMsgInfo("Parecer número: " + numeroParecer + " Cadastro com Sucesso!");

			parecerCadastro = new Parecer();
			listaChecklists = new ArrayList<>();
			listaAtosNaoSelecionados = new ArrayList<>();

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar o Parecer " + ex.getMessage());
		}
	}

	public void listar() {
		try {
			ParecerDAO pdao = new ParecerDAO();
			if (autenticacaoBean.getFuncionarioLogado().getFuncao().equals("administrador")
					|| autenticacaoBean.getFuncionarioLogado().getFuncao().equals("digitador")) {
				listaPareceres = pdao.listar();
			} else {
				listaPareceres = pdao.buscarPorResponsavel(autenticacaoBean.getFuncionarioLogado().getId());
			}

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao listar os Pareceres " + ex.getMessage());
		}
	}

	public void carregarDados() {

		try {
			if (codigo != null) {
				ParecerDAO pdao = new ParecerDAO();
				parecerCadastro = pdao.buscarPorCodigo(codigo);

				ChecklistDAO cdao = new ChecklistDAO();
				listaChecklists = cdao.buscarPorParecer(codigo, "Aplicar");
				listaAtosNaoSelecionados = cdao.buscarPorParecer(codigo, "Omitir");

				AtosDAO adao = new AtosDAO();
				listaAtos = adao.listar();

				DocumentoDAO ddao = new DocumentoDAO();
				listaDocumentos = ddao.listar();

				FuncionarioDAO fdao = new FuncionarioDAO();
				listaFuncionarios = fdao.listar();

			} else {
				parecerCadastro = new Parecer();
				// dataCriacao = new Date();

				FuncionarioDAO fdao = new FuncionarioDAO();
				listaFuncionarios = fdao.listar();
				Funcionario funTemp = fdao.buscarPorCodigo(autenticacaoBean.getFuncionarioLogado().getId());
				parecerCadastro.setFuncionario(funTemp);

				DocumentoDAO ddao = new DocumentoDAO();
				listaDocumentos = ddao.listar();

				AtosDAO adao = new AtosDAO();
				listaAtos = adao.listar();
				
				SecretariaDAO sdao = new SecretariaDAO();
				listaSecretarias = sdao.listar();

			}
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao carregar os dados do parecer " + ex.getMessage());
		}
	}

	public void adicionarListAtos1() {
		List<Atos> listaNSelecionados = new ArrayList<>();
		List<Atos> listaAtosTemp = new ArrayList<>();
		listaAtosTemp = listaAtosFiltrados;
		for (int posi = 0; posi < listaAtosTemp.size(); posi++) {
			Atos atosTemp = (Atos) listaAtosTemp.get(posi);

			boolean diferente = false;
			for (int pos = 0; pos < listaAtosSelecionados.size(); pos++) {
				Atos ato = (Atos) listaAtosSelecionados.get(pos);

				if (atosTemp.getId() == ato.getId() && atosTemp.getStatus().equals("Aplicar")) {// Se
																								// as
																								// idis
																								// forem
																								// iguais
																								// eles
																								// serão
																								// adicionados
																								// como
																								// presentes
																								// e
																								// aplicavéis
					adicionarAtos(ato, "Aplicar");// adiciona os atos como
													// presentes temporariamente
					break;
					// assim que o ato é encontrado é adicionado no checklist
				}
				if (atosTemp.getId() != ato.getId() && atosTemp.getStatus().equals("Aplicar")) {// Se
																								// as
																								// idis
																								// forem
																								// diferentes
																								// e
																								// o
																								// status
																								// ativo,
																								// os
																								// atos
																								// não
																								// se
																								// encontram
																								// dentro
																								// dos
																								// presentes
					diferente = true; // nem na situação aplicável
				}
				if (pos == listaAtosSelecionados.size() - 1) {
					if (diferente == true) {
						listaNSelecionados.add(atosTemp);
						adicionarAtosInativos(atosTemp, "Omitir");// adiciona os
																	// atos como
																	// ausentes
					}
				}
			}

			/*
			 * for (int pos = 0; pos < listaAtosSelecionados.size(); pos++) {
			 * Atos ato = (Atos) listaAtosSelecionados.get(pos);
			 * adicionarAtos(ato,"A");
			 */
		}
	}

	public void adicionarListAtos() {
		// List<Atos> listaNSelecionados = new ArrayList<>();
		List<Atos> listaAtosTemp = new ArrayList<>();

		for (int posi = 0; posi < listaAtosFiltrados.size(); posi++) {
			Atos atosTempFiltrado = (Atos) listaAtosFiltrados.get(posi);

			for (int pos = 0; pos < listaAtosSelecionados.size(); pos++) {
				Atos atosTempSelecionado = (Atos) listaAtosSelecionados.get(pos);

				if (atosTempFiltrado.getId().equals(atosTempSelecionado.getId())) {
					listaAtosTemp.add(atosTempFiltrado);
				}
			}
		}
		for (int posj = 0; posj < listaAtosTemp.size(); posj++) {

			Atos atosTemp = (Atos) listaAtosTemp.get(posj);

			if (atosTemp.getStatus().equals("Aplicar")) {// Se as idis forem
															// iguais eles serão
															// adicionados como
															// presentes e
															// aplicavéis
				adicionarAtos(atosTemp, "Aplicar");// adiciona os atos como
													// presentes temporariamente

				// assim que o ato é encontrado é adicionado no checklist
			}
			if (atosTemp.getStatus().equals("Omitir")) {// Se as idis forem
														// diferentes e o status
														// ativo, os atos não se
														// encontram dentro dos
														// presentes
				adicionarAtosInativos(atosTemp, "Omitir");// adiciona os atos
															// como ausentes
															// //nem na situação
															// aplicável
			}
		}
	}

	public void adicionarAtosInativos(Atos ato, String status) {
		int posicaoEncontrada = -1;
		// percorre na lista de atos para verificar se o ato já existe
		if (listaAtosNaoSelecionados == null) {
			listaAtosNaoSelecionados = new ArrayList<>();
		}
		for (int pos = 0; pos < listaAtosNaoSelecionados.size() && posicaoEncontrada < 0; pos++) {
			Checklist cheklistTemp = listaAtosNaoSelecionados.get(pos);

			if (cheklistTemp.getAtos().equals(ato)) {
				posicaoEncontrada = pos;
			}
		}

		Checklist checklist = new Checklist();
		checklist.setAtos(ato);
		ChecklistDAO cdao = new ChecklistDAO();

		if (posicaoEncontrada < 0) {

			checklist.setCateoria(ato.getCategoria());
			checklist.setStatus(status);
			// listaAtosNaoSelecionados.add(checklist);
			listaAtosNaoSelecionados.add(checklist);
			if (parecerCadastro.getId() != null) {
				Checklist checkTemp = cdao.buscarPorParecerAto(parecerCadastro.getId(), checklist.getAtos().getId());
				if (checkTemp == null) {
					checklist.setParecer(parecerCadastro);
					cdao.salvar(checklist);
				} else {
					cdao.editar(checkTemp);
				}
			}
		} else {
			checklist.setCateoria(ato.getCategoria());
			checklist.setStatus(status);
			listaAtosNaoSelecionados.set(posicaoEncontrada, checklist);
		}

	}

	public void adicionarAtos(Atos ato, String status) {
		int posicaoEncontrada = -1;
		if (listaChecklists == null) {
			listaChecklists = new ArrayList<>();
		}
		// percorre na lista de atos para verificar se o ato já existe
		for (int pos = 0; pos < listaChecklists.size() && posicaoEncontrada < 0; pos++) {
			Checklist cheklistTemp = listaChecklists.get(pos);

			if (cheklistTemp.getAtos().equals(ato)) {
				posicaoEncontrada = pos;
			}
		}

		Checklist checklist = new Checklist();
		checklist.setAtos(ato);
		ChecklistDAO cdao = new ChecklistDAO();
		// se o item não for encontrado recebe posicaoEncontrada -1
		// e será adicionado no gravar do checklist
		if (posicaoEncontrada < 0) {
			checklist.setCateoria(ato.getCategoria());
			checklist.setStatus(status);
			listaChecklists.add(checklist);
			if (parecerCadastro.getId() != null) {
				Checklist checkTemp = cdao.buscarPorParecerAto(parecerCadastro.getId(), checklist.getAtos().getId());
				if (checkTemp == null) {
					checklist.setParecer(parecerCadastro);
					cdao.salvar(checklist);
				} else {
					cdao.editar(checkTemp);
				}
			}

		} else {

			checklist.setCateoria(ato.getCategoria());
			checklist.setStatus(status);
			listaChecklists.set(posicaoEncontrada, checklist);

		}

	}

	public void removerAtos(Checklist checklist) {
		int posicaoEncontrada = -1;

		for (int pos = 0; pos < listaChecklists.size() && posicaoEncontrada < 0; pos++) {
			Checklist checklistTemp = listaChecklists.get(pos);

			if (checklistTemp.getAtos().equals(checklist.getAtos())) {
				posicaoEncontrada = pos;
				listaAtosNaoSelecionados.add(checklistTemp);
			}
		}
		ChecklistDAO cdao = new ChecklistDAO();

		if (posicaoEncontrada > -1) {
			listaChecklists.remove(posicaoEncontrada);

			if (checklist.getParecer() != null) {
				Checklist checkTemp = cdao.buscarPorParecerAto(checklist.getParecer().getId(),
						checklist.getAtos().getId());
				if (checkTemp != null) {
					checkTemp.setStatus("Omitir");
					cdao.editar(checkTemp);
				}
			}
		}
	}

	public void removerAtosInativos(Checklist checklist) {
		int posicaoEncontrada = -1;

		for (int pos = 0; pos < listaAtosNaoSelecionados.size() && posicaoEncontrada < 0; pos++) {
			Checklist checklistTemp = listaAtosNaoSelecionados.get(pos);

			if (checklistTemp.getAtos().equals(checklist.getAtos())) {
				posicaoEncontrada = pos;
				listaChecklists.add(checklistTemp);
			}
		}
		ChecklistDAO cdao = new ChecklistDAO();

		if (posicaoEncontrada > -1) {

			listaAtosNaoSelecionados.remove(posicaoEncontrada);

			if (checklist.getParecer() != null) {
				Checklist checkTemp = cdao.buscarPorParecerAto(checklist.getParecer().getId(),
						checklist.getAtos().getId());
				if (checkTemp != null) {
					checkTemp.setStatus("Aplicar");
					cdao.editar(checkTemp);
				}
			}
		}
	}

	public void buscarPorPeriodo(Date inicio, Date fim) {
		try {
			ParecerDAO pdao = new ParecerDAO();
			listaPareceres = pdao.buscarPorPeriodo(inicio, fim);
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro pesquisar os Pareceres " + ex.getMessage());
		}
	}

	public void editar() {
		try {
			ParecerDAO pdao = new ParecerDAO();
			pdao.editar(parecerCadastro);

			for (Checklist checklist : listaChecklists) {
				ChecklistDAO cdao = new ChecklistDAO();
				checklist.setParecer(parecerCadastro);
				Checklist checkTemp = cdao.buscarPorParecerAto(checklist.getParecer().getId(),
						checklist.getAtos().getId());
				if (checkTemp != null) {
					checkTemp.setParecer(parecerCadastro);
					checkTemp.setAtos(checklist.getAtos());

					cdao.editar(checkTemp);
				} else {
					cdao.salvar(checklist);
				}
			}

			for (Checklist checklist : listaAtosNaoSelecionados) {
				ChecklistDAO cdao = new ChecklistDAO();
				checklist.setParecer(parecerCadastro);
				Checklist checkTemp = cdao.buscarPorParecerAto(checklist.getParecer().getId(),
						checklist.getAtos().getId());
				if (checkTemp != null) {
					checkTemp.setParecer(parecerCadastro);
					checkTemp.setAtos(checklist.getAtos());

					cdao.editar(checkTemp);
				} else {
					cdao.salvar(checklist);
				}
			}

			FacesUtil.addMsgInfo("Parecer editado com Sucesso!");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao editar o Parecer " + ex.getMessage());
		}
	}
	/**
	 * 
	 * @return
	 */
	public String excluir() {
		try {
			if (!listaAtosNaoSelecionados.isEmpty()) {
				for (Checklist checklist : listaAtosNaoSelecionados) {
					checklist.setParecer(parecerCadastro);

					ChecklistDAO cdao = new ChecklistDAO();
					cdao.excluir(checklist);
				}
			}
			if (!listaChecklists.isEmpty()) {
				for (Checklist checklist : listaChecklists) {
					checklist.setParecer(parecerCadastro);

					ChecklistDAO cdao = new ChecklistDAO();
					cdao.excluir(checklist);
				}
			}

			ParecerDAO pdao = new ParecerDAO();
			pdao.excluir(parecerCadastro);

			FacesUtil.addMsgInfo("Parecer excluído com Sucesso!");
			return "/pages/parecer/parecerPesquisa.xhtml?faces-redirect=true";
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir o Parecer " + ex.getMessage());
			return null;
		}
	}

	public void gerarParecer() {
		String caminho = "/parecer/parecer.jasper";

		Map<String, Object> parametros = new HashMap<>();

		if (this.parecerCadastro.getStatus().equals("conformidade")) {
			parametros.put("DESFECHO", "possuindo as");
		}
		if (this.parecerCadastro.getStatus().equals("Conformidade parcial")) {
			parametros.put("DESFECHO", "possuindo parte das");
		}
		if (this.parecerCadastro.getStatus().equals("desconformidade")) {
			parametros.put("DESFECHO", "não possuindo as");
		}
		if (this.parecerCadastro.getStatus().equals("sem status")) {
			parametros.put("DESFECHO", "sem status");
		}
		parametros.put("NUM_PARECER", this.parecerCadastro.getNumero());
		parametros.put("COD_DOC_P", this.parecerCadastro.getDocumento().getId());
		parametros.put("RESPONSAVEL", this.parecerCadastro.getFuncionario().getNome());
		parametros.put("FUNCAO_RESP", this.parecerCadastro.getFuncionario().getFuncao());
		parametros.put("COD_PARECER", this.parecerCadastro.getId());
		// parametros.put("COD_STATUS", "Aplicar");
		// parametros.put("COD_STATUS_I", "Omitir");

		GeraParecer geraParecer = new GeraParecer();
		geraParecer.geradorDeParecer(caminho, parametros);
	}

	public void gerarParecer(Long cod) {
		if (cod != null) {
			ParecerDAO pdao = new ParecerDAO();
			parecerCadastro = pdao.buscarPorCodigo(cod);

			ChecklistDAO cdao = new ChecklistDAO();
			listaChecklists = cdao.buscarPorParecer(cod);
			String caminho = "/parecer/parecer.jasper";

			Map<String, Object> parametros = new HashMap<>();

			if (this.parecerCadastro.getStatus().equals("conformidade")) {
				parametros.put("DESFECHO", "possuindo as");
			}
			if (this.parecerCadastro.getStatus().equals("Conformidade parcial")) {
				parametros.put("DESFECHO", "possuindo parte das");
			}
			if (this.parecerCadastro.getStatus().equals("desconformidade")) {
				parametros.put("DESFECHO", "não possuindo as");
			}
			if (this.parecerCadastro.getStatus().equals("sem status")) {
				parametros.put("DESFECHO", "sem status");
			}
			parametros.put("NUM_PARECER", this.parecerCadastro.getNumero());
			parametros.put("COD_DOC_P", this.parecerCadastro.getDocumento().getId());
			parametros.put("RESPONSAVEL", this.parecerCadastro.getFuncionario().getNome());
			parametros.put("FUNCAO_RESP", this.parecerCadastro.getFuncionario().getFuncao());
			parametros.put("COD_PARECER", this.parecerCadastro.getId());

			GeraParecer geraParecer = new GeraParecer();
			geraParecer.geradorDeParecer(caminho, parametros);
		}
	}
}
