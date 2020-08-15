package br.com.gerentedocumento.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;


import br.com.gerentedocumento.dao.AtosDAO;
import br.com.gerentedocumento.domain.Atos;
import br.com.gerentedocumento.util.FacesUtil;

@ManagedBean
@ViewScoped
public class AtosBean {

	private Atos atosCadastro;
	
	private List<Atos> listaAtos;
	private List<Atos> listaAtosFiltrados;
	
	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;
	
	private String acao;
	private Long codigo;
	
	public Atos getAtosCadastro() {
		if (atosCadastro == null){
			atosCadastro = new Atos();
		}
		return atosCadastro;
	}
	public void setAtosCadastro(Atos atosCadastro) {
		this.atosCadastro = atosCadastro;
	}
	public List<Atos> getListaAtos() {
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
		atosCadastro = new Atos();
	}
	
	public void salvar(){
		try{
			AtosDAO adao = new AtosDAO();
			adao.salvar(atosCadastro);
			
			FacesUtil.addMsgInfo("Ato Cadastrado com Sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao Salvar o ato " + ex.getMessage());
		}
	}
	
	public void listar(){
		try{
			AtosDAO adao = new AtosDAO();
			listaAtos = adao.listar();
		
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu erro ao carregar os atos");
		}
	}
	
	public void carregarDados(){
		try{
			if (codigo != null){
				AtosDAO adao = new AtosDAO();
				atosCadastro = adao.buscarPorCodigo(codigo);
			}else{
				atosCadastro = new Atos();
			}
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro ao carregar os dados do ato " + ex.getMessage());
		}
	}
	
	public void editar(){
		try{
			AtosDAO adao = new AtosDAO();
			adao.editar(atosCadastro);
			
			FacesUtil.addMsgInfo("Ato editado com Sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao editar o ato " + ex.getMessage());
		}
	}
	
	public String excluir(){
		try{
			AtosDAO adao = new AtosDAO();
			adao.excluir(atosCadastro);
			
			FacesUtil.addMsgInfo("Ato excluído com Sucesso!");
			return "/pages/ato/atoPesquisa.xhtml?faces-redirect=true";
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir o ato " + ex.getMessage());
			return null;
		}
	}
}
