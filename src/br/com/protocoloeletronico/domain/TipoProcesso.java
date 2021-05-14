package br.com.protocoloeletronico.domain;

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
@Table(name = "tbl_tipo_processo")
@NamedQueries({
	@NamedQuery(name = "TipoProcesso.listar", query = "SELECT tipo FROM TipoProcesso tipo"),
	@NamedQuery(name = "TipoProcesso.buscarPorCodigo", query = "SELECT tipo FROM TipoProcesso tipo WHERE tipo.id = :codigo")
})
public class TipoProcesso {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cod_tipo_processo")
	private Long id;
	
	@NotEmpty(message = "o campo objeto é obrigatório")
	@Size(min = 3, max = 1024, message= "descrição deve ter entre 3 e 1024 caracteres")
	@Column(name="descricao", length=1024, nullable=false)
	private String descricao;

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

	@Override
	public String toString() {
		return "TipoProcesso [id=" + id + ", descricao=" + descricao + "]";
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
		TipoProcesso other = (TipoProcesso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
