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

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_atos")
@NamedQueries({
	@NamedQuery(name = "Atos.listar", query = "SELECT atos FROM Atos atos"),
	@NamedQuery(name = "Atos.buscarPorCodigo", query = "SELECT atos FROM Atos atos WHERE atos.id = :codigo")
})
public class Atos {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cod_atos")
	private Long id;
	
	@NotEmpty(message = "o campo descrição é obrigatório")
	@Size(min = 3, max = 200, message= "A descrição deve ter entre 3 e 200 caracteres")
	@Column(name="descricao", length=200, nullable=false)
	private String descricao;
	
	@NotEmpty(message = "o campo tipo parecer é obrigatório")
	@Size(min = 3, max = 50, message= "O tipo parecer deve ter entre 3 e 15 caracteres")
	@Column(name="categoria", length=50, nullable=false)
	private String categoria;
	
	@NotEmpty(message = "o campo descrição é obrigatório")
	@Size(min = 1, max = 200, message= "A descrição deve ter entre 1 e 200 caracteres")
	@Column(name="status", length=200, nullable=false)
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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Atos [id=" + id + ", descricao=" + descricao + ", categoria=" + categoria + "]";
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
		Atos other = (Atos) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
