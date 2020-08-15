package br.com.gerentedocumento.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.gerentedocumento.dao.EmpresaDAO;
import br.com.gerentedocumento.domain.Empresa;

@FacesConverter("empresaConverter")
public class EmpresaConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String valor) {
		try{
			Long codigo = Long.parseLong(valor);
			EmpresaDAO edao = new EmpresaDAO();
			Empresa empresa = edao.buscarPorCodigo(codigo);
			
			return empresa;
		}catch(RuntimeException ex){
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object objeto) {
		try{
			Empresa empresa = (Empresa) objeto;
			Long codigo = empresa.getId();
			
			return codigo.toString();
		}catch(RuntimeException ex){
			return null;
		}
	}

}
