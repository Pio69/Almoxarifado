package br.com.controller;

/*
 * SENAI
 * PSIN
 * MI-66
 * Objetivo: Realizar o empréstimo de peças que estão no almoxarifado atraves dos métodos e validações apresentadas na classe
 * Autores: Leonardo Pio, Kelvin Schneider, Guilherme Witkosky, Rafael Adriano 
 * Data: 06/08/2020
 * 
 * Alterações:
 * 
 * Nome: Rafael Adriano 
 * Data: 06/08/2020
 * Alterou: Documentação de código		
 * 
 */

import java.sql.Timestamp;
import java.util.List;
import com.google.zxing.Result;
import br.com.dao.GenericDao;
import br.com.dao.GenericDaoAcao;
import br.com.model.Acao;
import br.com.model.Pessoa;
import br.com.model.Produto;

public class LeitorController {

	/* 
	 * Retorno: Void
	 * Objetivo: Realizar o empréstimo das peças do almoxarifado, verificara se o produto ja foi devolvido ou não
	 *
	*/
	public void emprestar(Result result, Pessoa pessoa) {

		Integer idProd = Integer.parseInt(result.getText().toString());
		
		GenericDaoAcao genericDaoAcao = new GenericDaoAcao();

		List<Acao> acoes = (List<Acao>) genericDaoAcao.valida(new Acao(),idProd);

		if (acoes == null || acoes.isEmpty()) {
			System.out.println("Leitor: " + idProd);

			GenericDao genericDao;

			genericDao = new GenericDao();

			Acao acao = new Acao();

			Timestamp dataAtual = new Timestamp(System.currentTimeMillis());

			System.out.println(pessoa);
			
			// Ira setar se o produto foi emprestado caso nao tenha sido entregue
			
			acao.setPessoa(pessoa);
			acao.setProduto((Produto) genericDao.search(idProd, new Produto()));
			acao.setEntregue(false);
			acao.setDataRetirada(dataAtual);

			System.out.println(acao);

			genericDao.insert(acao);
			
			Produto prod = acao.getProduto();
			prod.setEmprestado(true);
			
			genericDao.update(prod);
			
		} else {
			System.out.println("Leitor: " + idProd);

			GenericDao genericDao;

			genericDao = new GenericDao();
			genericDaoAcao = new GenericDaoAcao();

			System.out.println((Acao) genericDaoAcao.search(new Acao(),pessoa, idProd));

			Acao acao = (Acao) genericDaoAcao.search(new Acao(),pessoa, idProd);
			
			acao.setEntregue(true);
			
			Timestamp dataAtual = new Timestamp(System.currentTimeMillis());
			
			acao.setDataEntrega(dataAtual);
			
			// Verificação referente a quando o produto foi devolvido

			System.out.println(acao);

			genericDao.update(acao);
			
			Produto prod = acao.getProduto();
			prod.setEmprestado(false);
			
			genericDao.update(acao);
		}
	}
	
}
