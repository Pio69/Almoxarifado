package br.com.model.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.dao.GenericDao;
import br.com.model.Produto;

public class ProdutoTable extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GenericDao genericDao = new GenericDao();
	private String[] colunas = { "Nome", "Descricao", "Estado" };
	private final int col_nome = 0;
	private final int col_desc = 1;
	private final int col_estado = 2;
	private List<Produto> produto;

	public ProdutoTable() {
		this.produto = (List<Produto>) genericDao.select(new Produto());
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
		return produto.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {

		case col_nome:
			return produto.get(rowIndex).getNomeProduto();
		case col_desc:
			return produto.get(rowIndex).getDescProduto();
		case col_estado:
			return (produto.get(rowIndex).isEmprestado() ? "Indisponivel" : "Disponivel");

		default:
			break;
		}
		return null;
	}

}
