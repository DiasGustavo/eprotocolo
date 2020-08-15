package br.com.gerentedocumento.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.gerentedocumento.dao.EnderecoDAO;
import br.com.gerentedocumento.dao.FuncionarioDAO;
import br.com.gerentedocumento.dao.OrgaoDAO;
import br.com.gerentedocumento.dao.SecretariaDAO;
import br.com.gerentedocumento.domain.Endereco;
import br.com.gerentedocumento.domain.Funcionario;
import br.com.gerentedocumento.domain.Orgao;
import br.com.gerentedocumento.domain.Secretaria;
import br.com.gerentedocumento.util.FacesUtil;

@ManagedBean
@ViewScoped
public class SecretariaBean {

	private Secretaria secretariaCadastro;
	private Endereco enderecoCadastro;
	private Orgao orgaoCadastro;
	private Funcionario funcionarioCadastro;

	private List<Secretaria> listaSecretarias;
	private List<Secretaria> listaSecretariasFiltradas;
	
	private List<Endereco> listaEnderecos;
	private List<Funcionario> listaFuncionarios;
	private List<Orgao> listaOrgaos;

	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;

	private String acao;
	private Long codigo;

	public Secretaria getSecretariaCadastro() {
		if (secretariaCadastro == null) {
			secretariaCadastro = new Secretaria();
		}
		return secretariaCadastro;
	}

	public void setSecretariaCadastro(Secretaria secretariaCadastro) {
		this.secretariaCadastro = secretariaCadastro;
	}

	public List<Secretaria> getListaSecretarias() {
		return listaSecretarias;
	}

	public void setListaSecretarias(List<Secretaria> listaSecretarias) {
		this.listaSecretarias = listaSecretarias;
	}

	public List<Secretaria> getListaSecretariasFiltradas() {
		return listaSecretariasFiltradas;
	}

	public void setListaSecretariasFiltradas(List<Secretaria> listaSecretariasFiltradas) {
		this.listaSecretariasFiltradas = listaSecretariasFiltradas;
	}

	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}

	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
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

	public List<Endereco> getListaEnderecos() {
		return listaEnderecos;
	}

	public void setListaEnderecos(List<Endereco> listaEnderecos) {
		this.listaEnderecos = listaEnderecos;
	}

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public List<Orgao> getListaOrgaos() {
		return listaOrgaos;
	}

	public void setListaOrgaos(List<Orgao> listaOrgaos) {
		this.listaOrgaos = listaOrgaos;
	}

	public void novo() {
		secretariaCadastro = new Secretaria();
	}

	public Endereco getEnderecoCadastro() {
		return enderecoCadastro;
	}

	public void setEnderecoCadastro(Endereco enderecoCadastro) {
		this.enderecoCadastro = enderecoCadastro;
	}

	public Orgao getOrgaoCadastro() {
		return orgaoCadastro;
	}

	public void setOrgaoCadastro(Orgao orgaoCadastro) {
		this.orgaoCadastro = orgaoCadastro;
	}

	public Funcionario getFuncionarioCadastro() {
		return funcionarioCadastro;
	}

	public void setFuncionarioCadastro(Funcionario funcionarioCadastro) {
		this.funcionarioCadastro = funcionarioCadastro;
	}

	public void salvar() {
		try {
			SecretariaDAO sdao = new SecretariaDAO();
			sdao.salvar(secretariaCadastro);

			FacesUtil.addMsgInfo("Secretaria Cadastrada com Sucesso!");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar a Secretaria \n " + ex.getMessage());
		}
	}

	public void listar() {
		try {
			SecretariaDAO sdao = new SecretariaDAO();
			listaSecretarias = sdao.listar();
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu erro ao carregar as Secretarias");
		}
	}

	public void carregarDados(){
		try{
			if(codigo != null){
				SecretariaDAO sdao = new SecretariaDAO();
				secretariaCadastro = sdao.buscarPorCodigo(codigo);
				
				EnderecoDAO edao = new EnderecoDAO();
				enderecoCadastro = edao.buscarPorCodigo(secretariaCadastro.getEndereco().getId());
				
				FuncionarioDAO fdao = new FuncionarioDAO();
				funcionarioCadastro = fdao.buscarPorCodigo(secretariaCadastro.getFuncionario().getId());
				
				OrgaoDAO odao = new OrgaoDAO();
				orgaoCadastro = odao.buscarPorCodigo(secretariaCadastro.getOrgao().getId());
			}else{
				secretariaCadastro = new Secretaria();
				enderecoCadastro = new Endereco();
				funcionarioCadastro = new Funcionario();
				orgaoCadastro = new Orgao();
			}
			EnderecoDAO edao = new EnderecoDAO();
			listaEnderecos = edao.listar();
			
			FuncionarioDAO fdao = new FuncionarioDAO();
			listaFuncionarios = fdao.listar();
			
			OrgaoDAO odao = new OrgaoDAO();
			listaOrgaos = odao.listar();
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro ao carregar os dados da Secretaria " + ex.getMessage());
		}
	}
	
	public void editar() {
		try {
			SecretariaDAO sdao = new SecretariaDAO();
			sdao.editar(secretariaCadastro);

			FacesUtil.addMsgInfo("Secretaria Editada com Sucesso!");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao editar a Secretaria \n " + ex.getMessage());
		}
	}
	
	public String excluir(){
		try{
			SecretariaDAO sdao = new SecretariaDAO();
			sdao.excluir(secretariaCadastro);
			
			FacesUtil.addMsgInfo("Secretaria Excluída com Sucesso!");
			return "/pages/secretaria/secretariaPesquisa.xhtml?faces-redirect=true"; 
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir a Secretaria \n " + ex.getMessage());
			return null;
		}
	}
}
