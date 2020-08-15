package br.com.gerentedocumento.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.gerentedocumento.dao.FuncionarioDAO;
import br.com.gerentedocumento.dao.OrgaoDAO;
import br.com.gerentedocumento.domain.Funcionario;
import br.com.gerentedocumento.domain.Orgao;
import br.com.gerentedocumento.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FuncionarioBean {

	private Funcionario funCadastro;
	
	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;
	
	private List<Funcionario> listaFuncionarios;
	private List<Funcionario> listaFuncionariosFiltrados;
	private List<Orgao> listaOrgaos;
	
	private String acao;
	private Long codigo;
	
	public Funcionario getFunCadastro() {
		if (funCadastro == null){
			funCadastro = new Funcionario();
		}
		return funCadastro;
	}
	public void setFunCadastro(Funcionario funCadastro) {
		this.funCadastro = funCadastro;
	}
	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}
	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}
	public List<Funcionario> getListaFuncionariosFiltrados() {
		return listaFuncionariosFiltrados;
	}
	public void setListaFuncionariosFiltrados(List<Funcionario> listaFuncionariosFiltrados) {
		this.listaFuncionariosFiltrados = listaFuncionariosFiltrados;
	}	
	public List<Orgao> getListaOrgaos() {
		return listaOrgaos;
	}
	public void setListaOrgaos(List<Orgao> listaOrgaos) {
		this.listaOrgaos = listaOrgaos;
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
	public void novo(){
		funCadastro = new Funcionario();
	}
	
	public void salvar(){
		try{
			FuncionarioDAO fdao = new FuncionarioDAO();
			funCadastro.setSenha(DigestUtils.md5Hex(funCadastro.getSenha()));
			fdao.salvar(funCadastro);
			
			funCadastro = new Funcionario();
			
			FacesUtil.addMsgInfo("Colaborador Cadastrado com Sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao Cadastrar o Colaborador!" + ex.getMessage());
		}
	}
	
	public void listar(){
		try{
			FuncionarioDAO fdao = new FuncionarioDAO();
			listaFuncionarios = fdao.listar();
			
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao listar os Colaboradores!" + ex.getMessage());
		}
	}
	
	public void carregarDados(){
		try{
			if (codigo != null){
				FuncionarioDAO fdao = new FuncionarioDAO();
				funCadastro = fdao.buscarPorCodigo(codigo);
			}else{
				funCadastro = new Funcionario();
			}
			
			FuncionarioDAO fdao = new FuncionarioDAO();
			listaFuncionarios = fdao.listar();
			
			OrgaoDAO odao = new OrgaoDAO();
			listaOrgaos = odao.listar();
			
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro ao carregar os dados do Colaborador" + ex.getMessage());
		}
	}
	
	public void editar(){
		try{
			FuncionarioDAO fdao = new FuncionarioDAO();
			funCadastro.setSenha(DigestUtils.md5Hex(funCadastro.getSenha()));
			fdao.editar(funCadastro);
			
			funCadastro = new Funcionario();
			
			FacesUtil.addMsgInfo("Colaborador editado com Sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro ao editar o Colaborador!" + ex.getMessage());
		}
	}
	
	public String excluir(){
		try{
			FuncionarioDAO fdao = new FuncionarioDAO();
			fdao.excluir(funCadastro);
			
			funCadastro = new Funcionario();
			
			FacesUtil.addMsgInfo("Colaborador excluído com Sucesso!");
			return "/pages/funcionario/funcionarioPesquisa.xhtml?faces-redirect=true";
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro ao excluir o Colaborador!" + ex.getMessage());
			return null;
		}
	}
}
