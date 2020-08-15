package br.com.gerentedocumento.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.gerentedocumento.dao.EnderecoDAO;
import br.com.gerentedocumento.dao.OrgaoDAO;
import br.com.gerentedocumento.domain.Endereco;
import br.com.gerentedocumento.domain.Orgao;
import br.com.gerentedocumento.util.FacesUtil;

@ManagedBean
@ViewScoped
public class OrgaoBean {

	private Orgao orgaoCadastro;
	private Endereco enderecoCadastro;
	
	private List<Orgao> listaOrgaos;
	private List<Orgao> listaOrgaosFiltrados;
	
	private List<Endereco> listaEnderecos;
	
	
	private String acao;
	private Long codigo;
	
	public Orgao getOrgaoCadastro() {
		if (orgaoCadastro == null){
			orgaoCadastro = new Orgao();
		}
		return orgaoCadastro;
	}
	public void setOrgaoCadastro(Orgao orgaoCadastro) {
		this.orgaoCadastro = orgaoCadastro;
	}
	public List<Orgao> getListaOrgaos() {
		return listaOrgaos;
	}
	public void setListaOrgaos(List<Orgao> listaOrgaos) {
		this.listaOrgaos = listaOrgaos;
	}
	public List<Orgao> getListaOrgaosFiltrados() {
		return listaOrgaosFiltrados;
	}
	public void setListaOrgaosFiltrados(List<Orgao> listaOrgaosFiltrados) {
		this.listaOrgaosFiltrados = listaOrgaosFiltrados;
	}
	public List<Endereco> getListaEnderecos() {
		return listaEnderecos;
	}
	public void setListaEnderecos(List<Endereco> listaEnderecos) {
		this.listaEnderecos = listaEnderecos;
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
	
	public void novo(){
		orgaoCadastro = new Orgao();
	}
	
	public void salvar(){
		try{
			if (orgaoCadastro.getEndereco() == null) {
				EnderecoDAO edao = new EnderecoDAO();
				Long codigoEndereco = edao.salvar(enderecoCadastro);
				Endereco enderecoFK = edao.buscarPorCodigo(codigoEndereco);
				orgaoCadastro.setEndereco(enderecoFK);
			}
			OrgaoDAO odao = new OrgaoDAO();
			//gerar o registro md5 a partir do cnpj e da data de vigencia para o orgão.
			orgaoCadastro.setRegistro(DigestUtils.md5Hex(orgaoCadastro.getCnpj()+ orgaoCadastro.getNome()));
			odao.salvar(orgaoCadastro);

			orgaoCadastro = new Orgao();
			enderecoCadastro = new Endereco();
			
			FacesUtil.addMsgInfo("Orgão Cadastrado com Sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro ao cadastrar o Orgao " + ex.getMessage());
		}
	}
	
	public void listar(){
		try{
			OrgaoDAO odao = new OrgaoDAO();
			listaOrgaos = odao.listar();
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu erro ao carregar os orgãos " + ex.getMessage());
		}
	}
	
	public void carregarDados(){
		try {
			if (codigo != null) {
				OrgaoDAO odao = new OrgaoDAO();
				orgaoCadastro = odao.buscarPorCodigo(codigo);

				EnderecoDAO edao = new EnderecoDAO();
				enderecoCadastro = edao.buscarPorCodigo(orgaoCadastro.getEndereco().getId());
			} else {
				orgaoCadastro = new Orgao();
				enderecoCadastro = new Endereco();
			}
			
			EnderecoDAO edao = new EnderecoDAO();
			listaEnderecos = edao.listar();
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao carregar os dados Orgão " + ex.getMessage());
		}
	}
	
	public void editar(){
		try{
			OrgaoDAO odao = new OrgaoDAO();
			//gerar o registro md5 a partir do cnpj e da data de vigencia para o orgão.
			orgaoCadastro.setRegistro(DigestUtils.md5Hex(orgaoCadastro.getCnpj()+ orgaoCadastro.getNome()));
			odao.editar(orgaoCadastro);
			
			EnderecoDAO edao = new EnderecoDAO();
			edao.editar(enderecoCadastro);
			
			orgaoCadastro = new Orgao();
			enderecoCadastro = new Endereco();
			
			FacesUtil.addMsgInfo("Orgão editado com sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao editar o Orgão " + ex.getMessage());
		}
	}
	
	public String excluir(){
		try{
			OrgaoDAO odao = new OrgaoDAO();
			odao.excluir(orgaoCadastro);
			
			codigo = null;
			
			FacesUtil.addMsgInfo("Orgão excluído com sucesso!");
			return "/pages/orgao/orgaoPesquisa.xhtml?faces-redirect=true";
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir o Orgão " + ex.getMessage());
			return null;
		}
	}
}
