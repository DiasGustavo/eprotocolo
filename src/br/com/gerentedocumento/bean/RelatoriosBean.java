package br.com.gerentedocumento.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.gerentedocumento.dao.FuncionarioDAO;
import br.com.gerentedocumento.domain.Funcionario;
import br.com.gerentedocumento.util.FacesUtil;
import br.com.gerentedocumento.util.GeraRelatorio;

@ManagedBean
@ViewScoped
public class RelatoriosBean {

	private String status;
	private String secretaria;
	private Funcionario responsavel;
	private Date entrada;
	private Date saida;

	private List<Funcionario> listaFuncionarios;

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

	public void carregaDados() {
		try {
			FuncionarioDAO fdao = new FuncionarioDAO();
			listaFuncionarios = fdao.listar();
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
			parametros.put("SECRETARIA_DOC", "%%");
		} else {
			parametros.put("SECRETARIA_DOC", this.secretaria);
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
