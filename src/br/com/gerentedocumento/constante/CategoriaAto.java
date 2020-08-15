package br.com.gerentedocumento.constante;

public enum CategoriaAto {
	consumo("consumo"),obras("obras"),servicopf("Serviços Pessoa Física"),
	servicopj("Serviço Pessoa Jurídica"),diarias("Diárias"),alugueispf("Aluguéis Pessoa Física"),
	alugueispj("Aluguéis Pessoa Jurídica");
	
	private String categoria;
	
	private CategoriaAto (String categoria){
		this.categoria = categoria;
	}
	
	public String getCategoria(){
		return categoria;
	}
}
