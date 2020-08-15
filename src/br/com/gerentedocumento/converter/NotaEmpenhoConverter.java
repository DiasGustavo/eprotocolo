package br.com.gerentedocumento.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.gerentedocumento.dao.NotaEmpenhoDAO;
import br.com.gerentedocumento.domain.NotaEmpenho;

@FacesConverter("notaEmpenhoConverter")
public class NotaEmpenhoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String valor) {
		try{
			Long codigo = Long.parseLong(valor);
			NotaEmpenhoDAO ndao = new NotaEmpenhoDAO();
			NotaEmpenho nota = ndao.buscarPorCodigo(codigo);
			
			return nota;
		}catch(RuntimeException ex){
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object objeto) {
		try{
			NotaEmpenho nota  = (NotaEmpenho) objeto;
			Long codigo = nota.getId();
			
			return codigo.toString();
		}catch(RuntimeException ex){
			return null;
		}
	}

}
