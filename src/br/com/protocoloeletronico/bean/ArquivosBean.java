package br.com.protocoloeletronico.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


import br.com.protocoloeletronico.dao.ArquivoDAO;
import br.com.protocoloeletronico.domain.Arquivo;
import br.com.protocoloeletronico.util.FacesUtil;


@ManagedBean
@RequestScoped
public class ArquivosBean {

	private Arquivo arquivoCadastro;

	private List<Arquivo> listaArquivos;
	private List<Arquivo> listaArquivosFiltrados;

	private String acao;
	private Long codigo;

	public Arquivo getArquivoCadastro() {
		return arquivoCadastro;
	}

	public void setArquivoCadastro(Arquivo arquivoCadastro) {
		this.arquivoCadastro = arquivoCadastro;
	}

	public List<Arquivo> getListaArquivos() {
		return listaArquivos;
	}

	public void setListaArquivos(List<Arquivo> listaArquivos) {
		this.listaArquivos = listaArquivos;
	}

	public List<Arquivo> getListaArquivosFiltrados() {
		return listaArquivosFiltrados;
	}

	public void setListaArquivosFiltrados(List<Arquivo> listaArquivosFiltrados) {
		this.listaArquivosFiltrados = listaArquivosFiltrados;
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

	public void novo() {
		arquivoCadastro = new Arquivo();
	}

	public void salvar() {
		try {
			ArquivoDAO adao = new ArquivoDAO();
			adao.salvar(arquivoCadastro);

			FacesUtil.addMsgInfo("O arquivo foi cadastrado com sucesso!");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar o arquivo");
		}
	}

	public void listar() {
		try {
			ArquivoDAO adao = new ArquivoDAO();
			listaArquivos = adao.listar();
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao listar os arquivos");
		}
	}

	public void carregarDados() {
		try {
			if (codigo != null) {
				ArquivoDAO adao = new ArquivoDAO();
				arquivoCadastro = adao.buscarPorCodigo(codigo);
			}else{
				arquivoCadastro = new Arquivo();
			}
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao carregar o arquivo " + ex.getMessage());
		}
	}
	
	public void editar() {
		try {
			ArquivoDAO adao = new ArquivoDAO();
			adao.editar(arquivoCadastro);

			FacesUtil.addMsgInfo("O arquivo foi editado com sucesso!");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao editar o arquivo");
		}
	}
	
	public void excluir() {
		try {
			ArquivoDAO adao = new ArquivoDAO();
			adao.excluir(arquivoCadastro);

			FacesUtil.addMsgInfo("O arquivo foi excluído com sucesso!");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir o arquivo");
		}
	}
	
	
}
