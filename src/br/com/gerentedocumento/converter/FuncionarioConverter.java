package br.com.gerentedocumento.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.gerentedocumento.dao.FuncionarioDAO;
import br.com.gerentedocumento.domain.Funcionario;

@FacesConverter("funcionarioConverter")
public class FuncionarioConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String valor) {
		try{
			Long codigo = Long.parseLong(valor);
			FuncionarioDAO fdao = new FuncionarioDAO();
			Funcionario funcionario = fdao.buscarPorCodigo(codigo);
			
			return funcionario;
		}catch(RuntimeException ex){
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object objeto) {
		try{
			Funcionario funcionario = (Funcionario) objeto;
			Long codigo = funcionario.getId();
			
			return codigo.toString();
		}catch(RuntimeException ex){
			return null;
		}
	}

}
