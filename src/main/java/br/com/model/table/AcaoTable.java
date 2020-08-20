package br.com.model.table;

/*
SENAI
PSIN
MI-66
Objetivo:
Autores: Leonardo Pio, Kelvin Schneider, Guilherme Witkosky, Rafael Adriano e Vinicius Silva Sena
Data: 06/08/2020

Alterações: 
Nome: Vinícius Sena
Alterou: Documentou o codigo

Objetivo: Classe responsavel pelo modelo de dados da listagem de ações

*/

import java.util.List;
		

import javax.swing.table.AbstractTableModel;

import br.com.dao.GenericDao;
import br.com.dao.GenericDaoAcao;
import br.com.model.Acao;
import br.com.model.Produto;

public class AcaoTable extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private GenericDao genericDao = new GenericDao();
	private GenericDaoAcao genericDaoAcao = new GenericDaoAcao();
	private String[] colunas = {"ID" ,"Nome Produto", "Funcionario", "Hora Retirada", "Estado" };
	private final int col_id = 0;
	private final int col_nome = 1;
	private final int col_func = 2;
	private final int col_hora = 3;
	private final int col_estado = 4;
	private List<Acao> acoes;

	public AcaoTable(boolean chefia,Integer idPessoa) {
		if(chefia) {			
			this.acoes = (List<Acao>) genericDao.select(new Acao());
		}else {
			this.acoes = (List<Acao>) genericDaoAcao.select(new Acao(),idPessoa);
		}
	}

	public AcaoTable() {
		
		acoes = (List<Acao>) genericDao.select(new Acao());
		
	}

	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return acoes.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {

		case col_id:
			return acoes.get(rowIndex).getId();
		case col_nome:
			return acoes.get(rowIndex).getProduto().getNomeProduto();
		case col_func:
			return acoes.get(rowIndex).getPessoa().getNome();
		case col_hora:
			return acoes.get(rowIndex).getDataRetirada();
		case col_estado:
			return (acoes.get(rowIndex).isEntregue() ? "Entregue" : "Não Entregue");

		default:
			break;
		}
		return null;
	}

}