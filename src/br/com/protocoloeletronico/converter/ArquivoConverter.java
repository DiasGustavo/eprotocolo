package br.com.protocoloeletronico.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.protocoloeletronico.dao.ArquivoDAO;
import br.com.protocoloeletronico.domain.Arquivo;

@FacesConverter("arquivoConverter")
public class ArquivoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String valor) {
		try{
			Long codigo = Long.parseLong(valor);
			ArquivoDAO adao = new ArquivoDAO();
			Arquivo arquivo = adao.buscarPorCodigo(codigo);
			
			return arquivo;
		}catch(RuntimeException ex){
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object objeto) {
		try{
			Arquivo arquivo = (Arquivo)objeto;
			Long codigo = arquivo.getId();
			
			return codigo.toString();
		}catch(RuntimeException ex){
			return null;
		}
	}

}
