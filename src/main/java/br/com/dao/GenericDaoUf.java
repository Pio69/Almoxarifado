package br.com.dao;

/*
SENAI
PSIN
MI-66
Autores: Leonardo Pio, Kelvin Schneider, Guilherme Witkosky, Rafael Adriano e Vinicius Silva Sena
Data: 06/08/2020

Alterações: 
Nome: Leonardo Pio
Alteração: Documentação de código

Objetivo: Classe responsavel pelos metodos necessarios pela classe de UnidadeFederativa

*/

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
