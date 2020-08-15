package br.com.gerentedocumento.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.gerentedocumento.dao.AtosDAO;
import br.com.gerentedocumento.domain.Atos;

@FacesConverter("atosConverter")
public class AtosConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String valor) {
		try{
			Long codigo = Long.parseLong(valor);
			
			AtosDAO adao = new AtosDAO();
			Atos atos =  adao.buscarPorCodigo(codigo);
			
			return atos;
		}catch(RuntimeException ex){
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object objeto) {
		try{
			Atos atos = (Atos)objeto;
			Long codigo = atos.getId();
			
			return codigo.toString();
			
		}catch(RuntimeException ex){
			return null;
		}
	}

}
