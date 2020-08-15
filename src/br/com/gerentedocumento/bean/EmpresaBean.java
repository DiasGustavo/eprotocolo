package br.com.gerentedocumento.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.gerentedocumento.dao.EmpresaDAO;
import br.com.gerentedocumento.domain.Empresa;
import br.com.gerentedocumento.util.FacesUtil;

@ManagedBean
@ViewScoped
public class EmpresaBean {

	private Empresa empresaCadastro;
	
	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;
	
	private List<Empresa> listaEmpresas;
	private List<Empresa> listaEmpresasFiltradas;
	
	private String acao;
	private Long codigo;
	
	public Empresa getEmpresaCadastro() {
		if (empresaCadastro == null){
			empresaCadastro = new Empresa();
		}
		return empresaCadastro;
	}
	public void setEmpresaCadastro(Empresa empresaCadastro) {
		this.empresaCadastro = empresaCadastro;
	}
	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}
	public void setListaEmpresas(List<Empresa> listaEmpresas) {
		this.listaEmpresas = listaEmpresas;
	}
	public List<Empresa> getListaEmpresasFiltradas() {
		return listaEmpresasFiltradas;
	}
	public void setListaEmpresasFiltradas(List<Empresa> listaEmpresasFiltradas) {
		this.listaEmpresasFiltradas = listaEmpresasFiltradas;
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
	public void novo (){
		empresaCadastro = new Empresa();
	}
	
	public void salvar(){
		try{
			EmpresaDAO edao = new EmpresaDAO();
			edao.salvar(empresaCadastro);
			
			FacesUtil.addMsgInfo("Empresa cadastrada com sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar a Empresa " + ex.getMessage());
		}
	}
	
	public void listar(){
		try{
			EmpresaDAO edao = new EmpresaDAO();
			listaEmpresas = edao.listar();
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu erro ao carregar as Empresas");
		}
	}
	
	public void carregarDados(){
		try{
			if (codigo != null){
				EmpresaDAO edao = new EmpresaDAO();
				empresaCadastro = edao.buscarPorCodigo(codigo);
			}else{
				empresaCadastro = new Empresa();
			}
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro ao carregar os dados da Empresa " + ex.getMessage());
		}
	}
	
	public void editar(){
		try{
			EmpresaDAO edao = new EmpresaDAO();
			edao.editar(empresaCadastro);
			
			FacesUtil.addMsgInfo("Empresa editada com sucesso");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao editar a Empresa " + ex.getMessage());
		}
	}
	
	public String excluir(){
		try{
			EmpresaDAO edao = new EmpresaDAO();
			edao.excluir(empresaCadastro);
			
			codigo = null;
			
			FacesUtil.addMsgInfo("Empresa exlcuída com sucesso");
			return "/pages/empresa/empresaPesquisa.xhtml?faces-redirect=true";
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir a Empresa " + ex.getMessage());
			return null;
		}
	}
	
}
