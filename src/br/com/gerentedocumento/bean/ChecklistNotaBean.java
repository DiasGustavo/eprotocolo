package br.com.gerentedocumento.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.gerentedocumento.dao.ChecklistNotaDAO;
import br.com.gerentedocumento.domain.ChecklistNota;
import br.com.gerentedocumento.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ChecklistNotaBean {

	private ChecklistNota checklistNotaCadastro;
	
	private List<ChecklistNota> listaChecklistNotas;
	private List<ChecklistNota> listaChecklistNotasFiltradas;
	
	
	
	private String acao;
	private Long codigo;
	
	public ChecklistNota getChecklistNotaCadastro() {
		if(checklistNotaCadastro == null){
			checklistNotaCadastro = new ChecklistNota();
		}
		return checklistNotaCadastro;
	}
	public void setChecklistNotaCadastro(ChecklistNota checklistNotaCadastro) {
		this.checklistNotaCadastro = checklistNotaCadastro;
	}
	public List<ChecklistNota> getListaChecklistNotas() {
		return listaChecklistNotas;
	}
	public void setListaChecklistNotas(List<ChecklistNota> listaChecklistNotas) {
		this.listaChecklistNotas = listaChecklistNotas;
	}
	public List<ChecklistNota> getListaChecklistNotasFiltradas() {
		return listaChecklistNotasFiltradas;
	}
	public void setListaChecklistNotasFiltradas(List<ChecklistNota> listaChecklistNotasFiltradas) {
		this.listaChecklistNotasFiltradas = listaChecklistNotasFiltradas;
	}	
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public void salvar(){
		try{
			ChecklistNotaDAO cndao = new ChecklistNotaDAO();
			cndao.salvar(checklistNotaCadastro);
			FacesUtil.addMsgInfo("Checklist cadastrado com Sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao cadastrar o Checklist " + ex.getMessage());
		}
	}
	
	public void listar(){
		try{
			ChecklistNotaDAO cndao = new ChecklistNotaDAO();
			listaChecklistNotas = cndao.listar();
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro ao listar os Documentos " + ex.getMessage());
		}
	}
	
	public void carregarDados(){
		try{
			if(codigo != null){
				ChecklistNotaDAO cndao = new ChecklistNotaDAO();
				checklistNotaCadastro = cndao.buscarPorCodigo(codigo);				
				
			}else{
				checklistNotaCadastro = new ChecklistNota();
			}
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro ao carregar os dados do checklist! " + ex.getMessage());
		}
	}
	
	public void editar(){
		try{
			ChecklistNotaDAO cndao = new ChecklistNotaDAO();
			cndao.editar(checklistNotaCadastro);
			FacesUtil.addMsgInfo("Checklist editado com Sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao editar o Checklist " + ex.getMessage());
		}
	}
	
	public void excluir(){
		try{
			ChecklistNotaDAO cndao = new ChecklistNotaDAO();
			cndao.excluir(checklistNotaCadastro);
			
			FacesUtil.addMsgInfo("Checklist excluído com Sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir o Checklist " + ex.getMessage());
		}
	}
}
