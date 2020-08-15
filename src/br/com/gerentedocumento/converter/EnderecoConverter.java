package br.com.gerentedocumento.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.gerentedocumento.dao.EnderecoDAO;
import br.com.gerentedocumento.domain.Endereco;

@FacesConverter("enderecoConverter")
public class EnderecoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String valor) {
		try{
			Long codigo = Long.parseLong(valor);
			EnderecoDAO edao = new EnderecoDAO();
			Endereco endereco = edao.buscarPorCodigo(codigo);
			
			return endereco;
		}catch(RuntimeException ex){
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object objeto) {
		try{
			Endereco endereco = (Endereco) objeto;
			Long codigo = endereco.getId();
			
			return codigo.toString();
		}catch(RuntimeException ex){
			return null;
		}
	}

}
