package br.com.gerentedocumento.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.gerentedocumento.constante.Status;
import br.com.gerentedocumento.dao.AtosDAO;
import br.com.gerentedocumento.dao.ChecklistDAO;
import br.com.gerentedocumento.dao.ChecklistNotaDAO;
import br.com.gerentedocumento.dao.ControleRegistroDAO;
import br.com.gerentedocumento.dao.DocumentoDAO;
import br.com.gerentedocumento.dao.FuncionarioDAO;
import br.com.gerentedocumento.dao.NotaTecnicaDAO;
import br.com.gerentedocumento.domain.Atos;
import br.com.gerentedocumento.domain.Checklist;
import br.com.gerentedocumento.domain.ChecklistNota;
import br.com.gerentedocumento.domain.ControleRegistro;
import br.com.gerentedocumento.domain.Documento;
import br.com.gerentedocumento.domain.Funcionario;
import br.com.gerentedocumento.domain.NotaTecnica;
import br.com.gerentedocumento.util.FacesUtil;
import br.com.gerentedocumento.util.GeraNotaTecnica;

@ManagedBean
@ViewScoped
public class NotaTecnicaBean {

	private NotaTecnica notaTecnicaCadastro;

	private List<NotaTecnica> listaNotas;
	private List<NotaTecnica> listaNotasFiltradas;
	private List<Funcionario> listaFuncionarios;
	private List<Atos> listaAtos;
	private List<Atos> listaAtosFiltrados;
	private List<Atos> listaAtosSelecionados;
	private List<ChecklistNota> listaAtosNaoSelecionados;
	private List<Documento> listaDocumentos;
	private List<ChecklistNota> listaChecklists;

	private String acao;
	private Long codigo;
	private Date dataCriacao;

	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;

	public NotaTecnica getNotaTecnicaCadastro() {
		if (notaTecnicaCadastro == null) {
			notaTecnicaCadastro = new NotaTecnica();
			dataCriacao = new Date();
		}
		return notaTecnicaCadastro;
	}

	public void setNotaTecnicaCadastro(NotaTecnica notaTecnicaCadastro) {
		this.notaTecnicaCadastro = notaTecnicaCadastro;
	}

	public List<NotaTecnica> getListaNotas() {
		return listaNotas;
	}

	public void setListaNotas(List<NotaTecnica> listaNotas) {
		this.listaNotas = listaNotas;
	}

	public List<NotaTecnica> getListaNotasFiltradas() {
		return listaNotasFiltradas;
	}

	public void setListaNotasFiltradas(List<NotaTecnica> listaNotasFiltradas) {
		this.listaNotasFiltradas = listaNotasFiltradas;
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

	public List<Atos> getListaAtosSelecionados() {
		return listaAtosSelecionados;
	}

	public void setListaAtosSelecionados(List<Atos> listaAtosSelecionados) {
		this.listaAtosSelecionados = listaAtosSelecionados;
	}

	public List<ChecklistNota> getListaAtosNaoSelecionados() {
		if (listaAtosNaoSelecionados == null) {
			listaAtosNaoSelecionados = new ArrayList<>();
		}
		return listaAtosNaoSelecionados;
	}

	public void setListaAtosNaoSelecionados(List<ChecklistNota> listaAtosNaoSelecionados) {
		this.listaAtosNaoSelecionados = listaAtosNaoSelecionados;
	}

	public List<Documento> getListaDocumentos() {
		return listaDocumentos;
	}

	public void setListaDocumentos(List<Documento> listaDocumentos) {
		this.listaDocumentos = listaDocumentos;
	}

	public List<ChecklistNota> getListaChecklists() {
		if (listaChecklists == null) {
			listaChecklists = new ArrayList<>();
		}
		return listaChecklists;
	}

	public void setListaChecklists(List<ChecklistNota> listaChecklists) {
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

	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}

	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}

	public Status[] getStatus() {
		return Status.values();
	}

	public void novo() {
		notaTecnicaCadastro = new NotaTecnica();
	}

	public void salvar() {
		try {
			NotaTecnicaDAO ntdao = new NotaTecnicaDAO();
			// numero da notatecnica automático
			ControleRegistroDAO crdao = new ControleRegistroDAO();
			ControleRegistro registro = crdao.buscarPorDescricao("notaTecnica");
			int numero = registro.getValor();
			String numeroNota = String.valueOf(numero);
			notaTecnicaCadastro.setNumero(numeroNota);
			registro.setValor(registro.getValor() + 1);
			crdao.editar(registro);

			Long codigoNotaTecnica = ntdao.Salvar(notaTecnicaCadastro);
			NotaTecnica notaFK = ntdao.buscarPorCodigo(codigoNotaTecnica);

			for (ChecklistNota checklist : listaChecklists) {
				checklist.setNotaTecnica(notaFK);

				ChecklistNotaDAO cndao = new ChecklistNotaDAO();
				cndao.salvar(checklist);
			}
			// adicionando os inativos
			for (ChecklistNota checklist : listaAtosNaoSelecionados) {
				checklist.setNotaTecnica(notaFK);

				ChecklistNotaDAO cndao = new ChecklistNotaDAO();
				cndao.salvar(checklist);
			}
			// fim dos inativos

			FacesUtil.addMsgInfo("Nota Técnica número: " + numeroNota + " Cadastrada com Sucesso!");

			notaTecnicaCadastro = new NotaTecnica();
			listaChecklists = new ArrayList<>();
			listaAtosNaoSelecionados = new ArrayList<>();

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar a Nota Técnica " + ex.getMessage());
		}
	}

	public void listar() {
		try {
			NotaTecnicaDAO ntdao = new NotaTecnicaDAO();
			if (autenticacaoBean.getFuncionarioLogado().getFuncao().equals("administrador")
					|| autenticacaoBean.getFuncionarioLogado().getFuncao().equals("digitador")) {
				listaNotas = ntdao.listar();
			} else {
				listaNotas = ntdao.buscarPorResponsavel(autenticacaoBean.getFuncionarioLogado().getId());
			}
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao listar as Notas Técnicas " + ex.getMessage());
		}
	}

	public void carregarDados() {
		try {
			if (codigo != null) {
				NotaTecnicaDAO ntdao = new NotaTecnicaDAO();
				notaTecnicaCadastro = ntdao.buscarPorCodigo(codigo);

				ChecklistNotaDAO cdao = new ChecklistNotaDAO();
				listaChecklists = cdao.buscarPorNotaTecnica(codigo, "Aplicar");
				listaAtosNaoSelecionados = cdao.buscarPorNotaTecnica(codigo, "Omitir");

				AtosDAO adao = new AtosDAO();
				listaAtos = adao.listar();

				DocumentoDAO ddao = new DocumentoDAO();
				listaDocumentos = ddao.listar();

				FuncionarioDAO fdao = new FuncionarioDAO();
				listaFuncionarios = fdao.listar();

			} else {
				notaTecnicaCadastro = new NotaTecnica();

				FuncionarioDAO fdao = new FuncionarioDAO();
				listaFuncionarios = fdao.listar();
				Funcionario funTemp = fdao.buscarPorCodigo(autenticacaoBean.getFuncionarioLogado().getId());
				notaTecnicaCadastro.setFuncionario(funTemp);

				DocumentoDAO ddao = new DocumentoDAO();
				listaDocumentos = ddao.listar();

				AtosDAO adao = new AtosDAO();
				listaAtos = adao.listar();

			}
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao carregar os dados da Nota Técnica " + ex.getMessage());
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
			ChecklistNota cheklistTemp = listaAtosNaoSelecionados.get(pos);

			if (cheklistTemp.getAtos().equals(ato)) {
				posicaoEncontrada = pos;
			}
		}

		ChecklistNota checklist = new ChecklistNota();
		checklist.setAtos(ato);
		ChecklistNotaDAO cdao = new ChecklistNotaDAO();

		if (posicaoEncontrada < 0) {

			checklist.setCategoria(ato.getCategoria());
			checklist.setStatus(status);
			// listaAtosNaoSelecionados.add(checklist);
			listaAtosNaoSelecionados.add(checklist);
			if (notaTecnicaCadastro.getId() != null) {
				ChecklistNota checkTemp = cdao.buscarPorNotaAto(notaTecnicaCadastro.getId(),
						checklist.getAtos().getId());
				if (checkTemp == null) {
					checklist.setNotaTecnica(notaTecnicaCadastro);
					cdao.salvar(checklist);
				} else {
					cdao.editar(checkTemp);
				}
			}
		} else {
			checklist.setCategoria(ato.getCategoria());
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
			ChecklistNota cheklistTemp = listaChecklists.get(pos);

			if (cheklistTemp.getAtos().equals(ato)) {
				posicaoEncontrada = pos;
			}
		}

		ChecklistNota checklist = new ChecklistNota();
		checklist.setAtos(ato);
		ChecklistNotaDAO cdao = new ChecklistNotaDAO();
		// se o item não for encontrado recebe posicaoEncontrada -1
		// e será adicionado no gravar do checklist
		if (posicaoEncontrada < 0) {
			checklist.setCategoria(ato.getCategoria());
			checklist.setStatus(status);
			listaChecklists.add(checklist);
			if (notaTecnicaCadastro.getId() != null) {
				ChecklistNota checkTemp = cdao.buscarPorNotaAto(notaTecnicaCadastro.getId(),
						checklist.getAtos().getId());
				if (checkTemp == null) {
					checklist.setNotaTecnica(notaTecnicaCadastro);
					cdao.salvar(checklist);
				} else {
					cdao.editar(checkTemp);
				}
			}

		} else {

			checklist.setCategoria(ato.getCategoria());
			checklist.setStatus(status);
			listaChecklists.set(posicaoEncontrada, checklist);

		}
	}

	public void removerAtos(Checklist checklist) {
		int posicaoEncontrada = -1;

		for (int pos = 0; pos < listaChecklists.size() && posicaoEncontrada < 0; pos++) {
			ChecklistNota checklistTemp = listaChecklists.get(pos);

			if (checklistTemp.getAtos().equals(checklist.getAtos())) {
				posicaoEncontrada = pos;
				listaAtosNaoSelecionados.add(checklistTemp);
			}
		}
		ChecklistNotaDAO cdao = new ChecklistNotaDAO();

		if (posicaoEncontrada > -1) {
			listaChecklists.remove(posicaoEncontrada);

			if (checklist.getParecer() != null) {
				ChecklistNota checkTemp = cdao.buscarPorNotaAto(checklist.getParecer().getId(),
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
			ChecklistNota checklistTemp = listaAtosNaoSelecionados.get(pos);

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

	public void editar() {
		try {
			NotaTecnicaDAO ntdao = new NotaTecnicaDAO();
			ntdao.editar(notaTecnicaCadastro);

			for (ChecklistNota checklist : listaChecklists) {
				ChecklistNotaDAO cdao = new ChecklistNotaDAO();
				checklist.setNotaTecnica(notaTecnicaCadastro);
				ChecklistNota checkTemp = cdao.buscarPorNotaAto(checklist.getNotaTecnica().getId(),
						checklist.getAtos().getId());
				if (checkTemp != null) {
					checkTemp.setNotaTecnica(notaTecnicaCadastro);
					checkTemp.setAtos(checklist.getAtos());

					cdao.salvar(checkTemp);
				} else {
					cdao.editar(checklist);
				}
			}

			for (ChecklistNota checklist : listaAtosNaoSelecionados) {
				ChecklistNotaDAO cdao = new ChecklistNotaDAO();
				checklist.setNotaTecnica(notaTecnicaCadastro);
				ChecklistNota checkTemp = cdao.buscarPorNotaAto(checklist.getNotaTecnica().getId(),
						checklist.getAtos().getId());
				if (checkTemp != null) {
					checkTemp.setNotaTecnica(notaTecnicaCadastro);
					checkTemp.setAtos(checklist.getAtos());

					cdao.salvar(checkTemp);
				} else {
					cdao.editar(checklist);
				}
			}

			FacesUtil.addMsgInfo("Nota Técnica editado com Sucesso!");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao editar o Nota Técnica " + ex.getMessage());
		}
	}

	public String excluir() {
		try {
			
			if (!listaChecklists.isEmpty()) {
				for (ChecklistNota checklist : listaChecklists) {
					checklist.setNotaTecnica(notaTecnicaCadastro);

					ChecklistNotaDAO cdao = new ChecklistNotaDAO();
					cdao.excluir(checklist);
				}
			}
			
			if (!listaAtosNaoSelecionados.isEmpty()) {
				for (ChecklistNota checklist : listaAtosNaoSelecionados) {
					checklist.setNotaTecnica(notaTecnicaCadastro);

					ChecklistNotaDAO cdao = new ChecklistNotaDAO();
					cdao.excluir(checklist);
				}
			}

			NotaTecnicaDAO ntdao = new NotaTecnicaDAO();
			ntdao.excluir(notaTecnicaCadastro);

			FacesUtil.addMsgInfo("Nota Técnica excluída com Sucesso!");
			return "/pages/notaTecnica/notaTecnicaPesquisa.xhtml?faces-redirect=true";
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir o Nota Técnica " + ex.getMessage());
			return null;
		}
	}

	public void gerarNota() {
		String caminho = "/notatecnica/nota.jasper";

		Map<String, Object> parametros = new HashMap<>();

		if (this.notaTecnicaCadastro.getStatus().equals("conformidade")) {
			parametros.put("DESFECHO", "possuindo as");
		}
		if (this.notaTecnicaCadastro.getStatus().equals("Conformidade parcial")) {
			parametros.put("DESFECHO", "possuindo parte das");
		}
		if (this.notaTecnicaCadastro.getStatus().equals("desconformidade")) {
			parametros.put("DESFECHO", "não possuindo as");
		}
		if (this.notaTecnicaCadastro.getStatus().equals("sem status")) {
			parametros.put("DESFECHO", "sem status");
		}

		parametros.put("NUM_NOTA", this.notaTecnicaCadastro.getNumero());
		parametros.put("COD_DOC_P", this.notaTecnicaCadastro.getDocumento().getId());
		parametros.put("RESPONSAVEL", this.notaTecnicaCadastro.getFuncionario().getNome());
		parametros.put("FUNCAO_RESP", this.notaTecnicaCadastro.getFuncionario().getFuncao());
		parametros.put("COD_NOTA", this.notaTecnicaCadastro.getId());
		// parametros.put("COD_STATUS", "Aplicar");
		// parametros.put("COD_STATUS_I", "Omitir");

		GeraNotaTecnica geraNota = new GeraNotaTecnica();
		geraNota.geradorDeNota(caminho, parametros);
	}

	public void gerarNota(Long cod) {
		if (cod != null) {
			NotaTecnicaDAO ntdao = new NotaTecnicaDAO();
			notaTecnicaCadastro = ntdao.buscarPorCodigo(cod);

			ChecklistNotaDAO cndao = new ChecklistNotaDAO();
			listaChecklists = cndao.buscarPorNota(cod);

			String caminho = "/notatecnica/nota.jasper";

			Map<String, Object> parametros = new HashMap<>();

			if (this.notaTecnicaCadastro.getStatus().equals("conformidade")) {
				parametros.put("DESFECHO", "possuindo as");
			}
			if (this.notaTecnicaCadastro.getStatus().equals("Conformidade parcial")) {
				parametros.put("DESFECHO", "possuindo parte das");
			}
			if (this.notaTecnicaCadastro.getStatus().equals("desconformidade")) {
				parametros.put("DESFECHO", "não possuindo as");
			}
			if (this.notaTecnicaCadastro.getStatus().equals("sem status")) {
				parametros.put("DESFECHO", "sem status");
			}

			parametros.put("NUM_NOTA", this.notaTecnicaCadastro.getNumero());
			parametros.put("COD_DOC_P", this.notaTecnicaCadastro.getDocumento().getId());
			parametros.put("RESPONSAVEL", this.notaTecnicaCadastro.getFuncionario().getNome());
			parametros.put("FUNCAO_RESP", this.notaTecnicaCadastro.getFuncionario().getFuncao());
			parametros.put("COD_NOTA", this.notaTecnicaCadastro.getId());

			GeraNotaTecnica geraNota = new GeraNotaTecnica();
			geraNota.geradorDeNota(caminho, parametros);
		}
	}
}
