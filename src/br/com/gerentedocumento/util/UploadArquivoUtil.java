package br.com.gerentedocumento.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class UploadArquivoUtil {

	public String upload(String pasta, String nomeDoArquivo, InputStream arquivoCarregado){
		/*String caminhoReal = FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath(pasta);*/
		
		String caminhoArquivo = pasta + nomeDoArquivo;
		File novoArquivo = new File(caminhoArquivo);
		try {
			FileOutputStream saida = new FileOutputStream(novoArquivo);
			copiar(arquivoCarregado, saida);
		} catch (FileNotFoundException e) {
			FacesUtil.addMsgErro("Arquivo não encontrado! " + e.getMessage());
		}
		
		return caminhoArquivo;
	}
	
	private void copiar(InputStream origem, OutputStream destino) {
		int bite = 0;
		byte[] tamanhoMaximo = new byte[1024 * 1024]; // 1MB
		try {
			// enquanto bytes forem sendo lidos
			while ((bite = origem.read(tamanhoMaximo)) >= 0) {
				// pegue o byte lido e escreva no destino
				destino.write(tamanhoMaximo, 0, bite);
			}
		} catch (IOException ex) {
			FacesUtil.addMsgErro("Erro ao carregar o arquivo " + ex.getMessage());
		}
	}
	
	public void removerArquivo(String caminho){
		File arquivoTemp = new File(caminho);
		
		try{
			// Make sure the file or directory exists and isn't write protected
		    if (!arquivoTemp.exists())
		        throw new IllegalArgumentException(
		            "Delete: no such file or directory: " + caminho);

		      if (!arquivoTemp.canWrite())
		        throw new IllegalArgumentException("Delete: write protected: "
		            + caminho);

		      // If it is a directory, make sure it is empty
		      if (arquivoTemp.isDirectory()) {
		        String[] files = arquivoTemp.list();
		        if (files.length > 0)
		          throw new IllegalArgumentException(
		              "Delete: directory not empty: " + caminho);
		      }

		      // Attempt to delete it
		      boolean success = arquivoTemp.delete();

		      if (!success)
		        throw new IllegalArgumentException("Delete: deletion failed");
		}catch(Exception ex){
			FacesUtil.addMsgErro("Erro ao carregar o arquivo " + ex.getMessage());
		}
	}
}
