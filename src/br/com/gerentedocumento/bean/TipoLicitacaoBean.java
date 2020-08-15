package br.com.gerentedocumento.bean;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.gerentedocumento.dao.TipoLicitacaoDAO;
import br.com.gerentedocumento.domain.TipoLicitacao;
import br.com.gerentedocumento.util.FacesUtil;

@ManagedBean
@ViewScoped
public class TipoLicitacaoBean {

	private TipoLicitacao tipoCadastro;
	
	private List<TipoLicitacao> listaTipos;
	private List<TipoLicitacao> listaTiposSelecionados;
	
	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;
	
	private String acao;
	private Long codigo;
	public TipoLicitacao getTipoCadastro() {
		if(tipoCadastro == null){
			tipoCadastro = new TipoLicitacao();
		}
		return tipoCadastro;
	}
	public void setTipoCadastro(TipoLicitacao tipoCadastro) {
		this.tipoCadastro = tipoCadastro;
	}
	public List<TipoLicitacao> getListaTipos() {
		return listaTipos;
	}
	public void setListaTipos(List<TipoLicitacao> listaTipos) {
		this.listaTipos = listaTipos;
	}
	public List<TipoLicitacao> getListaTiposSelecionados() {
		return listaTiposSelecionados;
	}
	public void setListaTiposSelecionados(List<TipoLicitacao> listaTiposSelecionados) {
		this.listaTiposSelecionados = listaTiposSelecionados;
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
		tipoCadastro = new TipoLicitacao();
	}
	
	public void salvar(){
		try{
			TipoLicitacaoDAO tldao = new TipoLicitacaoDAO();
			tipoCadastro.setDataCadastro(getPegaDataAtual());
			tipoCadastro.setDataModificacao(getPegaDataAtual());
			tipoCadastro.setCriador(autenticacaoBean.getFuncionarioLogado());
			tldao.salvar(tipoCadastro);
			
			FacesUtil.addMsgInfo("Tipo Cadastrado com Sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao Salvar o tipo " + ex.getMessage());
		}
	}
	
	public void listar(){
		try{
			TipoLicitacaoDAO tldao = new TipoLicitacaoDAO();
			listaTipos = tldao.listar();
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu erro ao carregar os tipos");
		}
	}
	
	public void carregarDados(){
		try{
			if(codigo != null){
				TipoLicitacaoDAO tldao = new TipoLicitacaoDAO();
				tipoCadastro = tldao.buscarPorCodigo(codigo);
			}else{
				tipoCadastro = new TipoLicitacao();
			}
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro ao carregar os dados do tipo " + ex.getMessage());
		}
	}
	
	public void editar(){
		try{
			TipoLicitacaoDAO tldao = new TipoLicitacaoDAO();
			tipoCadastro.setDataModificacao(getPegaDataAtual());
			tipoCadastro.setCriador(autenticacaoBean.getFuncionarioLogado());
			tldao.editar(tipoCadastro);
			
			FacesUtil.addMsgInfo("Tipo editado com Sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao editar o tipo " + ex.getMessage());
		}
	}
	
	public String excluir(){
		try{
			TipoLicitacaoDAO tldao = new TipoLicitacaoDAO();
			tldao.excluir(tipoCadastro);
			
			FacesUtil.addMsgInfo("Tipo excluído com Sucesso!");
			return "/pages/ato/tipolicitacaoPesquisa.xhtml?faces-redirect=true";
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir o tipo " + ex.getMessage());
			return null;
		}
	}
	
	@SuppressWarnings("deprecation")
	private Date getPegaDataAtual() {
		Date date = new Date();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);

		return date;
	}
}
