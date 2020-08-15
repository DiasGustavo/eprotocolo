package br.com.gerentedocumento.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.gerentedocumento.dao.ControleRegistroDAO;
import br.com.gerentedocumento.domain.ControleRegistro;
import br.com.gerentedocumento.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ControleRegistroBean {

	private ControleRegistro controleCadastro;
	
	private List<ControleRegistro> listaRegistros;
	private List<ControleRegistro> listaRegistrosFiltrados;
	
	private String acao;
	private Long codigo;
	
	public ControleRegistro getControleCadastro() {
		if(controleCadastro == null){
			controleCadastro = new ControleRegistro();
		}
		return controleCadastro;
	}
	public void setControleCadastro(ControleRegistro controleCadastro) {
		this.controleCadastro = controleCadastro;
	}
	public List<ControleRegistro> getListaRegistros() {
		return listaRegistros;
	}
	public void setListaRegistro(List<ControleRegistro> listaRegistros) {
		this.listaRegistros = listaRegistros;
	}
	public List<ControleRegistro> getListaRegistrosFiltrados() {
		return listaRegistrosFiltrados;
	}
	public void setListaRegistroFiltrados(List<ControleRegistro> listaRegistrosFiltrados) {
		this.listaRegistrosFiltrados = listaRegistrosFiltrados;
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
		controleCadastro = new ControleRegistro();
	}
	
	public void salvar(){
		try{
			ControleRegistroDAO cdao = new ControleRegistroDAO();
			cdao.salvar(controleCadastro);
			
			FacesUtil.addMsgInfo("Controle de Registro cadastrado com Sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar o Controle Registro " + ex.getMessage());
		}
	}
	
	public void listar(){
		try{
			ControleRegistroDAO cdao = new ControleRegistroDAO();
			listaRegistros = cdao.listar();
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao listar os registros " + ex.getMessage());
		}
	}
	
	public void carregarDados(){
		try{
			if (codigo != null){
				ControleRegistroDAO cdao = new ControleRegistroDAO();
				controleCadastro = cdao.buscarPorCodigo(codigo);
			}else{
				controleCadastro = new ControleRegistro();
			}
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao carregar os dados do registro " + ex.getMessage());
		}
	}
	
	public void editar(ControleRegistro controle){
		try{
			ControleRegistroDAO cdao = new ControleRegistroDAO();
			cdao.editar(controleCadastro);
			
			FacesUtil.addMsgInfo("Controle de Registro editado  com Sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao editar o Controle Registro " + ex.getMessage());
		}
	}
	
	public void excluir(ControleRegistro controle){
		try{
			ControleRegistroDAO cdao = new ControleRegistroDAO();
			cdao.excluir(controleCadastro);
			
			FacesUtil.addMsgInfo("Controle de Registro excluído com Sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir o Controle Registro " + ex.getMessage());
		}
	}
}
