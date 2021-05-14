package br.com.protocoloeletronico.bean;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.protocoloeletronico.dao.ArquivoDAO;
import br.com.protocoloeletronico.dao.FuncionarioDAO;
import br.com.protocoloeletronico.dao.ProtocoloDAO;
import br.com.protocoloeletronico.dao.TipoProcessoDAO;
import br.com.protocoloeletronico.domain.Arquivo;
import br.com.protocoloeletronico.domain.Funcionario;
import br.com.protocoloeletronico.domain.Protocolo;
import br.com.protocoloeletronico.domain.TipoProcesso;
import br.com.protocoloeletronico.util.ArquivoUtil;
import br.com.protocoloeletronico.util.DownloadArquivoUtil;
import br.com.protocoloeletronico.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ProtocoloBean {

	private File arquivoCarregado;
	private StreamedContent arquivoVisualizar;
	private Arquivo arquivoCadastro;
	
	private Protocolo protocoloCadastro;
	
	private List<Protocolo> listaProtocolos;
	private List<Protocolo> listaProtocolosFiltrados;
	private List<Funcionario> listaFuncionarios;
	private List<TipoProcesso> listaTiposProcessos;
	private List<Arquivo> listaArquivos;
	private List<Arquivo> listaArquivosTemp;
	private Set<Arquivo> arquivos;
	
	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;
	
	private String acao;
	private Long codigo;
	
	public Protocolo getProtocoloCadastro() {
		if(protocoloCadastro == null){
			protocoloCadastro = new Protocolo();
		}
		return protocoloCadastro;
	}
	public void setProtocoloCadastro(Protocolo protocoloCadastro) {
		this.protocoloCadastro = protocoloCadastro;
	}
	public List<Protocolo> getListaProtocolos() {
		return listaProtocolos;
	}
	public void setListaProtocolos(List<Protocolo> listaProtocolos) {
		this.listaProtocolos = listaProtocolos;
	}
	public List<Protocolo> getListaProtocolosFiltrados() {
		return listaProtocolosFiltrados;
	}
	public void setListaProtocolosFiltrados(List<Protocolo> listaProtocolosFiltrados) {
		this.listaProtocolosFiltrados = listaProtocolosFiltrados;
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
	
	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}
	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}
	
	public List<TipoProcesso> getListaTiposProcessos() {
		return listaTiposProcessos;
	}
	public void setListaTiposProcessos(List<TipoProcesso> listaTiposProcessos) {
		this.listaTiposProcessos = listaTiposProcessos;
	}
	public List<Arquivo> getListaArquivos() {
		return listaArquivos;
	}
	public void setListaArquivos(List<Arquivo> listaArquivos) {
		this.listaArquivos = listaArquivos;
	}
	
	public List<Arquivo> getListaArquivosTemp() {
		return listaArquivosTemp;
	}
	public void setListaArquivosTemp(List<Arquivo> listaArquivosTemp) {
		this.listaArquivosTemp = listaArquivosTemp;
	}
	public File getArquivoCarregado() {
		return arquivoCarregado;
	}
	public void setArquivoCarregado(File arquivoCarregado) {
		this.arquivoCarregado = arquivoCarregado;
	}
	public StreamedContent getArquivoVisualizar() {
		return arquivoVisualizar;
	}
	public void setArquivoVisualizar(StreamedContent arquivoVisualizar) {
		this.arquivoVisualizar = arquivoVisualizar;
	}
	public Arquivo getArquivoCadastro() {
		return arquivoCadastro;
	}
	public void setArquivoCadastro(Arquivo arquivoCadastro) {
		this.arquivoCadastro = arquivoCadastro;
	}
	public Set<Arquivo> getArquivos() {
		if (arquivos == null) {
			arquivos = new HashSet<Arquivo>();
		}
		return arquivos;
	}

	public void setArquivos(Set<Arquivo> arquivos) {
		this.arquivos = arquivos;
	}
	
	public void novo(){
		protocoloCadastro = new Protocolo();
	}
	
	public void salvar(){
		try{
			ProtocoloDAO pdao = new ProtocoloDAO();
			
			
			for (int i =0; i < listaArquivos.size(); i++){
				Arquivo arqaux = (Arquivo)listaArquivos.get(i);
				arqaux.setProtocolo(protocoloCadastro);
				listaArquivos.set(i, arqaux);
			}
			protocoloCadastro.setArquivos(listaArquivos);
			pdao.salvar(protocoloCadastro);
			
			
			
			FacesUtil.addMsgInfo("Protocolo Cadastrado com Sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao Salvar o Protocolo " + ex.getMessage());
		}
	}
	
	public void listar(){
		try{
			ProtocoloDAO pdao = new ProtocoloDAO();
			listaProtocolos = pdao.listar();
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu erro ao carregar os protocolos");
		}
	}
	
	public void carregarDados(){
		try{
			if(codigo != null){
				ProtocoloDAO pdao = new ProtocoloDAO();
				protocoloCadastro = pdao.buscarPorCodigo(codigo);
				
				ArquivoDAO adao = new ArquivoDAO();
				//listaArquivos = adao.buscarPorCodigoProcesso(codigo);
				listaArquivos = adao.buscarListaSetPorCodigo(codigo);
				arquivos = adao.buscarListaArquivosPorCodigo(codigo);
			}else{
				protocoloCadastro = new Protocolo();
				arquivoCadastro = new Arquivo();
				
			}
			FuncionarioDAO fdao = new FuncionarioDAO();
			listaFuncionarios = fdao.listar();
			
			TipoProcessoDAO tdao = new TipoProcessoDAO();
			listaTiposProcessos = tdao.listar();
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro ao carregar os dados do Protocolo " + ex.getMessage());
		}
	}
	
	public void listarResponsavel() {
		try {
			ProtocoloDAO ddao = new ProtocoloDAO();

			if (autenticacaoBean.getFuncionarioLogado().getFuncao().equals("administrador")
					|| autenticacaoBean.getFuncionarioLogado().getFuncao().equals("digitador")) {
				listaProtocolos = ddao.listar();

			} else {
				listaProtocolos = ddao.listarPorResponsavel(autenticacaoBean.getFuncionarioLogado().getId());
			}
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao listar os Protocolos " + ex.getMessage());
		}
	}
	
	public void editar(){
		try{
			protocoloCadastro.setArquivos(listaArquivosTemp);
			
			ProtocoloDAO pdao = new ProtocoloDAO();
			pdao.editar(protocoloCadastro);
			
			FacesUtil.addMsgInfo("Protocolo Editado com Sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao Editar o Protocolo " + ex.getMessage());
		}
	}
	
	public String excluir(){
		try{
			ProtocoloDAO pdao = new ProtocoloDAO();
			pdao.excluir(protocoloCadastro);
			
			FacesUtil.addMsgInfo("Protocolo excluído com Sucesso!");
			return "/pages/protocolo/protocoloPesquisa.xhtml?faces-redirect=true";
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir o protocolo " + ex.getMessage());
			return null;
		}
	}
	
	public void upload(FileUploadEvent event) {
		UploadedFile arquivo = event.getFile();
		ArquivoUtil carregamento = new ArquivoUtil();

		try {
			arquivoCarregado = carregamento.escrever(arquivo.getFileName(), arquivo.getContents());
			adicionarArquivo(arquivoCarregado);

		} catch (IOException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro no upload dos arquivos " + ex.getMessage());
		}
	}

	public void adicionarArquivo(File arquivoTemp) {
		
		if (arquivoCadastro == null) {
			arquivoCadastro = new Arquivo();
			arquivoCadastro.setNome(arquivoTemp.getName());
			arquivoCadastro.setCaminho(arquivoTemp.getPath());
		} else {
			arquivoCadastro.setNome(arquivoTemp.getName());
			arquivoCadastro.setCaminho(arquivoTemp.getPath());
		}
		/*if (arquivos == null) {
			arquivos = new HashSet<Arquivo>();
		}
		//arquivoCadastro.setId((long) arquivos.size() + 1);
		
		arquivoCadastro = new Arquivo();*/
		//arquivos.add(arquivoCadastro);
		if(listaArquivos == null){
			listaArquivos = new ArrayList<Arquivo>();
		}
		listaArquivos.add(arquivoCadastro);
		
		if (acao.equals("editar")){
			arquivoCadastro.setProtocolo(protocoloCadastro);
			ArquivoDAO ardao = new ArquivoDAO();
			ardao.salvar(arquivoCadastro);
		}
		arquivoCadastro = new Arquivo();
		
		
	}

	public void removerArquivo(Arquivo arquivo) {
		for(Iterator<Arquivo> i = arquivos.iterator(); i.hasNext();){
			Arquivo arq = i.next();
			if(arq.equals(arquivo)){
				ArquivoDAO arqdao = new ArquivoDAO();
				arqdao.excluir(arquivo);
			}
		}
	}
	/**
	 * Realiza o Download do arquivo selecionado
	 * @param arquivoTemp
	 */
	public void visualizarArquivo(Arquivo arquivoTemp){
		for(Iterator<Arquivo> i = arquivos.iterator(); i.hasNext();){
			Arquivo arq = i.next();
			if(arq.equals(arquivoTemp)){
				DownloadArquivoUtil downloadArquivo = new DownloadArquivoUtil();
				arquivoVisualizar = downloadArquivo.visualizarArquivo(arquivoTemp.getCaminho(),
						"application/pdf");
			}
		}
	}
}
