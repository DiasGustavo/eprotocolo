package br.com.gerentedocumento.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.gerentedocumento.dao.TipoLicitacaoDAO;
import br.com.gerentedocumento.domain.ModalidadeLicitacao;
import br.com.gerentedocumento.domain.TipoLicitacao;

@FacesConverter("tipoLicitacaoConverter")
public class TipoLicitacaoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String valor) {
		try{
			Long codigo = Long.parseLong(valor);
			
			TipoLicitacaoDAO tldao = new TipoLicitacaoDAO();
			TipoLicitacao tipo = tldao.buscarPorCodigo(codigo);
			
			return tipo;
		}catch(RuntimeException ex){
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object objeto) {
		try{
			ModalidadeLicitacao modalidade = (ModalidadeLicitacao)objeto;
			Long codigo = modalidade.getId();
			
			return codigo.toString();
		}catch(RuntimeException ex){
			return null;
		}
	}

}
