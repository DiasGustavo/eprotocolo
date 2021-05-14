package br.com.protocoloeletronico.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.protocoloeletronico.dao.TipoProcessoDAO;
import br.com.protocoloeletronico.domain.TipoProcesso;

@FacesConverter("tipoProcessoConverter")
public class TipoProcessoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String valor) {
		try{
			Long codigo = Long.parseLong(valor);
			
			TipoProcessoDAO tdao = new TipoProcessoDAO();
			TipoProcesso tipo = tdao.buscarPorCodigo(codigo);
			
			return tipo;
		}catch(RuntimeException ex){
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object objeto) {
		try{
			TipoProcesso tipo = (TipoProcesso) objeto;
			Long codigo = tipo.getId();
			
			return codigo.toString();
		}catch(RuntimeException ex){
			return null;
		}
	}

}
