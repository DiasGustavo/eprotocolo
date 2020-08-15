package br.com.gerentedocumento.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_controle_registro")
@NamedQueries({
	@NamedQuery(name = "controleRegistro.buscarPorDescricao", query = "SELECT controleRegistro FROM ControleRegistro controleRegistro WHERE controleRegistro.descricao = :descricao"),
	@NamedQuery(name = "controleRegistro.listar", query = "SELECT controleRegistro FROM ControleRegistro controleRegistro"),
	@NamedQuery(name = "controleRegistro.buscarPorCodigo", query = "SELECT controleRegistro FROM ControleRegistro controleRegistro WHERE controleRegistro.id = :codigo")
})
public class ControleRegistro {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cod_controle")
	private Long id;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "valor")
	private int valor;

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

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "ControleRegistro [id=" + id + ", descricao=" + descricao + ", valor=" + valor + "]";
	}
	
}
