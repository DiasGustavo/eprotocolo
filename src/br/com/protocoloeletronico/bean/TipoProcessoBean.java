package br.com.protocoloeletronico.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.protocoloeletronico.dao.TipoProcessoDAO;
import br.com.protocoloeletronico.domain.TipoProcesso;
import br.com.protocoloeletronico.util.FacesUtil;

@ManagedBean
@ViewScoped
public class TipoProcessoBean {

	private TipoProcesso tipoProcessoCadastro;
	
	private List<TipoProcesso> listaTipos;
	private List<TipoProcesso> listaTiposFiltrados;
	
	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;
	
	private String acao;
	private Long codigo;
	
	public TipoProcesso getTipoProcessoCadastro() {
		if(tipoProcessoCadastro == null){
			tipoProcessoCadastro = new TipoProcesso();
		}
		return tipoProcessoCadastro;
	}
	public void setTipoProcessoCadastro(TipoProcesso tipoProcessoCadastro) {
		this.tipoProcessoCadastro = tipoProcessoCadastro;
	}
	public List<TipoProcesso> getListaTipos() {
		return listaTipos;
	}
	public void setListaTipos(List<TipoProcesso> listaTipos) {
		this.listaTipos = listaTipos;
	}
	public List<TipoProcesso> getListaTiposFiltrados() {
		return listaTiposFiltrados;
	}
	public void setListaTiposFiltrados(List<TipoProcesso> listaTiposFiltrados) {
		this.listaTiposFiltrados = listaTiposFiltrados;
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
	
	public void novo(){
		tipoProcessoCadastro = new TipoProcesso();
	}
	
	public void salvar(){
		try{
			TipoProcessoDAO tdao = new TipoProcessoDAO();
			tdao.salvar(tipoProcessoCadastro);
			
			FacesUtil.addMsgInfo("Tipo de Processo Cadastrado com Sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao Salvar o Tipo de Processo " + ex.getMessage());
		}
	}
	
	public void listar(){
		try{
			TipoProcessoDAO tdao = new TipoProcessoDAO();
			listaTipos = tdao.listar();
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao Listar os Tipos de Processos " + ex.getMessage());
		}
	}
	
	public void carregarDados(){
		try{
			if(codigo != null){
				TipoProcessoDAO tdao = new TipoProcessoDAO();
				tipoProcessoCadastro = tdao.buscarPorCodigo(codigo);
			}else{
				tipoProcessoCadastro = new TipoProcesso();
			}
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro ao carregar os dados do Tipo do Processo " + ex.getMessage());
		}
	}
	
	public void editar(){
		try{
			TipoProcessoDAO tdao = new TipoProcessoDAO();
			tdao.editar(tipoProcessoCadastro);
			
			FacesUtil.addMsgInfo("Tipo de Processo Editado com Sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao Editar o Tipo de Processo " + ex.getMessage());
		}
	}
	
	public String excluir(){
		try{
			TipoProcessoDAO tdao = new TipoProcessoDAO();
			tdao.excluir(tipoProcessoCadastro);
			
			FacesUtil.addMsgInfo("Tipo de Processo excluído com Sucesso!");
			return "/pages/tipop/tipoPesquisa.xhtml?faces-redirect=true";
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir o Tipo de Processo " + ex.getMessage());
			return null;
		}
	}
}
