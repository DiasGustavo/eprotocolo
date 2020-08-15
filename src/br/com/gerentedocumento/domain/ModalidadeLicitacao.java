package br.com.gerentedocumento.domain;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_modalidade_licitacao")
@NamedQueries({
	@NamedQuery(name = "ModalidadeLicitacao.listar", query = "SELECT modalidade FROM ModalidadeLicitacao modalidade"),
	@NamedQuery(name = "ModalidadeLicitacao.buscarPorCodigo", query = "SELECT modalidade FROM ModalidadeLicitacao modalidade WHERE modalidade.id = :codigo"),
	@NamedQuery(name = "ModalidadeLicitacao.buscarPorStatus", query = "SELECT modalidade FROM ModalidadeLicitacao modalidade WHERE modalidade.status = :status")
})
public class ModalidadeLicitacao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cod_modalidade")
	private Long id;
	
	@NotEmpty(message = "o campo descrição é obrigatório")
	@Size(min = 1, max = 100, message= "A descrição deve ter entre 1 e 100 caracteres")
	@Column(name="descricao", length=100, nullable=false)
	private String descricao;
	
	@NotNull(message = "o campo data de cadastro é obrigatório")
	@Temporal(value =TemporalType.DATE)
	@Column(name = "dt_cadastro", nullable = false)
	private Date dataCadastro;
	
	@NotNull(message = "O campo Funcionário é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_funcionario", referencedColumnName = "cod_fun", nullable = false)
	private Funcionario criador;
	
	@NotNull(message = "o campo data de modificação é obrigatório")
	@Temporal(value =TemporalType.DATE)
	@Column(name = "dt_modificacao", nullable = false)
	private Date dataModificacao;
	
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ModalidadeLicitacao [id=" + id + ", descricao=" + descricao + ", dataCadastro=" + dataCadastro
				+ ", dataModificacao=" + dataModificacao + "]";
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
		ModalidadeLicitacao other = (ModalidadeLicitacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
