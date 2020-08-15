package br.com.gerentedocumento.util;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;


@SuppressWarnings("deprecation")
public class GeraRelatorio {

	public void geraRelatorioProcessos(){
		try {
			String caminho = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/reports/documentos.jasper");

			Map<String, Object> parametros = new HashMap<>();

			Connection conexao = HibernateUtil.getConexao();

			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			JasperPrintManager.printReport(relatorio, true);
			
			
		} catch (JRException ex) {
			FacesUtil.addMsgErro("Ocorreru ao gerar o relatório de processos" + ex.getMessage());
		}
	}
	
	
	public void geradorDeRelatorios(String caminho, Map<String, Object> parametros) {
		Connection conexao = HibernateUtil.getConexao();
		try {
			// contexto do relatório
			FacesContext context = FacesContext.getCurrentInstance();
			ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
			String caminhoRelatorio = servletContext.getRealPath(caminho);
			
			// Definição do tipo de arquivos
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
			response.setContentType("application/pdf");
			//abre o relatório no próprio navegador
			response.addHeader("Content-disposition", "inline; filename=\"relatorio.pdf\"");
			
			// compilando o relatório
			JasperPrint relatorio = JasperFillManager.fillReport(caminhoRelatorio, parametros, conexao);
			JasperExportManager.exportReportToPdfStream(relatorio, response.getOutputStream());
			
			// finalizando o contexto
			context.getApplication().getStateManager().saveView(context);
			context.renderResponse();
			context.responseComplete();
			
		} catch (JRException ex) {
			FacesUtil.addMsgErro("Erro JRE ao gerar o relatório " + ex.getMessage());
		} catch (IOException ex) {
			FacesUtil.addMsgErro("Erro I/O ao gerar o relatório " + ex.getMessage());
		}
	}
}
