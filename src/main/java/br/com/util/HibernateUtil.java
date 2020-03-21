package br.com.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

public class HibernateUtil {

	public static EntityManagerFactory factory = null;

	static {

		init();

	}

	private static void init() {

		try {

			if (factory == null) {

				factory = Persistence.createEntityManagerFactory("Almoxarifado");

			}

		} catch (Exception e) {

			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possivel iniciar conexão", "Erro de conexão",
					JOptionPane.ERROR_MESSAGE);

		}

	}

	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

	public static Object getPrimaryKey(Object entity) {
		return factory.getPersistenceUnitUtil().getIdentifier(entity);
	}

	public static Object getSessionFactory() {
		// TODO Auto-generated method stub
		return null;
	}

}
