package br.com.protocoloeletronico.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cod_pes")
	private Long id;
	
	@NotEmpty(message = "o campo nome é obrigatório")
	@Size(min = 3, max = 50, message= "Nome deve ter entre 3 e 50 caracteres")
	@Column(name="nome", length=50, nullable=false)
	private String nome;
	
	@NotNull(message = "o campo data de nascimento é obrigatório")
	@Temporal(value =TemporalType.DATE)
	@Column(name = "dt_nascimento", nullable = false)
	private Date dataNascimento;
	
	@NotNull(message="o campo idade é obrigatório.")
	@DecimalMin(value="0.00", message="o campo idade deve ser maior do que 0.00")
	@Digits(integer = 7, fraction = 2, message = "coloque um valor válido para a idade")
	@Column(name = "idade", precision = 4, scale = 1, nullable = false)
	private BigDecimal idade;
	
	@NotEmpty(message = "o campo mae é obrigatório")
	@Size(min = 3, max = 50, message= "Nome da mãe deve ter entre 3 e 50 caracteres")
	@Column(name="mae", length=50, nullable=false)
	private String mae;
	
	@Column(name = "telefone", length = 13)
	private String telefone;
	
	@NotEmpty(message = "o campo nacionalidade é obrigatório")
	@Size(min = 3, max = 50, message= "A nacionalidade deve ter entre 3 e 50 caracteres")
	@Column(name="nacionalidade", length=50, nullable=false)
	private String nacionalidade;
	
	@NotEmpty(message = "o campo naturalidade é obrigatório")
	@Size(min = 3, max = 50, message= "A naturalidade deve ter entre 3 e 50 caracteres")
	@Column(name="naturalidade", length=50, nullable=false)
	private String naturalidade;
	
	@NotEmpty(message="O Campo CPF é obrigatório.")
	@CPF(message="O CPF informado é inválido.")
	@Column (name = "cpf", length = 14, nullable = false)
	private String cpf;
	
	@Column (name = "rg", length = 12)
	private String rg;
	
	@Column(name = "raca", length = 13)
	private String raca;
	
	@Column(name = "genero", length = 13)
	private String genero;
	
	@Column(name = "orientacao_sexual", length = 13)
	private String orientacaoSexual;
	
	@Column(name = "deficiencia", length = 13)
	private String deficiencia;
	
	@Column(name = "servico", length = 13)
	private String servico;
	
	@NotNull(message = "o campo endereço é obrigatório")
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="endereco_fk", referencedColumnName="cod_endereco", nullable = false)
	private Endereco endereco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public BigDecimal getIdade() {
		return idade;
	}

	public void setIdade(BigDecimal idade) {
		this.idade = idade;
	}

	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getOrientacaoSexual() {
		return orientacaoSexual;
	}

	public void setOrientacaoSexual(String orientacaoSexual) {
		this.orientacaoSexual = orientacaoSexual;
	}

	public String getDeficiencia() {
		return deficiencia;
	}

	public void setDeficiencia(String deficiencia) {
		this.deficiencia = deficiencia;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", dataNascimento=" + dataNascimento + ", idade=" + idade
				+ ", mae=" + mae + ", telefone=" + telefone + ", nacionalidade=" + nacionalidade + ", naturalidade="
				+ naturalidade + ", cpf=" + cpf + ", rg=" + rg + ", raca=" + raca + ", genero=" + genero
				+ ", orientacaoSexual=" + orientacaoSexual + ", deficiencia=" + deficiencia + ", servico=" + servico
				+ "]";
	}
	
	
}
