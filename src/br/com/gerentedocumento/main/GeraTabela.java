package br.com.gerentedocumento.main;

import br.com.gerentedocumento.util.HibernateUtil;

public class GeraTabela {

	public static void main(String args[]){
		HibernateUtil.getSessionFactory();
		HibernateUtil.getSessionFactory().close();
	}
}
