package br.com.util;

/*
 * SENAI
 * PSIN
 * MI-66
 * Objetivo: Realizar os métodos que serão aplicados para fazer o crud no banco de dados
 * Data: 06/08/2020
 * 
 * Alterações:
 * 
 * Nome: Rafael Adriano 
 * Data: 06/08/2020
 * Alterou: Documentação de código		
 * 
 */

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

public class HibernateUtil {

	// Atributo que recebo a conexão com o banco de dados
	public static EntityManagerFactory factory = null;

	/* 
	 * Objetivo: Caso o metodo init ja possua valor nao será necessario entrar no mesmo novamente
	 * Retorno: Conexao com banco de dados
	 *
	*/
	static {

		init();

	}

	/* 
	 * Retorno: vois
	 * Objetivo: Dar valor ao atributo factory para estabelecer conexao com o banco de dados
	 *
	*/
	private static void init() {

		try {

			// Responsavel em passar a conexao para factory
			if (factory == null) {

				factory = Persistence.createEntityManagerFactory("Almoxarifado");

			}

		} catch (Exception e) {

			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possivel iniciar conexão", "Erro de conexão",
					JOptionPane.ERROR_MESSAGE);

		}

	}

	/* 
	 * Retorno: Atributo Factory poluido
	 * Objetivo: Retornar os dados para utilização em outras classes
	 * 
	*/
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

	/* 
	 * Retorno: Objeto como chave primaria
	 * Objetivo: Setar a chave primaria
	 * 
	*/
	public static Object getPrimaryKey(Object entity) {
		return factory.getPersistenceUnitUtil().getIdentifier(entity);
	}

	public static Object getSessionFactory() {
		
		return null;
	}

}
