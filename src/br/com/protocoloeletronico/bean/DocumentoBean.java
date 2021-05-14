package br.com.protocoloeletronico.bean;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.protocoloeletronico.dao.CadastraDocumentoDAO;
import br.com.protocoloeletronico.dao.DocumentoDAO;
import br.com.protocoloeletronico.dao.EmpresaDAO;
import br.com.protocoloeletronico.dao.FuncionarioDAO;
import br.com.protocoloeletronico.dao.NotaEmpenhoDAO;
import br.com.protocoloeletronico.dao.SecretariaDAO;
import br.com.protocoloeletronico.domain.Arquivo;
import br.com.protocoloeletronico.domain.CadastraDocumento;
import br.com.protocoloeletronico.domain.Documento;
import br.com.protocoloeletronico.domain.Email;
import br.com.protocoloeletronico.domain.Empresa;
import br.com.protocoloeletronico.domain.Funcionario;
import br.com.protocoloeletronico.domain.NotaEmpenho;
import br.com.protocoloeletronico.domain.Orgao;
import br.com.protocoloeletronico.domain.Secretaria;
import br.com.protocoloeletronico.util.ArquivoUtil;
import br.com.protocoloeletronico.util.DownloadArquivoUtil;
import br.com.protocoloeletronico.util.EmailUtil;
import br.com.protocoloeletronico.util.FacesUtil;
import br.com.protocoloeletronico.util.UploadArquivoUtil;

@ManagedBean
@ViewScoped
public class DocumentoBean {

	private Documento docCadastro;
	private CadastraDocumento cadDocumentoCadastro;

	private List<Documento> listaDocumentos;
	private List<Documento> listaDocumentosFiltrados;
	private List<Funcionario> listaFuncionarios;
	private List<Empresa> listaEmpresas;
	private List<NotaEmpenho> listaEmpenhos;
	private List<Secretaria> listaSecretarias;
	private Set<Arquivo> arquivos;

	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;

	private UploadedFile arquivoUpload;
	private StreamedContent arquivoVisualizar;

	private String acao;
	private Long codigo;
	private Boolean exibirCD = false;
	private Boolean exibirIT = false;
	private String opcao;
	
	
	
	public String getOpcao() {
		return opcao;
	}

	public void setOpcao(String opcao) {
		this.opcao = opcao;
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
	
	public Boolean getExibirCD() {
		return exibirCD;
	}

	public void setExibirCD(Boolean exibir) {
		this.exibirCD = exibir;
	}

	public Boolean getExibirIT() {
		return exibirIT;
	}

	public void setExibirIT(Boolean exibirIT) {
		this.exibirIT = exibirIT;
	}

	public Documento getDocCadastro() {
		if (docCadastro == null) {
			docCadastro = new Documento();
		}
		return docCadastro;
	}

	public void setDocCadastro(Documento docCadastro) {
		this.docCadastro = docCadastro;
	}

	public CadastraDocumento getCadDocumentoCadastro() {
		return cadDocumentoCadastro;
	}

	public void setCadDocumentoCadastro(CadastraDocumento cadDocumentoCadastro) {
		this.cadDocumentoCadastro = cadDocumentoCadastro;
	}

	public List<Documento> getListaDocumentos() {
		return listaDocumentos;
	}

	public void setListaDocumentos(List<Documento> listaDocumentos) {
		this.listaDocumentos = listaDocumentos;
	}

	public List<Documento> getListaDocumentosFiltrados() {
		return listaDocumentosFiltrados;
	}

	public void setListaDocumentosFiltrados(List<Documento> listaDocumentosFiltrados) {
		this.listaDocumentosFiltrados = listaDocumentosFiltrados;
	}

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

	public void setListaEmpresas(List<Empresa> listaEmpresas) {
		this.listaEmpresas = listaEmpresas;
	}

	public List<NotaEmpenho> getListaEmpenhos() {
		return listaEmpenhos;
	}

	public void setListaEmpenhos(List<NotaEmpenho> listaEmpenhos) {
		this.listaEmpenhos = listaEmpenhos;
	}

	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}

	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}

	public UploadedFile getArquivoCarregado() {
		return arquivoUpload;
	}

	public void setArquivoCarregado(UploadedFile arquivoCarregado) {
		this.arquivoUpload = arquivoCarregado;
	}

	public StreamedContent getArquivoVisualizar() {
		return arquivoVisualizar;
	}

	public void setArquivoVisualizar(StreamedContent arquivoVisualizar) {
		this.arquivoVisualizar = arquivoVisualizar;
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

	public List<Secretaria> getListaSecretarias() {
		return listaSecretarias;
	}

	public void setListaSecretarias(List<Secretaria> listaSecretarias) {
		this.listaSecretarias = listaSecretarias;
	}

	public void novo() {
		docCadastro = new Documento();
	}

	public void salvar() {
		try {
			DocumentoDAO ddao = new DocumentoDAO();

			if (!(arquivoUpload.getFileName().isEmpty())) {
				upload();
				ddao.salvar(docCadastro);

				// captura as informa��es do documento cadastrado
				Documento doc = ddao.buscarPorProcessoSecretaria(docCadastro.getId(), docCadastro.getProcesso(),
						docCadastro.getSecretaria().getId());
				CadastraDocumento cadastra = new CadastraDocumento();
				cadastra.setCodDocumento(doc.getId());
				cadastra.setCodFuncionario(autenticacaoBean.getFuncionarioLogado().getId());
				cadastra.setDataCadastro(new Date());

				// cadastra o log do cadastro
				CadastraDocumentoDAO cddao = new CadastraDocumentoDAO();
				cddao.salvar(cadastra);
				// EmailUtil.sendMessage("gustavouepb@gmail.com", "Cadastro",
				// "documento Cadastrado");
				String mensagem = this.mensagem(autenticacaoBean.getOrgaoLogado(), docCadastro, docCadastro.getStatus());
				this.enviarEmail(autenticacaoBean.getFuncionarioLogado().getOrgao().getEmail(),
						docCadastro.getEmpresa().getEmail(),
						"Processo no �rg�o " + autenticacaoBean.getFuncionarioLogado().getOrgao().getNome(), mensagem,
						autenticacaoBean.getFuncionarioLogado().getOrgao().getTokenAcesso(),
						autenticacaoBean.getFuncionarioLogado().getOrgao());
				docCadastro = new Documento();

				FacesUtil.addMsgInfo("Documento cadastrado com Sucesso!");
			} else {
				ddao.salvar(docCadastro);

				// captura as informa��es do documento cadastrado
				Documento doc = ddao.buscarPorProcessoSecretaria(docCadastro.getId(), docCadastro.getProcesso(),
						docCadastro.getSecretaria().getId());
				CadastraDocumento cadastra = new CadastraDocumento();
				cadastra.setCodDocumento(doc.getId());
				cadastra.setCodFuncionario(autenticacaoBean.getFuncionarioLogado().getId());
				cadastra.setDataCadastro(new Date());

				// cadastra o log do cadastro
				CadastraDocumentoDAO cddao = new CadastraDocumentoDAO();
				cddao.salvar(cadastra);
				String mensagem = this.mensagem(autenticacaoBean.getOrgaoLogado(), docCadastro, docCadastro.getStatus());
				this.enviarEmail(autenticacaoBean.getFuncionarioLogado().getOrgao().getEmail(),
						docCadastro.getEmpresa().getEmail(),
						"Processo no �rg�o " + autenticacaoBean.getFuncionarioLogado().getOrgao().getNome(), mensagem,
						autenticacaoBean.getFuncionarioLogado().getOrgao().getTokenAcesso(),
						autenticacaoBean.getFuncionarioLogado().getOrgao());
				docCadastro = new Documento();

				FacesUtil.addMsgInfo("Documento cadastrado com Sucesso!");
			}
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao cadastrar o Documento! " + ex.getMessage());
		}
	}

	public void listar() {
		try {
			DocumentoDAO ddao = new DocumentoDAO();

			if (autenticacaoBean.getFuncionarioLogado().getFuncao().equals("administrador")
					|| autenticacaoBean.getFuncionarioLogado().getFuncao().equals("digitador")) {
				listaDocumentos = ddao.listar();

			} else {
				listaDocumentos = ddao.listarPorResponsavel(autenticacaoBean.getFuncionarioLogado().getId());
			}
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao listar os Documentos " + ex.getMessage());
		}
	}

	public void listarNotasEmpenho() {
		try {
			NotaEmpenhoDAO nedao = new NotaEmpenhoDAO();
			listaEmpenhos = nedao.buscarPorDocumento(codigo);
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao listar as Notas de Empenho " + ex.getMessage());
		}
	}

	public void carregarDados() {
		try {
			
			if (codigo != null) {
				DocumentoDAO ddao = new DocumentoDAO();
				docCadastro = ddao.buscarPorCodigo(codigo);
				NotaEmpenhoDAO nedao = new NotaEmpenhoDAO();
				listaEmpenhos = nedao.buscarPorDocumento(codigo);
				// carregar o arquivo armazenado
				if (docCadastro.getDocArquivo() != null) {
					DownloadArquivoUtil downloadArquivo = new DownloadArquivoUtil();
					arquivoVisualizar = downloadArquivo.visualizarArquivo(docCadastro.getDocArquivo(),
							"application/pdf");
				}
			} else {
				docCadastro = new Documento();
				docCadastro.setDataEntrada(new Date());
			}
			DocumentoDAO ddao = new DocumentoDAO();
			listaDocumentos = ddao.listar();

			FuncionarioDAO fdao = new FuncionarioDAO();
			listaFuncionarios = fdao.listar();

			EmpresaDAO edao = new EmpresaDAO();
			listaEmpresas = edao.listar();
			
			SecretariaDAO sdao = new SecretariaDAO();
			listaSecretarias = sdao.listar();

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao carregar os dados do Documento! " + ex.getMessage());
		}
	}

	public void editar() {
		try {
			DocumentoDAO ddao = new DocumentoDAO();
			if (arquivoUpload == null) {
				ddao.editar(docCadastro);

				// captura o log correto e atualiza
				CadastraDocumentoDAO cddao = new CadastraDocumentoDAO();
				CadastraDocumento cadastra = cddao.buscarPorCodigoDocumento(docCadastro.getId());
				cadastra.setCodFuncionario(autenticacaoBean.getFuncionarioLogado().getId());
				cadastra.setDataCadastro(new Date());
				cddao.editar(cadastra);

				docCadastro = new Documento();
				arquivoUpload = null;

				FacesUtil.addMsgInfo("Documento editado com Sucesso!");
			} else {

				if (arquivoUpload.getSize() > 0 && docCadastro.getDocArquivo() == null) {
					upload();
					ddao.editar(docCadastro);

					// captura o log correto e atualiza
					CadastraDocumentoDAO cddao = new CadastraDocumentoDAO();
					CadastraDocumento cadastra = cddao.buscarPorCodigoDocumento(docCadastro.getId());
					cadastra.setCodFuncionario(autenticacaoBean.getFuncionarioLogado().getId());
					cadastra.setDataCadastro(new Date());
					cddao.editar(cadastra);

					docCadastro = new Documento();
					arquivoUpload = null;

					FacesUtil.addMsgInfo("Documento editado com Sucesso!");
				} else {
					ddao.editar(docCadastro);

					// captura o log correto e atualiza
					CadastraDocumentoDAO cddao = new CadastraDocumentoDAO();
					CadastraDocumento cadastra = cddao.buscarPorCodigoDocumento(docCadastro.getId());
					cadastra.setCodFuncionario(autenticacaoBean.getFuncionarioLogado().getId());
					cadastra.setDataCadastro(new Date());
					cddao.editar(cadastra);

					docCadastro = new Documento();
					arquivoUpload = null;

					FacesUtil.addMsgInfo("Documento editado com Sucesso!");
				}
			}
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao editar o Documento! " + ex.getMessage());
		}
	}

	public String excluir() {
		try {
			DocumentoDAO ddao = new DocumentoDAO();
			ddao.excluir(docCadastro);

			docCadastro = new Documento();

			FacesUtil.addMsgInfo("Documento exclu�do com Sucesso!");
			return "/pages/documento/documentoPesquisa.xhtml?faces-redirect=true";
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao excluir o Documento! " + ex.getMessage());
			return null;
		}
	}

	/*
	 * public void upload() { UploadArquivoUtil upload = new
	 * UploadArquivoUtil(); OrgaoDAO odao = new OrgaoDAO();
	 * 
	 * Orgao orgao =
	 * odao.buscarPorCodigo(autenticacaoBean.getOrgaoLogado().getId()); try {
	 * docCadastro.setDocArquivo(upload.upload(orgao.getRepositorio(),
	 * arquivoUpload.getFileName(), arquivoUpload.getInputstream()));
	 * 
	 * } catch (IOException e) { FacesUtil.addMsgErro(
	 * "Erro ao fazer upload do arquivo! " + e.getMessage()); } }
	 */

	public void upload() {
		ArquivoUtil arquivo = new ArquivoUtil();
		try {
			File upload = arquivo.escrever(arquivoUpload.getFileName(), arquivoUpload.getContents());
			docCadastro.setDocArquivo(upload.getPath());
			FacesUtil.addMsgInfo("Upload do arquivo:  " + upload.getName() + " realizado com sucesso!");
		} catch (IOException e) {
			FacesUtil.addMsgErro("Erro ao fazer upload do arquivo! " + e.getMessage());
		}
	}

	public void deletarArquivo() {
		try {
			UploadArquivoUtil upload = new UploadArquivoUtil();
			upload.removerArquivo(this.docCadastro.getDocArquivo());
			this.docCadastro.setDocArquivo(null);
			DocumentoDAO ddao = new DocumentoDAO();
			arquivoVisualizar = null;
			ddao.editar(docCadastro);

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao excluir o arquivo! " + ex.getMessage());
		}
	}

	private String mensagem(Orgao orgao, Documento documento, String status) {
		String message = "Mensagem instant�nea do processo no �rg�o "
				+ autenticacaoBean.getFuncionarioLogado().getOrgao().getNome() + "<p>Processo de n�mero: "
				+ documento.getId() + " no �rg�o " + autenticacaoBean.getFuncionarioLogado().getOrgao().getNome()
				+ "</p> </br>" + "Situa��o: " + status + "</br> Data: " + new Date() + "</br> contato: </br> E-mail: "
				+ orgao.getEmail() + "</br> Telefone: " + orgao.getTelefone();

		return message;
	}

	/**
	 * Envia um email
	 * 
	 * @param remetente
	 * @param destinatario
	 * @param assunto
	 * @param mensagem
	 * @param token
	 * @param orgao
	 */
	private void enviarEmail(String remetente, String destinatario, String assunto, String mensagem, String token,
			Orgao orgao) {
		EmailUtil enviarEmail = new EmailUtil();
		// configurando o email para enviar
		Email emailAntes = new Email();
		emailAntes.setFormEmail(remetente);
		emailAntes.setToEmail(destinatario);
		emailAntes.setMessage(mensagem);
		emailAntes.setSubject(assunto);
		enviarEmail.setEmail(emailAntes);
		// captura novo token
		String newToken;
		try {
			newToken = enviarEmail.getNewToken(token, orgao.getClientId(), orgao.getClientSecret());

			// atualiza o token no sistema
			/*
			 * OrgaoDAO odao = new OrgaoDAO(); OrgaoBean obean = new
			 * OrgaoBean(); obean.setOrgaoCadastro(orgao);
			 * obean.getOrgaoCadastro().setTokenAcesso(newToken);
			 * odao.editar(obean.getOrgaoCadastro());
			 */

			// envia o email
			enviarEmail.sendMail(newToken);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void showMessage() {
        
        if(docCadastro.getStatus().equals("requerimentoCD")){ 
        	FacesUtil.addMsgInfo("Documentos necess�rios \n Pessoa F�sica \n Do contribuinte:	\n C�pias do RG e CPF."
        			+ "\n Do Procurador, no caso de requerimento feito mediante procura��o: \n"
        			+ "Procura��o particular; \n"
        			+ "C�pias do RG e CPF."
        			+ "Pessoa Jur�dica: \n Do contribuinte: \n C�pia do Cart�o de CNPJ;"
        			+ "\n Gerente/respons�vel"
        			+ "\n C�pias do RG e CPF."
        			+ "Do procurador, no caso de requerimento feito mediante procura��o: \n"
        			+ "Procura��o particular; \n"
        			+ "C�pias do RG e CPF.");
        	
        }
		/*FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "What we do in life", "Documentos necess�rios \n Pessoa F�sica \n Do contribuinte:	\n C�pias do RG e CPF."
        			+ "\n Do Procurador, no caso de requerimento feito mediante procura��o: \n"
        			+ "Procura��o particular; \n"
        			+ "C�pias do RG e CPF."
        			+ "Pessoa Jur�dica: \n Do contribuinte: \n C�pia do Cart�o de CNPJ;"
        			+ "\n Gerente/respons�vel"
        			+ "\n C�pias do RG e CPF."
        			+ "Do procurador, no caso de requerimento feito mediante procura��o: \n"
        			+ "Procura��o particular; \n"
        			+ "C�pias do RG e CPF.");
        
        PrimeFaces.current().dialog().showMessageDynamic(message);*/
    }
	
	public void mostrar(){
		String opc = this.opcao;
		switch (opc){
			case "requerimentoCD":
				this.exibirCD = true;
				this.exibirIT = false;
				FacesUtil.addMsgInfo("Documentos necess�rios <br/> Pessoa F�sica <br/>"
						+ "-<u>Do contribuinte:</u>	<br/> * C�pias do RG e CPF."
	        			+ "<br/>- Do Procurador, no caso de requerimento feito mediante procura��o: <br/>"
	        			+ "* Procura��o particular; <br/>"
	        			+ "* C�pias do RG e CPF. <br/>"
	        			+ "Pessoa Jur�dica: <br/> -<u>Do contribuinte:</u> <br/> "
	        			+ "*C�pia do Cart�o de CNPJ;"
	        			+ "<br/> -<u>Gerente/respons�vel</u>"
	        			+ "<br/> * C�pias do RG e CPF. <br/>"
	        			+ "-<u>Do procurador, no caso de requerimento feito mediante procura��o:</u> <br/>"
	        			+ "* Procura��o particular; <br/>"
	        			+ "* C�pias do RG e CPF.");
				break;
			case "requerimentoI":
				this.exibirIT = true;
				this.exibirCD = false;
				FacesUtil.addMsgInfo("<p style='color:red'>Documentos necess�rios</p> <br/> Pessoa F�sica <br/>"
						+ "-<u>Do contribuinte:</u>	<br/> * C�pias do RG e CPF."
	        			+ "<br/>- Do Procurador, no caso de requerimento feito mediante procura��o: <br/>"
	        			+ "* Procura��o particular; <br/>"
	        			+ "* C�pias do RG e CPF. <br/>"
	        			+ "Pessoa Jur�dica: <br/> -<u>Do contribuinte:</u> <br/> "
	        			+ "*C�pia do Cart�o de CNPJ;"
	        			+ "<br/> -<u>Gerente/respons�vel</u>"
	        			+ "<br/> * C�pias do RG e CPF. <br/>"
	        			+ "-<u>Do procurador, no caso de requerimento feito mediante procura��o:</u> <br/>"
	        			+ "* Procura��o particular; <br/>"
	        			+ "* C�pias do RG e CPF.");
				
		}
		
	}

}
