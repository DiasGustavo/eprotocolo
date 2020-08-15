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
@Table(name = "tbl_nota_tecnica")
@NamedQueries({
	@NamedQuery(name = "NotaTecnica.listar", query = "SELECT notaTecnica FROM NotaTecnica notaTecnica"),
	@NamedQuery(name = "NotaTecnica.buscarPorCodigo", query = "SELECT notaTecnica FROM NotaTecnica notaTecnica WHERE notaTecnica.id = :codigo"),
	@NamedQuery(name = "NotaTecnica.buscarPorResponsavel", query = "SELECT notaTecnica FROM NotaTecnica notaTecnica WHERE notaTecnica.funcionario = :funcionario ORDER BY notaTecnica.id DESC")
})
public class NotaTecnica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cod_nota")
	private Long id;

	@NotEmpty(message = "o campo número é obrigatório")
	@Size(min = 1, max = 10, message = "Número deve ter entre 1 e 10 caracteres")
	@Column(name = "numero", length = 10, nullable = false)
	private String numero;

	// é o campo observação
	@Column(name = "conclusao", length = 8192)
	private String conclusao;

	@NotEmpty(message = "o campo tipo é obrigatório")
	@Size(min = 3, max = 50, message = "Tipo deve ter entre 3 e 50 caracteres")
	@Column(name = "objeto", length = 50, nullable = false)
	private String tipo;

	@NotEmpty(message = "o campo status é obrigatório")
	@Size(min = 3, max = 250, message = "Status deve ter entre 3 e 250 caracteres")
	@Column(name = "status", length = 250, nullable = false)
	private String status;

	@NotNull(message = "O campo Funcionário é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_funcionario", referencedColumnName = "cod_fun", nullable = false)
	private Funcionario funcionario;

	@NotNull(message = "o campo data de entrada é obrigatório")
	@Temporal(value = TemporalType.DATE)
	@Column(name = "dt_entrada", nullable = false)
	private Date dataEntrada;

	@NotNull(message = "O campo Documento é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_documento", referencedColumnName = "cod_doc", nullable = false)
	private Documento documento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getConclusao() {
		return conclusao;
	}

	public void setConclusao(String conclusao) {
		this.conclusao = conclusao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	@Override
	public String toString() {
		return "NotaTecnica [id=" + id + ", numero=" + numero + ", conclusao=" + conclusao + ", tipo=" + tipo
				+ ", status=" + status + ", funcionario=" + funcionario + ", dataEntrada=" + dataEntrada
				+ ", documento=" + documento + "]";
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
		NotaTecnica other = (NotaTecnica) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
