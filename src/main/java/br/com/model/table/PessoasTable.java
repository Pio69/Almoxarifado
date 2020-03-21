package br.com.model.table;

import javax.swing.table.AbstractTableModel;

import java.util.List;

import br.com.dao.GenericDao;
import br.com.model.Pessoa;

@SuppressWarnings({ "unchecked", "serial" })
public class PessoasTable extends AbstractTableModel {

	private GenericDao genericDao = new GenericDao();
	private String[] colunas = { "Id", "Nome", "CPF", "Entrada", "Saida", "Email", "Tipo" };
	private final int col_id = 0;
	private final int col_nome = 1;
	private final int col_cpf = 2;
	private final int col_entrada = 3;
	private final int col_saida = 4;
	private final int col_email = 5;
	private final int col_tipo = 6;
	private List<Pessoa> pessoas;

	public PessoasTable() {
		this.pessoas = (List<Pessoa>) genericDao.select(new Pessoa());
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
		return pessoas.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case col_id:
			return pessoas.get(rowIndex).getId();
		case col_nome:
			return pessoas.get(rowIndex).getNome();
		case col_cpf:
			return pessoas.get(rowIndex).getCpf();
		case col_entrada:
			return pessoas.get(rowIndex).getEntrada();
		case col_saida:
			return pessoas.get(rowIndex).getSaida();
		case col_email:
			return pessoas.get(rowIndex).getUsuario().getEmail();
		case col_tipo:
			return pessoas.get(rowIndex).getUsuario().getTipo();

		default:
			break;
		}
		return null;
	}

}
