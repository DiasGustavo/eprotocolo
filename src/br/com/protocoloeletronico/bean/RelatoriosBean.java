package br.com.protocoloeletronico.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.protocoloeletronico.dao.FuncionarioDAO;
import br.com.protocoloeletronico.dao.TipoProcessoDAO;
import br.com.protocoloeletronico.domain.Funcionario;
import br.com.protocoloeletronico.domain.TipoProcesso;
import br.com.protocoloeletronico.util.FacesUtil;
import br.com.protocoloeletronico.util.GeraRelatorio;

@ManagedBean
@ViewScoped
public class RelatoriosBean {

	private String status;
	private String secretaria;
	private Funcionario responsavel;
	private TipoProcesso tipo;
	private Date entrada;
	private Date saida;

	private List<Funcionario> listaFuncionarios;
	private List<TipoProcesso> listaTipos;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(String secretaria) {
		this.secretaria = secretaria;
	}

	public Funcionario getResponsavel() {
		if (responsavel == null) {
			responsavel = new Funcionario();
		}
		return responsavel;
	}

	public void setResponsavel(Funcionario responsavel) {
		this.responsavel = responsavel;
	}

	public Date getEntrada() {
		return entrada;
	}

	public void setEntrada(Date entrada) {
		this.entrada = entrada;
	}

	public Date getSaida() {
		return saida;
	}

	public void setSaida(Date saida) {
		this.saida = saida;
	}

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public List<TipoProcesso> getListaTipos() {
		return listaTipos;
	}

	public void setListaTipos(List<TipoProcesso> listaTipos) {
		this.listaTipos = listaTipos;
	}

	public TipoProcesso getTipo() {
		return tipo;
	}

	public void setTipo(TipoProcesso tipo) {
		this.tipo = tipo;
	}

	public void carregaDados() {
		try {
			FuncionarioDAO fdao = new FuncionarioDAO();
			listaFuncionarios = fdao.listar();
			
			TipoProcessoDAO tpdao = new TipoProcessoDAO();
			listaTipos = tpdao.listar();
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao carregar os Funcionários! " + ex.getMessage());
		}
	}

	public void relatorioDocumentos() {
		String caminho = "/reports/documentos.jasper";

		Map<String, Object> parametros = new HashMap<>();
		if (this.status == null) {
			parametros.put("STATUS_DOC", "%%");
		} else {
			parametros.put("STATUS_DOC", this.status);
		}
		if (this.secretaria == null) {
			parametros.put("DESCRICAO_TIPO_DOC", "%%");
		} else {
			parametros.put("DESCRICAO_TIPO_DOC", this.tipo);
		}

		// verifica se o responsável está null e coloca na query do sql
		if (this.responsavel == null) {
			parametros.put("RESPONSAVEL_DOC", "%%");
		} else {
			parametros.put("RESPONSAVEL_DOC", this.responsavel.getNome());
		}
		parametros.put("ENTRADA_DOC", this.entrada);
		parametros.put("ENTRADA_DOC_FIM", this.saida);
				
		GeraRelatorio gerador = new GeraRelatorio();
		gerador.geradorDeRelatorios(caminho, parametros);
	}

}
