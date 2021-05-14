package br.com.protocoloeletronico.main;

import br.com.protocoloeletronico.util.HibernateUtil;

public class GeraTabela {

	public static void main(String args[]){
		HibernateUtil.getSessionFactory();
		HibernateUtil.getSessionFactory().close();
	}
}
