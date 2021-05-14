package br.com.protocoloeletronico.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_certidaocd")
public class CertidaoCD {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_certidaocd")
	private Long id;
	
	@NotEmpty(message = "o campo nome do solicitante � obrigat�rio")
	@Size(min = 1, max = 255, message= "O Nome do solicitante deve ter entre 1 e 255 caracteres")
	@Column(name = "nome_solicitante", length = 255, nullable = false)
	private String nomeSolicitante;
	
	@NotEmpty(message = "o campo de identifica��o do solicitante � obrigat�rio")
	@Size(min = 1, max = 255, message= "A identifica��o deve ter entre 1 e 255 caracteres")
	@Column(name = "id_solicitante", length = 255, nullable = false)
	private String cpfSolicitante;
	
	@NotEmpty(message = "o campo tipo � obrigat�rio")
	@Size(min = 1, max = 255, message= "O Tipo deve ter entre 1 e 255 caracteres")
	@Column(name = "tipo", length = 255, nullable = false)
	private String tipo;
	
	@NotEmpty(message = "o campo nome � obrigat�rio")
	@Size(min = 1, max = 255, message= "O Nome deve ter entre 1 e 255 caracteres")
	@Column(name = "nome", length = 255, nullable = false)
	private String judicial;
	
	@NotEmpty(message = "o campo nome � obrigat�rio")
	@Size(min = 1, max = 255, message= "O Nome deve ter entre 1 e 255 caracteres")
	@Column(name = "nome", length = 255, nullable = false)
	private String terceiros;
}
