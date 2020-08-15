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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_checklist")
@NamedQueries({
	@NamedQuery(name = "Checklist.listar", query = "SELECT checklist FROM Checklist checklist"),
	@NamedQuery(name = "Checklist.buscarPorCodigo", query = "SELECT checklist FROM Checklist checklist WHERE checklist.id = :codigo"),
	@NamedQuery(name = "Checklist.buscarPorParecer", query = "SELECT checklist FROM Checklist checklist WHERE checklist.parecer = :codigo"),
	@NamedQuery(name = "Checklist.buscarPorParecerStatus", query = "SELECT checklist FROM Checklist checklist WHERE checklist.parecer = :codigo AND checklist.status = :status"),
	@NamedQuery(name = "Checklist.buscarPorParecerAto", query = "SELECT checklist FROM Checklist checklist WHERE checklist.parecer = :codigo AND checklist.atos = :atosCodigo")
})
public class Checklist {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cod_checklist")
	private Long id;
	
	@NotEmpty(message = "o campo Categoria é obrigatório")
	@Size(min=1, max=50, message = "a categoria deve ter entre 1 e 50 caracteres")
	@Column(name = "categoria", length = 50)
	private String categoria;
	
	@NotEmpty(message = "o campo Status é obrigatório")
	@Size(min=1, max=50, message = "o status deve ter entre 1 e 10 caracteres")
	@Column(name = "status", length = 10)
	private String status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_atos", referencedColumnName = "cod_atos")
	private Atos atos;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_parecer", referencedColumnName = "cod_parecer")
	private Parecer parecer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCateoria() {
		return categoria;
	}

	public void setCateoria(String categoria) {
		this.categoria = categoria;
	}	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Atos getAtos() {
		return atos;
	}

	public void setAtos(Atos atos) {
		this.atos = atos;
	}

	public Parecer getParecer() {
		return parecer;
	}

	public void setParecer(Parecer parecer) {
		this.parecer = parecer;
	}

	@Override
	public String toString() {
		return "Checklist [id=" + id + ", cateoria=" + categoria + ", atos=" + atos + ", parecer=" + parecer + "]";
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
		Checklist other = (Checklist) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
