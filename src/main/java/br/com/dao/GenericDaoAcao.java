package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.hibernate.Session;

import br.com.model.Acao;
import br.com.model.Pessoa;
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

	public Object busca(Pessoa pessoa, Integer qrCode) {
	
		String hql = "from acao where pessoa_id = :idPessoa"
				+ "	 	and produto_id = :idQrCode"
				+ "		and entregue = 0";
		  /*String hql = "from Restaurante r where r.nome like :nome" +
		    " and r.endereco like :endereco" +
		    " and r.tipoDeComida.nome like :tipoDeComida";*/
		  return entityManager.createQuery(hql)
		   .setParameter("idPessoa", pessoa.getId())
		   .setParameter("idQrCode", qrCode)
		   .getResultList();
		}
	
	// METODO DE REMOCAO
		public Object search(Object entity, Pessoa pessoa, Integer qrCode) {

			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();

			List<?> lista = entityManager.createQuery("from " + entity.getClass().getName() + " where produto_id = " + qrCode + "and entregue = 0").getResultList();

			transaction.commit();
			
			return lista.get(0);
					
		}

	public List<?> valida(Object entity, Integer idQRCode) {

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		List<?> lista = entityManager
				.createQuery("from " + entity.getClass().getName() + " where produto_id = " + idQRCode + "and entregue = 0").getResultList();

		transaction.commit();

		return lista;

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
