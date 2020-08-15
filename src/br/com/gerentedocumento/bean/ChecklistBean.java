package br.com.gerentedocumento.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.gerentedocumento.dao.ChecklistDAO;
import br.com.gerentedocumento.domain.Checklist;
import br.com.gerentedocumento.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ChecklistBean {

	private Checklist checklistCadastro;
	
	private List<Checklist> listaChecklists;
	private List<Checklist> listaChecklistsFiltrados;
	
	private String acao;
	private Long codigo;
	
	public Checklist getChecklistCadastro() {
		if (checklistCadastro == null){
			checklistCadastro = new Checklist();
		}
		return checklistCadastro;
	}
	public void setChecklistCadastro(Checklist checklistCadastro) {
		this.checklistCadastro = checklistCadastro;
	}
	public List<Checklist> getListaChecklists() {
		return listaChecklists;
	}
	public void setListaChecklists(List<Checklist> listaChecklists) {
		this.listaChecklists = listaChecklists;
	}
	public List<Checklist> getListaChecklistsFiltrados() {
		return listaChecklistsFiltrados;
	}
	public void setListaChecklistsFiltrados(List<Checklist> listaChecklistsFiltrados) {
		this.listaChecklistsFiltrados = listaChecklistsFiltrados;
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
	
	public void novo(){
		checklistCadastro = new Checklist();
	}
	
	public void salvar(){
		try{
			ChecklistDAO cdao = new ChecklistDAO();
			cdao.salvar(checklistCadastro);
			
			FacesUtil.addMsgInfo("Checklist cadastrado com Sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao cadastrar o Checklist " + ex.getMessage());
			
		}
	}
	
	public void listar(){
		try{
			ChecklistDAO cdao = new ChecklistDAO();
			listaChecklists = cdao.listar();
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro ao listar os Documentos " + ex.getMessage());
		}
	}
	
	public void carregarDados(){
		try{
			if(codigo != null){
				ChecklistDAO cdao = new ChecklistDAO();
				checklistCadastro = cdao.buscarPorCodigo(codigo);
			}else{
				checklistCadastro = new Checklist();
			}
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro ao carregar os dados do checklist! " + ex.getMessage());
		}
	}
	
	public void editar(){
		try{
			ChecklistDAO cdao = new ChecklistDAO();
			cdao.editar(checklistCadastro);
			
			FacesUtil.addMsgInfo("Checklist editado com Sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao editar o Checklist " + ex.getMessage());
		}
	}
	
	public void excluir(){
		try{
			ChecklistDAO cdao = new ChecklistDAO();
			cdao.excluir(checklistCadastro);
			
			FacesUtil.addMsgInfo("Checklist excluído com Sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir o Checklist " + ex.getMessage());
		}
	}
}
