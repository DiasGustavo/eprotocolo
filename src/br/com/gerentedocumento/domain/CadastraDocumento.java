package br.com.gerentedocumento.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name = "tbl_cadastra_documento")
@NamedQueries({
	@NamedQuery(name = "CadastraDocumento.listar", query = "SELECT cadastraDocumento FROM CadastraDocumento cadastraDocumento"),
	@NamedQuery(name = "CadastraDocumento.buscarPorCodigoDocumento", query = "SELECT cadastraDocumento FROM CadastraDocumento cadastraDocumento WHERE cadastraDocumento.codDocumento = :codigo")
})

public class CadastraDocumento implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cod_cad")
	private Long id;
	
	@Column(name="cod_fun")
	private Long codFuncionario;
	
	@Column(name="cod_doc")
	private Long codDocumento;
	
	@NotNull(message = "o campo data de cadastro é obrigatório")
	@Temporal(value =TemporalType.DATE)
	@Column(name = "dt_cadastro", nullable = false)
	private Date dataCadastro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCodFuncionario() {
		return codFuncionario;
	}

	public void setCodFuncionario(Long codFuncionario) {
		this.codFuncionario = codFuncionario;
	}

	public Long getCodDocumento() {
		return codDocumento;
	}

	public void setCodDocumento(Long codDocumento) {
		this.codDocumento = codDocumento;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public String toString() {
		return "CadastraDocumento [id=" + id + ", codFuncionario=" + codFuncionario + ", codDocumento=" + codDocumento
				+ ", dataCadastro=" + dataCadastro + "]";
	}
}
