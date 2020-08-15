package br.com.gerentedocumento.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.gerentedocumento.dao.ModalidadeLicitacaoDAO;
import br.com.gerentedocumento.domain.ModalidadeLicitacao;

@FacesConverter("modalidadeLicitacaoConverter")
public class ModalidadeLicitacaoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String valor) {
		try{
			Long codigo = Long.parseLong(valor);
			
			ModalidadeLicitacaoDAO mldao = new ModalidadeLicitacaoDAO();
			ModalidadeLicitacao modalidade = mldao.buscarPorCodigo(codigo);
			
			return modalidade;
		}catch(RuntimeException ex){
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object objeto) {
		try{
			ModalidadeLicitacao modalidade = (ModalidadeLicitacao) objeto;
			Long codigo = modalidade.getId();
			
			return codigo.toString();
		}catch(RuntimeException ex){
			return null;
		}
	}

}
