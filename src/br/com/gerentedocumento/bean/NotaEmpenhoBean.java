package br.com.gerentedocumento.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.gerentedocumento.dao.DocumentoDAO;
import br.com.gerentedocumento.dao.NotaEmpenhoDAO;
import br.com.gerentedocumento.domain.Documento;
import br.com.gerentedocumento.domain.NotaEmpenho;
import br.com.gerentedocumento.util.FacesUtil;

@ManagedBean
@ViewScoped
public class NotaEmpenhoBean {

	private NotaEmpenho notaCadastro;
	
	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;

	private List<NotaEmpenho> listaNotas;
	private List<NotaEmpenho> listaNotasFiltradas;

	private List<Documento> listaDocumentos;

	private String acao;
	private Long codigo;

	public NotaEmpenho getNotaCadastro() {
		if (notaCadastro == null) {
			notaCadastro = new NotaEmpenho();
		}
		return notaCadastro;
	}

	public void setNotaCadastro(NotaEmpenho notaCadastro) {
		this.notaCadastro = notaCadastro;
	}

	public List<NotaEmpenho> getListaNotas() {
		return listaNotas;
	}

	public void setListaNotas(List<NotaEmpenho> listaNotas) {
		this.listaNotas = listaNotas;
	}

	public List<NotaEmpenho> getListaNotasFiltradas() {
		return listaNotasFiltradas;
	}

	public void setListaNotasFiltradas(List<NotaEmpenho> listaNotasFiltradas) {
		this.listaNotasFiltradas = listaNotasFiltradas;
	}

	public List<Documento> getListaDocumentos() {
		return listaDocumentos;
	}

	public void setListaDocumentos(List<Documento> listaDocumentos) {
		this.listaDocumentos = listaDocumentos;
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

	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}

	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}

	public void novo() {
		notaCadastro = new NotaEmpenho();
	}

	public void salvar() {
		try {
			NotaEmpenhoDAO ndao = new NotaEmpenhoDAO();
			notaCadastro.setFuncionario(autenticacaoBean.getFuncionarioLogado());
			ndao.salvar(notaCadastro);

			FacesUtil.addMsgInfo("Nota de Empenho Cadastrado com Sucesso!");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao Cadastrar a Nota de Empenho! " + ex.getMessage());
		}
	}

	public void listar() {
		try {
			NotaEmpenhoDAO ndao = new NotaEmpenhoDAO();
									
			if (autenticacaoBean.getFuncionarioLogado().getFuncao().equals("administrador")
					|| autenticacaoBean.getFuncionarioLogado().getFuncao().equals("digitador")) {
				listaNotas = ndao.listar();				
			} else {
				listaNotas = ndao.buscarPorFuncionario(autenticacaoBean.getFuncionarioLogado().getId());
			}

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao listar as Notas! " + ex.getMessage());
		}
	}

	public void carregarDados() {
		try {
			if (codigo != null) {
				NotaEmpenhoDAO ndao = new NotaEmpenhoDAO();
				notaCadastro = ndao.buscarPorCodigo(codigo);
			} else {
				notaCadastro = new NotaEmpenho();
			}

			NotaEmpenhoDAO ndao = new NotaEmpenhoDAO();
			listaNotas = ndao.listar();

			DocumentoDAO ddao = new DocumentoDAO();
			listaDocumentos = ddao.listar();			
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao carregar os dados da Nota de Empenho " + ex.getMessage());
		}
	}

	public void editar() {
		try {
			NotaEmpenhoDAO ndao = new NotaEmpenhoDAO();
			notaCadastro.setFuncionario(autenticacaoBean.getFuncionarioLogado());
			ndao.editar(notaCadastro);

			notaCadastro = new NotaEmpenho();

			FacesUtil.addMsgInfo("Nota de Empenho editada com Sucesso!");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao editar a Nota de Empenho! " + ex.getMessage());
		}
	}

	public String excluir() {
		try {
			NotaEmpenhoDAO ndao = new NotaEmpenhoDAO();
			ndao.excluir(notaCadastro);

			FacesUtil.addMsgInfo("Nota de Empenho Excluída com sucesso!");
			return "/pages/notaempenho/notaPesquisa.xhtml?faces-redirect=true";
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao excluir a Nota de Empenho! " + ex.getMessage());
			return null;
		}
	}
}
