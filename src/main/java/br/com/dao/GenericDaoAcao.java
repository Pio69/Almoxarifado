package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.model.Acao;
import br.com.util.HibernateUtil;

public class GenericDaoAcao {

	private EntityManager entityManager = HibernateUtil.getEntityManager();

	// METODO DE LISTAGEM
	public List<?> select(Object entity, Integer idPessoa) {

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		List<?> lista = entityManager
				.createQuery("from " + entity.getClass().getName() + " where pessoa_id = " + idPessoa).getResultList();

		transaction.commit();
		return lista;

	}

	public Acao validaProduto(Object entity, Integer qrCode) {

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		Acao acao = (Acao) entityManager.createQuery("from " + entity.getClass().getName() + " where produto_id = " + qrCode + " and entregue = 0").getSingleResult();

		transaction.commit();

		return acao;

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
