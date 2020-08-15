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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_licitacao")
@NamedQueries({
	@NamedQuery(name = "Licitacao.listar", query = "SELECT licitacao FROM Licitacao licitacao ORDER BY licitacao.id DESC"),
	@NamedQuery(name = "Licitacao.buscarPorCodigo", query = "SELECT licitacao FROM Licitacao licitacao WHERE licitacao.id = :codigo")
})
public class Licitacao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cod_doc")
	private Long id;
	
	@NotEmpty(message = "o campo objeto é obrigatório")
	@Size(min = 3, max = 1024, message= "Nome deve ter entre 3 e 1024 caracteres")
	@Column(name="objeto", length=1024, nullable=false)
	private String objeto;
	
	@NotNull(message="o campo valor é obrigatório.")
	@DecimalMin(value="0.00", message="o campo valor deve ser maior do que 0.00")
	@Digits(integer = 7, fraction = 2, message = "coloque um valor válido para o valor")
	@Column(name = "valor", precision = 9, scale = 2, nullable = false)
	private BigDecimal valor;
	
	@Temporal(value =TemporalType.DATE)
	@Column(name = "dt_assinatura_contrato", nullable = true)
	private Date dataAssinaturaContrato;
	
	
	@Column(name="prazo_execucao", length=10, nullable=true)
	private String prazoExecucao;
	
	@NotNull(message = "o campo data de vencimento é obrigatório")
	@Temporal(value =TemporalType.DATE)
	@Column(name = "dt_vencimento", nullable = false)
	private Date dataVencimento;
	
	@NotNull(message = "o campo data de cadastro é obrigatório")
	@Temporal(value =TemporalType.DATE)
	@Column(name = "dt_cadastro", nullable = false)
	private Date dataCadastro;
	
	@NotNull(message = "o campo data de modificação é obrigatório")
	@Temporal(value =TemporalType.DATE)
	@Column(name = "dt_modificacao", nullable = false)
	private Date dataModificacao;
	
	@NotNull(message = "O campo Tipo de Licitação é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_tipo_licitacao", referencedColumnName = "cod_tipo", nullable = false)
	private TipoLicitacao tipo;
	
	@NotNull(message = "O campo Modalidade de Licitação é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_modalidade_licitacao", referencedColumnName = "cod_modalidade", nullable = false)
	private ModalidadeLicitacao modalidade;
	
	@NotNull(message = "O campo Empresa é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_empresa", referencedColumnName = "cod_empresa", nullable = false)
	private Empresa empresa;
	
	@NotNull(message = "O campo Funcionário é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_funcionario", referencedColumnName = "cod_fun", nullable = false)
	private Funcionario criador;
	
	@NotEmpty(message = "o campo status é obrigatório")
	@Size(min = 1, max = 50, message= "o status deve ter entre 1 e 50 caracteres")
	@Column(name="status", length=50, nullable=false)
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getDataAssinaturaContrato() {
		return dataAssinaturaContrato;
	}

	public void setDataAssinaturaContrato(Date dataAssinaturaContrato) {
		this.dataAssinaturaContrato = dataAssinaturaContrato;
	}

	public String getPrazoExecucao() {
		return prazoExecucao;
	}

	public void setPrazoExecucao(String prazoExecucao) {
		this.prazoExecucao = prazoExecucao;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataModificacao() {
		return dataModificacao;
	}

	public void setDataModificacao(Date dataModificacao) {
		this.dataModificacao = dataModificacao;
	}

	public Funcionario getCriador() {
		return criador;
	}

	public void setCriador(Funcionario criador) {
		this.criador = criador;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public TipoLicitacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoLicitacao tipo) {
		this.tipo = tipo;
	}

	public ModalidadeLicitacao getModalidade() {
		return modalidade;
	}

	public void setModalidade(ModalidadeLicitacao modalidade) {
		this.modalidade = modalidade;
	}

	@Override
	public String toString() {
		return "Licitacao [id=" + id + ", objeto=" + objeto + ", valor=" + valor + ", dataAssinaturaContrato="
				+ dataAssinaturaContrato + ", prazoExecucao=" + prazoExecucao + ", dataVencimento=" + dataVencimento
				+ ", dataCadastro=" + dataCadastro + ", dataModificacao=" + dataModificacao + ", criador=" + criador
				+ ", status=" + status + "]";
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
		Licitacao other = (Licitacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
