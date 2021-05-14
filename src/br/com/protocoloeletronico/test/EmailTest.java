package br.com.protocoloeletronico.test;

import java.util.regex.Pattern;

import org.junit.Test;

import br.com.protocoloeletronico.domain.Email;
import br.com.protocoloeletronico.util.EmailUtil;

public class EmailTest {

	@Test
	public void testEmail(){
		String remetente = "gustavouepb@gmail.com";
		String destinatario = "gustavodias1988@hotmail.com";
		String mensagem = "teste";
		String assunto = "teste";
		EmailUtil email = new EmailUtil();
		Email teste = new Email();
		teste.setFormEmail(remetente);
		teste.setToEmail(destinatario);
		teste.setMessage(mensagem);
		teste.setSubject(assunto);
		email.setEmail(teste);
		
		String emailArray[] = remetente.split(Pattern.quote ("@"));
		String usuarioEmail = emailArray[0];
		
		System.out.println(usuarioEmail);
	}
}
