package br.com.gerentedocumento.bean;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.gerentedocumento.dao.EmpresaDAO;
import br.com.gerentedocumento.dao.LicitacaoDAO;
import br.com.gerentedocumento.dao.ModalidadeLicitacaoDAO;
import br.com.gerentedocumento.dao.TipoLicitacaoDAO;
import br.com.gerentedocumento.domain.Empresa;
import br.com.gerentedocumento.domain.Licitacao;
import br.com.gerentedocumento.domain.ModalidadeLicitacao;
import br.com.gerentedocumento.domain.TipoLicitacao;
import br.com.gerentedocumento.util.FacesUtil;

@ManagedBean
@ViewScoped
public class LicitacaoBean {

	private Licitacao licitacaoCadastro;
	
	private List<Licitacao> listaLicitacoes;
	private List<Licitacao> listaLicitacoesFiltradas;
	private List<TipoLicitacao> tiposLicitacao;
	private List<ModalidadeLicitacao> modalidadesLicitacao;
	private List<Empresa> listaEmpresas;
	
	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;
	
	private String acao;
	private Long codigo;
	
	public Licitacao getLicitacaoCadastro() {
		if(licitacaoCadastro == null){
			licitacaoCadastro = new Licitacao();
		}
		return licitacaoCadastro;
	}
	public void setLicitacaoCadastro(Licitacao licitacaoCadastro) {
		this.licitacaoCadastro = licitacaoCadastro;
	}
	public List<Licitacao> getListaLicitacoes() {
		return listaLicitacoes;
	}
	public void setListaLicitacoes(List<Licitacao> listaLicitacoes) {
		this.listaLicitacoes = listaLicitacoes;
	}
	public List<Licitacao> getListaLicitacoesFiltradas() {
		return listaLicitacoesFiltradas;
	}
	public void setListaLicitacoesFiltradas(List<Licitacao> listaLicitacoesFiltradas) {
		this.listaLicitacoesFiltradas = listaLicitacoesFiltradas;
	}
	public List<TipoLicitacao> getTiposLicitacao() {
		return tiposLicitacao;
	}
	public void setTiposLicitacao(List<TipoLicitacao> tiposLicitacao) {
		this.tiposLicitacao = tiposLicitacao;
	}
	public List<ModalidadeLicitacao> getModalidadesLicitacao() {
		return modalidadesLicitacao;
	}
	public void setModalidadesLicitacao(List<ModalidadeLicitacao> modalidadesLicitacao) {
		this.modalidadesLicitacao = modalidadesLicitacao;
	}
	
	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}
	public void setListaEmpresas(List<Empresa> listaEmpresas) {
		this.listaEmpresas = listaEmpresas;
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
		licitacaoCadastro = new Licitacao();
	}
	
	public void salvar(){
		try{
			LicitacaoDAO ldao = new LicitacaoDAO();
			licitacaoCadastro.setDataCadastro(getPegaDataAtual());
			licitacaoCadastro.setDataModificacao(getPegaDataAtual());
			licitacaoCadastro.setCriador(autenticacaoBean.getFuncionarioLogado());
			ldao.salvar(licitacaoCadastro);
			
			FacesUtil.addMsgInfo("Licitação Cadastrada com Sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao Salvar a Licitação " + ex.getMessage());
		}
	}
	
	public void listar(){
		try{
			LicitacaoDAO ldao = new LicitacaoDAO();
			listaLicitacoes = ldao.listar();
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu erro ao carregar as Licitações");
		}
	}
	
	public void carregarDados(){
		try{
			if(codigo != null){
				LicitacaoDAO ldao = new LicitacaoDAO();
				licitacaoCadastro = ldao.buscarPorCodigo(codigo);
			}else{
				licitacaoCadastro = new Licitacao();
			}
			TipoLicitacaoDAO tldao = new TipoLicitacaoDAO();
			tiposLicitacao = tldao.buscarPorStatus("Aplicar");
			
			ModalidadeLicitacaoDAO mldao = new ModalidadeLicitacaoDAO();
			modalidadesLicitacao = mldao.buscarPorStatus("Aplicar");
			
			EmpresaDAO edao = new EmpresaDAO();
			listaEmpresas = edao.listar();
			
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro ao carregar os dados do tipo " + ex.getMessage());
		}
	}
	
	public void editar(){
		try{
			LicitacaoDAO ldao = new LicitacaoDAO();
			licitacaoCadastro.setDataModificacao(getPegaDataAtual());
			licitacaoCadastro.setCriador(autenticacaoBean.getFuncionarioLogado());
			ldao.editar(licitacaoCadastro);
			
			FacesUtil.addMsgInfo("Licitação Editada com Sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao Editar a Licitação " + ex.getMessage());
		}
	}
	
	public void excluir(){
		try{
			LicitacaoDAO ldao = new LicitacaoDAO();
			ldao.excluir(licitacaoCadastro);
			
			FacesUtil.addMsgInfo("Licitação Excluída com Sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao Excluir a Licitação " + ex.getMessage());
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
