package br.com.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.util.HibernateUtil;

public class GenericDao {

	private EntityManager entityManager = HibernateUtil.getEntityManager();

	// METODO DE CADASTRO
	public void insert(Object entity) {

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(entity);

		transaction.commit();

	}

	// METODO DE ATUALIZCAO
	public void update(Object entity) {

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(entity);

		transaction.commit();

	}

	// METODO DE PESQUISA ESPECIFICO
	public Object search(int id, Object entity) {

		Object e = entityManager.find(entity.getClass(), id);
		return e;

	}

	// RETORNA O ULTIMO ID INSERIDO
	public int lastInsertId(Object entit) {

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		String index = entityManager
				.createNativeQuery("select max(id) from " + entit.getClass().getSimpleName().toLowerCase())
				.getResultList().toString();

		return Integer.parseInt(index.substring(1, index.length() - 1));
	}

	// METODO DE REMOCAO
	public void remove(Object entity) {

		Object id = HibernateUtil.getPrimaryKey(entity);
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager
				.createNativeQuery(
						"delete from " + entity.getClass().getSimpleName().toLowerCase() + " where id = " + id)
				.executeUpdate();
		transaction.commit();
	}

	// METODO DE LISTAGEM
	public List<?> select(Object entity) {

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		List<?> lista = entityManager.createQuery("from " + entity.getClass().getName()).getResultList();

		transaction.commit();
		return lista;

	}

}
