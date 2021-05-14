package br.com.protocoloeletronico.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.protocoloeletronico.dao.ProtocoloDAO;
import br.com.protocoloeletronico.domain.Protocolo;

@FacesConverter ("protocoloConverter")
public class ProtocoloConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String valor) {
		try{
			Long codigo = Long.parseLong(valor);
			ProtocoloDAO pdao = new ProtocoloDAO();
			Protocolo protocolo = pdao.buscarPorCodigo(codigo);
			return protocolo;
		}catch(RuntimeException ex){
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object objeto) {
		try{
			Protocolo protocolo = (Protocolo)objeto;
			Long codigo = protocolo.getId();
			return codigo.toString();
		}catch(RuntimeException ex){
			return null;
		}
	}

}
