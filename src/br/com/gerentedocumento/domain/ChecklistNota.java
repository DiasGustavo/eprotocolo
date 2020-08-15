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
@Table(name = "tbl_checklist_nota")
@NamedQueries({
	@NamedQuery(name = "ChecklistNota.listar", query = "SELECT checklistNota FROM ChecklistNota checklistNota"),
	@NamedQuery(name = "ChecklistNota.buscarPorNota", query = "SELECT checklistNota FROM ChecklistNota checklistNota WHERE checklistNota.notaTecnica = :codigo"),
	@NamedQuery(name = "ChecklistNota.buscarPorNotaAto", query = "SELECT checklistNota FROM ChecklistNota checklistNota WHERE checklistNota.id = :codigo AND checklistNota.atos = :atosCodigo"),
	@NamedQuery(name = "ChecklistNota.buscarPorNotaStatus", query = "SELECT checklistNota FROM ChecklistNota checklistNota WHERE checklistNota.notaTecnica = :codigo AND checklistNota.status = :status")
})
public class ChecklistNota {
	
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
	@JoinColumn(name = "fk_nota", referencedColumnName = "cod_nota")
	private NotaTecnica notaTecnica;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Atos getAtos() {
		return atos;
	}

	public void setAtos(Atos atos) {
		this.atos = atos;
	}

	public NotaTecnica getNotaTecnica() {
		return notaTecnica;
	}

	public void setNotaTecnica(NotaTecnica notaTecnica) {
		this.notaTecnica = notaTecnica;
	}

	@Override
	public String toString() {
		return "ChecklistNota [id=" + id + ", categoria=" + categoria + ", status=" + status + ", atos=" + atos
				+ ", notaTecnica=" + notaTecnica + "]";
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
		ChecklistNota other = (ChecklistNota) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
