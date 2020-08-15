package br.com.gerentedocumento.bean;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.gerentedocumento.dao.ModalidadeLicitacaoDAO;
import br.com.gerentedocumento.domain.ModalidadeLicitacao;
import br.com.gerentedocumento.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ModalidadeLicitacaoBean {

	private ModalidadeLicitacao modalidadeCadastro;
	
	private List<ModalidadeLicitacao> listaModalidades;
	private List<ModalidadeLicitacao> listaModalidadesFiltradas;
	
	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;
	
	private String acao;
	private Long codigo;
	
	public ModalidadeLicitacao getModalidadeCadastro() {
		if(modalidadeCadastro == null){
			modalidadeCadastro = new ModalidadeLicitacao();
		}
		return modalidadeCadastro;
	}
	public void setModalidadeCadastro(ModalidadeLicitacao modalidadeCadastro) {
		this.modalidadeCadastro = modalidadeCadastro;
	}
	public List<ModalidadeLicitacao> getListaModalidades() {
		return listaModalidades;
	}
	public void setListaModalidades(List<ModalidadeLicitacao> listaModalidades) {
		this.listaModalidades = listaModalidades;
	}
	public List<ModalidadeLicitacao> getListaModalidadesFiltradas() {
		return listaModalidadesFiltradas;
	}
	public void setListaModalidadesFiltradas(List<ModalidadeLicitacao> listaModalidadesFiltradas) {
		this.listaModalidadesFiltradas = listaModalidadesFiltradas;
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
		modalidadeCadastro = new ModalidadeLicitacao();
	}
	
	public void salvar(){
		try{
			ModalidadeLicitacaoDAO mldao = new ModalidadeLicitacaoDAO();
			modalidadeCadastro.setDataCadastro(getPegaDataAtual());
			modalidadeCadastro.setDataModificacao(getPegaDataAtual());
			modalidadeCadastro.setCriador(autenticacaoBean.getFuncionarioLogado());
			mldao.salvar(modalidadeCadastro);
			
			FacesUtil.addMsgInfo("Modalidade cadastrada com sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar a modalidade!" + ex.getMessage());
		}
	}
	
	public void listar(){
		try{
			ModalidadeLicitacaoDAO mldao = new ModalidadeLicitacaoDAO();
			listaModalidades = mldao.listar();
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu erro ao carregar as modalidade!");
		}
	}
	
	public void carregarDados(){
		try{
			if(codigo != null){
				ModalidadeLicitacaoDAO mldao = new ModalidadeLicitacaoDAO();
				modalidadeCadastro = mldao.buscarPorCodigo(codigo);
			}else{
				modalidadeCadastro = new ModalidadeLicitacao();
			}
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro ao carregar os dados da modalidade " + ex.getMessage());
		}
	}
	
	public void editar(){
		try{
			ModalidadeLicitacaoDAO mldao = new ModalidadeLicitacaoDAO();
			modalidadeCadastro.setDataModificacao(getPegaDataAtual());
			modalidadeCadastro.setCriador(autenticacaoBean.getFuncionarioLogado());
			mldao.editar(modalidadeCadastro);
			
			FacesUtil.addMsgInfo("Modalidade editada com Sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao editar a modalidade " + ex.getMessage());
		}
	}
	
	public String excluir(){
		try{
			ModalidadeLicitacaoDAO mldao = new ModalidadeLicitacaoDAO();
			mldao.excluir(modalidadeCadastro);
			
			FacesUtil.addMsgInfo("Modalidade excluída com Sucesso!");
			return "/pages/ato/tipolicitacaoPesquisa.xhtml?faces-redirect=true";
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir a modalidade " + ex.getMessage());
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
