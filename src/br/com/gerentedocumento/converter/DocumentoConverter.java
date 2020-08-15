package br.com.gerentedocumento.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.gerentedocumento.dao.DocumentoDAO;
import br.com.gerentedocumento.domain.Documento;

@FacesConverter("documentoConverter")
public class DocumentoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String valor) {
		try{
			Long codigo = Long.parseLong(valor);
			DocumentoDAO ddao = new DocumentoDAO();
			Documento documento = ddao.buscarPorCodigo(codigo);
			
			return documento;
			
		}catch(RuntimeException ex){
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object objeto) {
		try{
			Documento documento = (Documento) objeto;
			Long codigo = documento.getId();
			
			return codigo.toString();
		}catch(RuntimeException ex){
			return null;
		}
	}

}
