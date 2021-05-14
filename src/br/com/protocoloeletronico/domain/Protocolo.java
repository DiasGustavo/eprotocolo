package br.com.protocoloeletronico.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "tbl_protocolo")
@NamedQueries({
	@NamedQuery(name = "Protocolo.listar", query = "SELECT protocolo FROM Protocolo protocolo"),
	@NamedQuery(name = "Protocolo.buscarPorCodigo", query = "SELECT protocolo FROM Protocolo protocolo WHERE protocolo.id = :codigo"),
	@NamedQuery(name = "Protocolo.listarPorResponsavel", query = "SELECT protocolo FROM Protocolo protocolo WHERE protocolo.responsavel = :responsavel ORDER BY protocolo.id DESC")
})
public class Protocolo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cod_pro")
	private Long id;
	
	@NotEmpty(message = "o campo processo é obrigatório")
	@Size(min = 1, max = 10, message= "Nome deve ter entre 1 e 10 caracteres")
	@Column(name="processo", length=10, nullable=false)
	private String processo;
	
	@NotEmpty(message = "o campo descrição é obrigatório")
	@Size(min = 3, max = 1024, message= "descrição deve ter entre 3 e 1024 caracteres")
	@Column(name="descricao", length=1024, nullable=false)
	private String descricao;
	
	@NotNull(message = "o campo data de entrada é obrigatório")
	@Temporal(value =TemporalType.DATE)
	@Column(name = "dt_entrada", nullable = false)
	private Date dataEntrada;
	
	@NotNull(message = "O campo Responsável é obrigatório")
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_funcionario", referencedColumnName = "cod_fun", nullable = false)
	private Funcionario responsavel;
	
	@NotNull(message = "o campo tipo de processo é obrigatório")
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="tipo_fk", referencedColumnName="cod_tipo_processo", nullable = false)
	private TipoProcesso tipo;
	
	@Temporal(value =TemporalType.DATE)
	@Column(name = "dt_saida")
	private Date dataSaida;
	
	@Column(name="observacao", length=1024)
	private String observacao;
	
	@Column(name = "status", length=20)
	private String status;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "protocolo_id")
    private List<Arquivo> arquivos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Funcionario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Funcionario responsavel) {
		this.responsavel = responsavel;
	}

	public TipoProcesso getTipo() {
		return tipo;
	}

	public void setTipo(TipoProcesso tipo) {
		this.tipo = tipo;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProcesso(){
		return processo;
	}
	
	public void setProcesso(String processo){
		this.processo = processo;
	}

	public List<Arquivo> getArquivos() {
		return arquivos;
	}

	public void setArquivos(List<Arquivo> arquivos) {
		this.arquivos = arquivos;
	}

	@Override
	public String toString() {
		return "Protocolo [id=" + id + ", descricao=" + descricao + ", dataEntrada=" + dataEntrada + ", responsavel="
				+ responsavel + ", tipo=" + tipo + ", dataSaida=" + dataSaida + ", observacao=" + observacao
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
		Protocolo other = (Protocolo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
