package br.com.gerentedocumento.test;

import org.junit.Test;

import br.com.gerentedocumento.util.UploadArquivoUtil;

public class AplicationTest {

	@Test
	public void removerArquivo(){
		UploadArquivoUtil upload = new UploadArquivoUtil();
		upload.removerArquivo("D:\\tmp\\Bloqueando Facebook Zentyal -Tutorial.pdf");
	}
}
