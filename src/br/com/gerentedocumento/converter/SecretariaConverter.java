package br.com.gerentedocumento.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.gerentedocumento.dao.SecretariaDAO;
import br.com.gerentedocumento.domain.Secretaria;

@FacesConverter("secretariaConverter")
public class SecretariaConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String valor) {
		try{
			Long codigo = Long.parseLong(valor);
			
			SecretariaDAO sdao = new SecretariaDAO();
			Secretaria secretaria  = sdao.buscarPorCodigo(codigo);
			
			return secretaria;
		}catch(RuntimeException ex){
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object objeto) {
		try{
			Secretaria secretaria = (Secretaria)objeto;
			Long codigo = secretaria.getId();
			
			return codigo.toString();
		}catch(RuntimeException ex){
			return null;
		}
	}

}
