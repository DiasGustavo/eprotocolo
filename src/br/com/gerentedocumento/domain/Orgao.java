package br.com.gerentedocumento.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_orgao")
@NamedQueries({
	@NamedQuery(name = "Orgao.listar", query = "SELECT orgao FROM Orgao orgao"),
	@NamedQuery(name = "Orgao.buscarPorCodigo", query = "SELECT orgao FROM Orgao orgao WHERE orgao.id = :codigo"),
	@NamedQuery(name = "Orgao.buscarPorRegistro", query = "SELECT orgao FROM Orgao orgao WHERE orgao.registro = :registro AND :dataTeste BETWEEN '1900-01-01' AND orgao.dataVigencia")
})
public class Orgao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cod_orgao")
	private Long id;
	
	@NotEmpty(message = "o campo nome é obrigatório")
	@Size(min = 1, max = 50, message= "Nome deve ter entre 1 e 50 caracteres")
	@Column(name="nome", length=50, nullable=false)
	private String nome;
	
	@NotEmpty(message = "o campo CNPJ é obrigatório")
	@Size(min = 1, max = 18, message= "CNPJ deve ter entre 1 e 18 caracteres")
	@Column(name="cnpj", length=18, nullable=false)
	private String cnpj;
	
	@NotEmpty(message = "o campo repositório é obrigatório")
	@Size(min = 1, max = 200, message= "repositório deve ter entre 1 e 200 caracteres")
	@Column(name="repositorio", length=200, nullable=false)
	private String repositorio;
	
	@Email(message = "Email informado não é válido")
	@Column(name = "email", length = 100)
	private String email;
	
	@Column(name = "telefone", length = 13)
	private String telefone;
	
	@NotEmpty(message = "o campo token de acesso é obrigatório")
	@Column(name="token_acesso", length=2048, nullable=false)
	private String tokenAcesso;
	
	@NotEmpty(message = "o campo client_id é obrigatório")
	@Column(name="client_id", length=2048, nullable=false)
	private String clientId;
	
	@NotEmpty(message = "o campo client_secret é obrigatório")
	@Column(name="client_secret", length=2048, nullable=false)
	private String clientSecret;
	
	@NotNull(message = "o campo endereço é obrigatório")
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="endereco_fk", referencedColumnName="cod_endereco", nullable = false)
	private Endereco endereco;
	
	@NotNull(message = "o campo data de vigência é obrigatório")
	@Temporal(value =TemporalType.DATE)
	@Column(name = "dt_vigencia", nullable = false)
	private Date dataVigencia;
	
	@NotEmpty(message = "o campo Registro é obrigatório")
	@Size(min = 6, max = 32, message= "Registro deve ter no mínimo 6 caracteres")
	@Column(name = "registro", length = 50, nullable = false)
	private String registro;
	
	@NotEmpty(message = "o campo status é obrigatório")
	@Size(min = 1, max = 1, message= "O status deve ter entre 1 caracteres")
	@Column(name="status", length=1, nullable=false)
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRepositorio() {
		return repositorio;
	}

	public void setRepositorio(String repositorio) {
		this.repositorio = repositorio;
	}
		
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTokenAcesso() {
		return tokenAcesso;
	}

	public void setTokenAcesso(String tokenAcesso) {
		this.tokenAcesso = tokenAcesso;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Date getDataVigencia() {
		return dataVigencia;
	}

	public void setDataVigencia(Date dataVigencia) {
		this.dataVigencia = dataVigencia;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Orgao [id=" + id + ", nome=" + nome + ", cnpj=" + cnpj + ", repositorio=" + repositorio + ", brasao="
				+ ", endereco=" + endereco + "]";
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
		Orgao other = (Orgao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
