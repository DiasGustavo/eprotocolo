package br.com.gerentedocumento.domain;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_funcionario")
@NamedQueries({
	@NamedQuery(name = "Funcionario.listar", query = "SELECT funcionario FROM Funcionario funcionario"),
	@NamedQuery(name = "Funcionario.buscarPorCodigo", query = "SELECT funcionario FROM Funcionario funcionario WHERE funcionario.id = :codigo"),
	@NamedQuery(name = "Funcionario.autenticar" , query = "SELECT funcionario FROM Funcionario funcionario WHERE funcionario.login = :login AND funcionario.senha = :senha")
})
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cod_fun")
	private Long id;
	
	@NotEmpty(message = "o campo nome é obrigatório")
	@Size(min = 3, max = 50, message= "Nome deve ter entre 3 e 50 caracteres")
	@Column(name="nome", length=50, nullable=false)
	private String nome;
	
	@Column(name="matricula", length=10, nullable=true)
	private String matricula;
	
	@NotEmpty(message = "o campo função é obrigatório")
	@Size(min = 1, max = 15, message= "A função deve ter entre 1 e 15 caracteres")
	@Column(name="funcao", length=10, nullable=false)
	private String funcao;
	
	@Email(message = "Email informado não é válido")
	@Column(name = "email", length = 50)
	private String email;
	
	@Column(name = "telefone", length = 13)
	private String telefone;
	
	@NotEmpty(message = "o campo Login é obrigatório")
	@Size(min = 1, max = 50, message= "Login deve ter entre 1 e 50 caracteres")
	@Column(name = "login", length = 50, nullable = false)
	private String login;
	
	@NotEmpty(message = "o campo Senha é obrigatório")
	@Size(min = 6, max = 32, message= "Senha deve ter no mínimo 6 caracteres")
	@Column(name = "senha", length = 50, nullable = false)
	private String senha;
	
	@NotNull(message = "o campo orgão é obrigatório")
	@ManyToOne(fetch =FetchType.EAGER)
	@JoinColumn(name = "orgao_fk", referencedColumnName = "cod_orgao", nullable=false)
	private Orgao orgao;

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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}	

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Orgao getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", matricula=" + matricula + ", email=" + email
				+ ", telefone=" + telefone + ", login=" + login + ", senha=" + senha + "]";
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
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
