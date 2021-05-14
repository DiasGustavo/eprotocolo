package br.com.protocoloeletronico.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cod_fun")
	private Long id;
	

	@NotEmpty(message = "o campo nome é obrigatório")
	@Size(min = 3, max = 50, message= "Nome deve ter entre 3 e 50 caracteres")
	@Column(name="nome", length=50, nullable=false)
	private String nome;
	
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
	
	@NotEmpty(message = "o campo codigoConfirmação é obrigatório")
	@Size(min = 3, max = 50, message= "codigoConfirmação deve ter entre 3 e 50 caracteres")
	@Column(name="codConfirmacao", length=50, nullable=false)
	private String codigoConfirmacao;
	
	@NotEmpty(message = "o campo pacesso é obrigatório")
	@Size(min = 3, max = 50, message= "pacesso deve ter entre 3 e 50 caracteres")
	@Column(name="pacesso", length=50, nullable=false)
	private String primeiroAcesso;
	
	@Column(name = "status", length=20)
	private String status;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getCodigoConfirmacao() {
		return codigoConfirmacao;
	}

	public void setCodigoConfirmacao(String codigoConfirmacao) {
		this.codigoConfirmacao = codigoConfirmacao;
	}

	public String getPrimeiroAcesso() {
		return primeiroAcesso;
	}

	public void setPrimeiroAcesso(String primeiroAcesso) {
		this.primeiroAcesso = primeiroAcesso;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", email=" + email + ", telefone=" + telefone + ", login=" + login + ", senha="
				+ senha + ", codigoConfirmacao=" + codigoConfirmacao + ", primeiroAcesso=" + primeiroAcesso
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
