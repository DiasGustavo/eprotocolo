package br.com.gerentedocumento.constante;

public enum Status {
	Aplicar("Aplicar"),Omitir("Omitir");
	
	private String status;
	
	private Status (String status){
		this.status = status;
	}
	
	public String getStatus(){
		return this.status;
	}
}
