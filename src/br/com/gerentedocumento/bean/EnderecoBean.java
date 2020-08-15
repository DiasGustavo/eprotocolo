package br.com.gerentedocumento.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.gerentedocumento.dao.EnderecoDAO;
import br.com.gerentedocumento.domain.Endereco;
import br.com.gerentedocumento.util.FacesUtil;



@ManagedBean
@ViewScoped
public class EnderecoBean {

	private Endereco enderecoCadastrado;
	private List<Endereco> listaEnderecos;
	private List<Endereco> listaEnderecosFiltrados;
	
	private String acao;
	private Long codigo;
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Endereco getEnderecoCadastrado() {
		if (enderecoCadastrado == null){
			enderecoCadastrado = new Endereco();
		}
		return enderecoCadastrado;
	}

	public void setEnderecoCadastrado(Endereco enderecoCadastrado) {
		this.enderecoCadastrado = enderecoCadastrado;
	}

	public List<Endereco> getListaEnderecos() {
		return listaEnderecos;
	}

	public void setListaEnderecos(List<Endereco> listaEnderecos) {
		this.listaEnderecos = listaEnderecos;
	}

	public List<Endereco> getListaEnderecosFiltrados() {
		return listaEnderecosFiltrados;
	}

	public void setListaEnderecosFiltrados(List<Endereco> listaEnderecosFiltrados) {
		this.listaEnderecosFiltrados = listaEnderecosFiltrados;
	}

	public void novo() {
		enderecoCadastrado = new Endereco();
	}

	public void salvar() {
		try {
			EnderecoDAO enderecoDAO = new EnderecoDAO();
			enderecoDAO.salvar(enderecoCadastrado);

			enderecoCadastrado = new Endereco();

			FacesUtil.addMsgInfo("Endereco cadastrado com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar o endereco" + ex.getMessage());
		}
	}

	public void listar() {
		try {
			EnderecoDAO enderecoDAO = new EnderecoDAO();
			listaEnderecos = enderecoDAO.listar();

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao carregar os endereços " + ex.getMessage());
		}
	}

	public void carregarDados() {
		try {
			if (codigo != null) {
				EnderecoDAO edao = new EnderecoDAO();
				enderecoCadastrado = edao.buscarPorCodigo(codigo);
			}else{
				enderecoCadastrado = new Endereco();
			}

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao carregar os enderecos" + ex.getMessage());
		}
	}

	public void editar() {
		try {
			EnderecoDAO enderecoDAO = new EnderecoDAO();
			enderecoDAO.editar(enderecoCadastrado);

			enderecoCadastrado = new Endereco();

			FacesUtil.addMsgInfo("Endereco editado com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao editar o endereco" + ex.getMessage());
		}
	}
	
	public void excluir() {
		try {
			EnderecoDAO enderecoDAO = new EnderecoDAO();
			enderecoDAO.excluir(enderecoCadastrado);

			enderecoCadastrado = new Endereco();

			FacesUtil.addMsgInfo("Endereco excluido com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir o endereco" + ex.getMessage());
		}
	}	
}
