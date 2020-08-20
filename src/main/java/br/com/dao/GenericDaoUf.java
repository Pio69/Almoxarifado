package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.hibernate.Session;

import br.com.model.Acao;
import br.com.model.Pessoa;
import br.com.util.HibernateUtil;

public class GenericDaoUf {

	private EntityManager entityManager = HibernateUtil.getEntityManager();

	
	// METODO DE BUSCA DE OBJETO
			public Object search(Object entity, String estado) {

				EntityTransaction transaction = entityManager.getTransaction();
				transaction.begin();
				
				List<?> lista = entityManager.createQuery("from " + entity.getClass().getSimpleName() + " where sigla = '" + estado.trim() + "'").getResultList();

				transaction.commit();
				
				return lista.get(0);
						
			}


}
