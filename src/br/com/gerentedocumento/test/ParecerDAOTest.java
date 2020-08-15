package br.com.gerentedocumento.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

import br.com.gerentedocumento.dao.ParecerDAO;
import br.com.gerentedocumento.domain.Parecer;

public class ParecerDAOTest {

	@Test
	public void buscarPorPerido() throws ParseException {
		ParecerDAO pdao = new ParecerDAO();

		String dataRecebida = "01/03/2018";
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date inicio = formato.parse(dataRecebida);
		Date fim = new Date();
		List<Parecer> listaPareceres = pdao.buscarPorPeriodo(inicio, fim);
		int mes = 12;
		int conformidade = 0;
		int conforParcial = 0;
		int desconformidade = 0;
		int semStatus = 0;

		for (int j = 1; j <= mes; j++) {
			for (int i = 0; i < listaPareceres.size(); i++) {
				Parecer parecerTemp = (Parecer) listaPareceres.get(i);
				GregorianCalendar dataCal = new GregorianCalendar();
				dataCal.setTime(parecerTemp.getDataEntrada());
		    	
		    	int month = dataCal.get(Calendar.MONTH);
				if ( month+1 == j) {
					if (parecerTemp.getStatus().equals("conformidade")) {
						conformidade = conformidade + 1;
					}
					if (parecerTemp.getStatus().equals("Conformidade parcial")) {
						conforParcial = conforParcial + 1;
					}
					if (parecerTemp.getStatus().equals("desconformidade")) {
						desconformidade = desconformidade + 1;
					}else{
						semStatus++;
					}
				}
			}
			System.out.println("Mês: " + j + "\n conformidade: " + conformidade + "\n Conf. Parcial: " + conforParcial
					+ "\n desconformidade: " + desconformidade + "\n Sem Status: " + semStatus);
			conformidade = 0;
			conforParcial = 0;
			desconformidade = 0;
			semStatus = 0;
		}

	}
}
