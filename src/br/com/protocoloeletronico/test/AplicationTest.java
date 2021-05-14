package br.com.protocoloeletronico.test;

import org.junit.Test;

import br.com.protocoloeletronico.util.UploadArquivoUtil;

public class AplicationTest {

	@Test
	public void removerArquivo(){
		UploadArquivoUtil upload = new UploadArquivoUtil();
		upload.removerArquivo("D:\\tmp\\Bloqueando Facebook Zentyal -Tutorial.pdf");
	}
}
