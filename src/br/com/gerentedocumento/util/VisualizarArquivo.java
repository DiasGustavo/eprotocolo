package br.com.gerentedocumento.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

public class VisualizarArquivo {

	// Array de bytes que armazenará o conteúdo do arquivo PDF
	private byte[] conteudoPdf;

	// Caminho completo do arquivo informado pelo usuário.
	// Ex: C:\Meus Documentos\boletim.pdf
	private String arquivo;

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public void importarArquivo() {
		try {
			// Cria um objeto File a partir do caminho especificado
			File file = new File(arquivo);

			// Inicializa o array bytes com o tamanho do arquivo especificado.
			// Note a conversao para int, restringindo a capacidade maxima do
			// arquivo em 2 GB
			conteudoPdf = new byte[(int) file.length()];

			// Cria um InputStream a partir do objeto File
			InputStream is = new FileInputStream(file);

			// Aqui o InputStream faz a leitura do arquivo, transformando em um
			// array de bytes
			is.read(conteudoPdf);

			// Fecha o InputStream, liberando seus recursos
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void visualizarPdf() {

		FacesContext fc = FacesContext.getCurrentInstance();

		// Obtem o HttpServletResponse, objeto responsável pela resposta do
		// servidor ao browser
		HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();

		// Limpa o buffer do response
		response.reset();

		// Seta o tipo de conteudo no cabecalho da resposta. No caso, indica que
		// o
		// conteudo sera um documento pdf.
		response.setContentType("application/pdf");

		// Seta o tamanho do conteudo no cabecalho da resposta. No caso, o
		// tamanho
		// em bytes do pdf
		response.setContentLength(conteudoPdf.length);

		// Seta o nome do arquivo e a disposição: "inline" abre no próprio
		// navegador.
		// Mude para "attachment" para indicar que deve ser feito um download
		response.setHeader("Content-disposition", "inline; filename=ManualUsuarioSysCI.pdf");
		try {

			// Envia o conteudo do arquivo PDF para o response
			response.getOutputStream().write(conteudoPdf);

			// Descarrega o conteudo do stream, forçando a escrita de qualquer
			// byte
			// ainda em buffer
			response.getOutputStream().flush();

			// Fecha o stream, liberando seus recursos
			response.getOutputStream().close();

			// Sinaliza ao JSF que a resposta HTTP para este pedido já foi
			// gerada
			fc.responseComplete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
