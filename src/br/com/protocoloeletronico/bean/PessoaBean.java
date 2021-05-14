package br.com.protocoloeletronico.bean;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.protocoloeletronico.dao.PessoaDAO;
import br.com.protocoloeletronico.domain.Pessoa;
import br.com.protocoloeletronico.util.FacesUtil;

@ManagedBean
@ViewScoped
public class PessoaBean {

	private Pessoa pessoaCadastro;
	
	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;
	
	private List<Pessoa> listaPessoas;
	private List<Pessoa> listaPessoasFiltradas;
	
	private String acao;
	private Long codigo;
	public Pessoa getPessoaCadastro() {
		if(pessoaCadastro == null){
			pessoaCadastro = new Pessoa();
		}
		return pessoaCadastro;
	}
	public void setPessoaCadastro(Pessoa pessoaCadastro) {
		this.pessoaCadastro = pessoaCadastro;
	}
	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}
	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}
	public List<Pessoa> getListaPessoas() {
		return listaPessoas;
	}
	public void setListaPessoas(List<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}
	public List<Pessoa> getListaPessoasFiltradas() {
		return listaPessoasFiltradas;
	}
	public void setListaPessoasFiltradas(List<Pessoa> listaPessoasFiltradas) {
		this.listaPessoasFiltradas = listaPessoasFiltradas;
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
		pessoaCadastro = new Pessoa();
	}
	
	public void salvar(){
		try{
			PessoaDAO pdao = new PessoaDAO();
			pdao.salvar(pessoaCadastro);
			
			FacesUtil.addMsgInfo("Sucesso ao cadastrar a Pessoa");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar a Pessoa " + ex.getMessage());
		}
	}
	
	public void listar(){
		try{
			PessoaDAO pdao = new PessoaDAO();
			listaPessoas = pdao.listar();
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao listar as pessoas!" + ex.getMessage());
		}
	}
	
	public void carregarDados(){
		try{
			if(codigo != null){
				PessoaDAO pdao = new PessoaDAO();
				pessoaCadastro = pdao.buscarPorCodigo(codigo);
			}else{
				pessoaCadastro = new Pessoa();
			}
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro ao carregar os dados da Pessoa " + ex.getMessage());
		}
	}
	
	public void editar(){
		try{
			PessoaDAO pdao = new PessoaDAO();
			pdao.editar(pessoaCadastro);
			
			pessoaCadastro = new Pessoa();
			
			FacesUtil.addMsgInfo("Pessoa editada com Sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro ao editar a Pessoa!" + ex.getMessage());
		}
	}
	
	public String excluir(){
		try{
			PessoaDAO pdao = new PessoaDAO();
			pdao.excluir(pessoaCadastro);
			
			pessoaCadastro = new Pessoa();
			
			FacesUtil.addMsgInfo("Pessoa excluída com Sucesso!");
			return "/pages/pessoa/pessoaPesquisa.xhtml?faces-redirect=true";
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro ao excluir a Pessoa!" + ex.getMessage());
			return null;
		}
	}
}
