package br.com.gerentedocumento.domain;

import java.math.BigDecimal;

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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_nota_empenho")
@NamedQueries({
	@NamedQuery(name = "NotaEmpenho.listar", query = "SELECT nota FROM NotaEmpenho nota"),
	@NamedQuery(name = "NotaEmpenho.buscarPorCodigo", query = "SELECT nota FROM NotaEmpenho nota WHERE nota.id = :codigo"),
	@NamedQuery(name = "NotaEmpenho.buscarPorDocumento", query = "SELECT nota FROM NotaEmpenho nota WHERE nota.documento = :documento"),
	@NamedQuery(name = "NotaEmpenho.buscarPorFuncionario", query = "SELECT nota FROM NotaEmpenho nota WHERE nota.funcionario = :funcionario")
	
	
})
public class NotaEmpenho {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cod_nota")
	private Long Id;
	
	@NotEmpty(message = "o campo processo é obrigatório")
	@Size(min = 1, max = 10, message= "Nome deve ter entre 1 e 10 caracteres")
	@Column(name="numero", length=10, nullable=false)
	private String numero;
	
	@NotNull(message="o campo valor é obrigatório.")
	@DecimalMin(value="0.00", message="o campo valor deve ser maior do que 0.00")
	@Digits(integer = 7, fraction = 2, message = "coloque um valor válido para o valor")
	@Column(name = "valor", precision = 9, scale = 2, nullable = false)
	private BigDecimal valor;
	
	@NotEmpty(message = "o campo descrição é obrigatório")
	@Size(min = 1, max = 50, message= "Descrição deve ter entre 1 e 50 caracteres")
	@Column(name="descricao", length=50, nullable=false)
	private String descricao;
	
	
	@Column(name="observacao", length=250)
	private String observacao;
	
	@NotNull(message = "O campo Documento é obrigatório")
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_documento", referencedColumnName = "cod_doc", nullable = false)
	private Documento documento;
	
	@NotNull(message = "O campo Digitador é obrigatório")
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_funcionario", referencedColumnName = "cod_fun", nullable = false)
	private Funcionario funcionario;


	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Override
	public String toString() {
		return "NotaEmpenho [Id=" + Id + ", numero=" + numero + ", valor=" + valor + ", descricao=" + descricao
				+ ", observacao=" + observacao + ", documento=" + documento + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
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
		NotaEmpenho other = (NotaEmpenho) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}
	
	
}
