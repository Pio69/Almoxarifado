package br.com.controller;

import java.sql.Timestamp;
import java.util.List;

import com.google.zxing.Result;

import br.com.dao.GenericDao;
import br.com.dao.GenericDaoAcao;
import br.com.model.Acao;
import br.com.model.Pessoa;
import br.com.model.Produto;

public class LeitorController {

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

			System.out.println(acao);

			genericDao.update(acao);
			
			Produto prod = acao.getProduto();
			prod.setEmprestado(false);
			
			genericDao.update(acao);
		}
	}
	
}
