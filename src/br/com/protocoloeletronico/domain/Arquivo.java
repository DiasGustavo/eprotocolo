package br.com.protocoloeletronico.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

	
@Entity
@Table(name = "tbl_arquivo")
@NamedQueries({
	@NamedQuery(name = "Arquivo.listar", query = "SELECT arquivo FROM Arquivo arquivo"),
	@NamedQuery(name = "Arquivo.buscarPorCodigo", query = "SELECT arquivo FROM Arquivo arquivo WHERE arquivo.id =:codigo"),
	@NamedQuery(name = "Arquivo.buscarArquivos", query = "SELECT arquivo FROM Arquivo arquivo  JOIN arquivo.protocolo protocolo WHERE protocolo.id =:codigo")
})
public class Arquivo implements Serializable{
	private static final long serialVersionUID = -6790693372846798580L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_arquivo")
	private Long id;
	
	@NotEmpty(message = "o campo nome é obrigatório")
	@Size(min = 1, max = 255, message= "O Nome deve ter entre 1 e 255 caracteres")
	@Column(name = "nome", length = 255, nullable = false)
	private String nome;
	
	@NotEmpty(message = "o campo caminho é obrigatório")
	@Size(min = 1, max = 1024, message= "O caminho deve ter entre 1 e 1024 caracteres")
	@Column(name = "caminho", length = 1024, nullable = false)
	private String caminho;	
	
	@ManyToOne
	private Protocolo protocolo;
	
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

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	
	public Protocolo getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(Protocolo protocolo) {
		this.protocolo = protocolo;
	}

	@Override
	public String toString() {
		return "Arquivo [id=" + id + ", nome=" + nome + ", caminho=" + caminho + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Arquivo other = (Arquivo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
