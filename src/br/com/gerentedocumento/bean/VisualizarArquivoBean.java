package br.com.gerentedocumento.bean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import br.com.gerentedocumento.util.VisualizarArquivo;

@ManagedBean
@ViewScoped
public class VisualizarArquivoBean {

	private String arquivo;

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public void abrirManual() throws IOException {
				
		String caminhoManual =  "/manual/manualusuario.pdf";
		
		
		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
		String caminhoRelatorio = servletContext.getRealPath(caminhoManual);
	
		
		VisualizarArquivo viewManual = new VisualizarArquivo();
		viewManual.setArquivo(caminhoRelatorio);
		viewManual.importarArquivo();
		viewManual.visualizarPdf();
		
	}

}
