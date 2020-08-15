package br.com.gerentedocumento.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_empresa")
@NamedQueries({
	@NamedQuery(name = "Empresa.listar", query = "SELECT empresa FROM Empresa empresa"),
	@NamedQuery(name = "Empresa.buscarPorCodigo", query = "SELECT empresa FROM Empresa empresa WHERE empresa.id = :codigo")
})
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cod_empresa")
	private Long id;
	
	@NotEmpty(message = "o campo razão é obrigatório")
	@Size(min = 1, max = 50, message= "Razão deve ter entre 1 e 50 caracteres")
	@Column(name="razao", length=50, nullable=false)
	private String razao;
	
	@NotEmpty(message = "o campo CNPJ/CPF é obrigatório")
	@Size(min = 1, max = 18, message= "CNPJ/CPF deve ter entre 1 e 18 caracteres")
	@Column(name="rua", length=18, nullable=false)
	private String cnpj;
	
	@NotEmpty(message = "o campo email é obrigatório")
	@Email(message = "Email informado não é válido")
	@Column(name = "email", length = 50)
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRazao() {
		return razao;
	}

	public void setRazao(String razao) {
		this.razao = razao;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public String toString() {
		return "Empresa [id=" + id + ", razao=" + razao + ", cnpj=" + cnpj + "]";
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
		Empresa other = (Empresa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
