package br.com.gerentedocumento.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.gerentedocumento.dao.LicitacaoDAO;
import br.com.gerentedocumento.domain.Licitacao;

@FacesConverter ("licitacaoConverter")
public class LicitacaoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String valor) {
		try{
			Long codigo = Long.parseLong(valor);
			
			LicitacaoDAO ldao = new LicitacaoDAO();
			Licitacao licitacao = ldao.buscarPorCodigo(codigo);
			
			return licitacao;
		}catch(RuntimeException ex){
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object objeto) {
		try{
			Licitacao licitacao = (Licitacao) objeto;
			Long codigo = licitacao.getId();
			
			return codigo.toString();
		}catch(RuntimeException ex){
			return null;
		}
	}

}
