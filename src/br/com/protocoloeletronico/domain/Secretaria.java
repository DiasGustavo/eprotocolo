package br.com.protocoloeletronico.domain;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_secretaria")
@NamedQueries({
	@NamedQuery(name = "Secretaria.listar", query = "SELECT secretaria FROM Secretaria secretaria"),
	@NamedQuery(name = "Secretaria.buscarPorCodigo", query = "SELECT secretaria FROM Secretaria secretaria WHERE secretaria.id = :codigo")
})
public class Secretaria {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cod_sec")
	private Long id;
	
	@NotEmpty(message = "o campo nome é obrigatório")
	@Size(min = 3, max = 50, message= "Nome deve ter entre 3 e 50 caracteres")
	@Column(name="nome", length=50, nullable=false)
	private String nome;
	
	@Email(message = "Email informado não é válido")
	@Column(name = "email", length = 100)
	private String email;
	
	@NotNull(message = "O campo Funcionário é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_funcionario", referencedColumnName = "cod_fun", nullable = false)
	private Funcionario funcionario;
	
	@NotNull(message = "o campo orgão é obrigatório")
	@ManyToOne(fetch =FetchType.EAGER)
	@JoinColumn(name = "orgao_fk", referencedColumnName = "cod_orgao", nullable=false)
	private Orgao orgao;

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

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Orgao getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		Secretaria other = (Secretaria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Secretaria [id=" + id + ", nome=" + nome + ", funcionario=" + funcionario + ", orgao=" + orgao + "]";
	}
	
}
