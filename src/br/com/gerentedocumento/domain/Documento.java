package br.com.gerentedocumento.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_documento")
@NamedQueries({
	@NamedQuery(name = "Documento.listar", query = "SELECT documento FROM Documento documento ORDER BY documento.id DESC"),
	@NamedQuery(name = "Documento.buscarPorCodigo", query = "SELECT documento FROM Documento documento WHERE documento.id = :codigo"),
	@NamedQuery(name = "Documento.buscarPorProcessoSecretaria", query = "SELECT documento FROM Documento documento WHERE documento.id = :codigo AND documento.processo = :processo AND documento.secretaria = :secretaria"),
	@NamedQuery(name = "Documento.listarPorResponsavel", query = "SELECT documento FROM Documento documento WHERE documento.responsavel = :responsavel ORDER BY documento.id DESC"),
	@NamedQuery(name = "Documento.listarPorMes", query = "SELECT COUNT(*), MONTH(documento.dataEntrada), YEAR(dataEntrada) FROM Documento documento GROUP BY MONTH(documento.dataEntrada)")
})
public class Documento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cod_doc")
	private Long id;
	
	@NotEmpty(message = "o campo processo é obrigatório")
	@Size(min = 1, max = 10, message= "Nome deve ter entre 1 e 10 caracteres")
	@Column(name="processo", length=10, nullable=false)
	private String processo;
	
	@NotEmpty(message = "o campo objeto é obrigatório")
	@Size(min = 3, max = 1024, message= "Nome deve ter entre 3 e 1024 caracteres")
	@Column(name="objeto", length=1024, nullable=false)
	private String objeto;
	
	@NotNull(message="o campo valor é obrigatório.")
	@DecimalMin(value="0.00", message="o campo valor deve ser maior do que 0.00")
	@Digits(integer = 7, fraction = 2, message = "coloque um valor válido para o valor")
	@Column(name = "valor", precision = 9, scale = 2, nullable = false)
	private BigDecimal valor;
	
	@NotNull(message = "o campo data de entrada é obrigatório")
	@Temporal(value =TemporalType.DATE)
	@Column(name = "dt_entrada", nullable = false)
	private Date dataEntrada;
	
	@NotNull(message = "O campo Secretaria é obrigatório")
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_secretaria", referencedColumnName = "cod_sec", nullable = false)
	private Secretaria secretaria;
	
	@Temporal(value =TemporalType.DATE)
	@Column(name = "dt_saida")
	private Date dataSaida;
	
	@Column(name="encaminhamento", length=50)
	private String encaminhamento;
	
	@Column(name="observacao", length=1024)
	private String observacao;
	
	@NotNull(message = "o campo Empresa é obrigatório")
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "empresa_fk", referencedColumnName = "cod_empresa", nullable = false)	
	private Empresa empresa;
	
	@NotNull(message = "O campo Responsável é obrigatório")
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_funcionario", referencedColumnName = "cod_fun", nullable = false)
	private Funcionario responsavel;
	
	@Column(name = "proc_licitatorio", length=100)
	private String procedimentoLicitatorio;
	
	@Column(name = "contrato", length=20)
	private String contrato;
	
	@Temporal(value =TemporalType.DATE)
	@Column(name = "dt_homologacao")
	private Date dataHomologacao;
	
	@Column(name = "aditivo", length=70)
	private String aditivo;
	
	@Column(name = "fiscal_contrato", length=200)
	private String fiscal;
	
	@Column(name = "medicao", length=5)
	private String medicao;
	
	@Column(name = "status", length=20)
	private String status;
	
	@Column(name = "doc_arquivo", length=1024)
	private String docArquivo;
	
	@Temporal(value =TemporalType.DATE)
	@Column(name = "dt_vigencia_inicio")
	private Date vigenciaInicio;
	
	@Temporal(value =TemporalType.DATE)
	@Column(name = "dt_vigencia_fim")
	private Date vigenciaFim;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProcesso() {
		return processo;
	}

	public void setProcesso(String processo) {
		this.processo = processo;
	}

	public String getObjeto() {
		return objeto;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Secretaria getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(Secretaria secretaria) {
		this.secretaria = secretaria;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}
	
	public String getEncaminhamento() {
		return encaminhamento;
	}

	public void setEncaminhamento(String encaminhamento) {
		this.encaminhamento = encaminhamento;
	}
	
	public String getContrato() {
		return contrato;
	}

	public void setContrato(String contrato) {
		this.contrato = contrato;
	}

	public Date getDataHomologacao() {
		return dataHomologacao;
	}

	public void setDataHomologacao(Date dataHomologacao) {
		this.dataHomologacao = dataHomologacao;
	}
	
	public String getMedicao() {
		return medicao;
	}

	public void setMedicao(String medicao) {
		this.medicao = medicao;
	}

	public String getAditivo() {
		return aditivo;
	}

	public void setAditivo(String aditivo) {
		this.aditivo = aditivo;
	}

	public String getFiscal() {
		return fiscal;
	}

	public void setFiscal(String fiscal) {
		this.fiscal = fiscal;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Funcionario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Funcionario responsavel) {
		this.responsavel = responsavel;
	}	

	public String getProcedimentoLicitatorio() {
		return procedimentoLicitatorio;
	}

	public void setProcedimentoLicitatorio(String procedimentoLicitatorio) {
		this.procedimentoLicitatorio = procedimentoLicitatorio;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDocArquivo() {
		return docArquivo;
	}

	public void setDocArquivo(String docArquivo) {
		this.docArquivo = docArquivo;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public Date getVigenciaInicio() {
		return vigenciaInicio;
	}

	public void setVigenciaInicio(Date vigenciaInicio) {
		this.vigenciaInicio = vigenciaInicio;
	}

	public Date getVigenciaFim() {
		return vigenciaFim;
	}

	public void setVigenciaFim(Date vigenciaFim) {
		this.vigenciaFim = vigenciaFim;
	}

	

	@Override
	public String toString() {
		return "Documento [id=" + id + ", processo=" + processo + ", objeto=" + objeto + ", valor=" + valor
				+ ", dataEntrada=" + dataEntrada + ", secretaria=" + secretaria + ", dataSaida=" + dataSaida
				+ ", encaminhamento=" + encaminhamento + ", observacao=" + observacao + ", empresa=" + empresa
				+ ", responsavel=" + responsavel + ", procedimentoLicitatorio=" + procedimentoLicitatorio
				+ ", contrato=" + contrato + ", dataHomologacao=" + dataHomologacao + ", aditivo=" + aditivo
				+ ", fiscal=" + fiscal + ", medicao=" + medicao + ", status=" + status + ", docArquivo=" + docArquivo
				+ ", vigenciaInicio=" + vigenciaInicio + ", vigenciaFim=" + vigenciaFim + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Documento other = (Documento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}