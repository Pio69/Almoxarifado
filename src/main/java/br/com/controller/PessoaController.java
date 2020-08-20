/*
 * SENAI
 * PSIN
 * MI-66
 * Objetivo: Realizar os métodos referente a entidade pessoa, limpar campos, atualizar dados e também verifica-los
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
package br.com.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import br.com.dao.GenericDao;
import br.com.model.Pessoa;
import br.com.model.Usuario;
import br.com.model.table.PessoasTable;

@SuppressWarnings("unchecked")
public class PessoaController {

	/* 
	 * Retorno: Int
	 * Objetivo: Retorna a idade da entidade pessoa
	 * Parametro de Entrada:
	 * 	  Ano de nascimento
	 * Parametro de Saida:
	 * 	  Idade da pessoa	
	 *
	*/
	public static int getIdade(int anoNasc) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		int anoAtual = Integer.parseInt(sdf.format(System.currentTimeMillis()));

		return anoAtual - anoNasc;

	}

	/* 
	 * Retorno: Void
	 * Objetivo: Limpas os campos de pessoa
	 * Parametro de Entrada: 
	 * 	  Nome, CPF, email, senha, hora entrada, hora saida
	*/
	public static void clear(JTextField txtNome, JTextField txtCpf, JTextField txtEmail, JPasswordField pswSenha,
			JTextField txtEntrada, JTextField txtSaida, JTextField txtCep,  JTextField txtLogradouro, JTextField txtComplemento, JTextField txtBairro, JTextField txtLocalidade) {

		txtNome.setText(null);
		txtCpf.setText(null);
		txtEmail.setText(null);
		txtEntrada.setText(null);
		txtSaida.setText(null);
		pswSenha.setText(null);
		txtCep.setText(null);
		txtLogradouro.setText(null);
		txtComplemento.setText(null);
		txtBairro.setText(null);
		txtLocalidade.setText(null);

	}

	/* 
	 * Retorno: Void
	 * Objetivo: Limpas os campos de pessoa após o update
	 *  * Parametro de Entrada: 
	 * 	  Nome, CPF, email, senha, hora entrada, hora saida
	 *
	*/
	public static void clearUpdate(JTextField txtNome, JTextField txtCpf, JTextField txtEmail, JTextField pswSenha,
			JTextField txtEntrada, JTextField txtSaida, JTextField txtCep,  JTextField txtLogradouro, 
			JTextField txtComplemento, JTextField txtBairro, JTextField txtLocalidade) {

		txtNome.setText(null);
		txtCpf.setText(null);
		txtEmail.setText(null);
		txtEntrada.setText(null);
		txtSaida.setText(null);
		pswSenha.setText(null);
		txtCep.setText(null);
		txtLogradouro.setText(null);
		txtComplemento.setText(null);
		txtBairro.setText(null);
		txtLocalidade.setText(null);

	}

	/* 
	 * Retorno: Void
	 * Objetivo: Remover uma pessoa juntamente do seu usario no programa
	 * Parametro de Entrada:
	 * 	  int ID
	 *
	*/
	public static void remove(JTable tablePessoas, Pessoa user, PessoasTable pessoasTable, JButton btnUpdate,
			JButton btnRemove) {

		GenericDao genericDao = new GenericDao();
		Pessoa pessoa = new Pessoa();
		List<Pessoa> pessoas = (List<Pessoa>) genericDao.select(pessoa);
		pessoa = (Pessoa) genericDao.search(pessoas.get(tablePessoas.getSelectedRow()).getId(), pessoa);

		if (pessoa != user) {
			genericDao.remove(pessoa);
			genericDao.remove(pessoa.getUsuario());
			pessoasTable = new PessoasTable();
			tablePessoas.setModel(pessoasTable);

			btnUpdate.setEnabled(false);
			btnRemove.setEnabled(false);
		} else {
			JOptionPane.showMessageDialog(null, "Você não pode remover a si mesmo", "Erro", JOptionPane.ERROR_MESSAGE);
		}

	}

	/* 
	 * Retorno: Boolean
	 * Objetivo: Verificar se a pessoa ja existe no sistema 
	 * Parametro de Entrada:
	 * 	  int ID
	*/
	public static boolean exist(Pessoa pessoa) {

		GenericDao genericDao = new GenericDao();

		for (Pessoa pessoas : (List<Pessoa>) genericDao.select(new Pessoa())) {

			if (pessoas.getId() != pessoa.getId()
					&& (pessoas.getUsuario().getEmail().equals(pessoa.getUsuario().getEmail())
							|| pessoas.getCpf().equalsIgnoreCase(pessoa.getCpf()))) {

				return true;

			}

		}

		return false;

	}

	/* 
	 * Retorno: Boolean
	 * Objetivo: Verificar se existe algum campo nulo
	 * Parametro de Entrada:
	 * 	  Nome da pessoa
	 * Parametro de Saida:
	 * 	  Boolean
	*/
	public static boolean isNull(Pessoa pessoa) {

		if (pessoa.getNome().trim().equals("") || pessoa.getCpf().equals("")
				|| pessoa.getUsuario().getEmail().equals("")) {

			return true;

		}

		return false;

	}

	/* 
	 * Retorno: Boolean
	 * Objetivo: Verificar se o CPF possui 11 dígitos
	 * Parametro de Entrada:
	 * 	  CPF
	 * Parametro de Saida:
	 * 	  Boolean
	 *
	*/
	public static boolean isCpf(Pessoa pessoa) {

		if (pessoa.getCpf().length() == 11) {
			return true;
		}

		return false;

	}

	/* 
	 * Retorno: Boolean
	 * Objetivo: Verificar se o campo de entrada é um tipo de email válido
	 * Parametro de Entrada:
	 * 	  Email
	 * Parametro de Saida:
	 * 	  Boolean
	 *
	*/
	public static boolean isEmail(Pessoa pessoa) {

		if (pessoa.getUsuario().getEmail().contains("@gmail.com")
				|| pessoa.getUsuario().getEmail().contains("@hotmail.com")
				|| pessoa.getUsuario().getEmail().contains("@senai.sc")) {

			return true;

		}

		return false;

	}
	
	/* 
	 * Retorno: Boolean
	 * Objetivo: Realizar cadastro ou edição, havendo esta variação de acordo com a decisão do usuário
	 * Parametro de Entrada:
	 * 	  Campos de pessoa
	 * Parametro de Saida:
	 * 	  Switch case de telas
	 *
	 *
	*/
	public static boolean genericFunction(Pessoa pessoa, JFrame genericFrame) {

		// VERIFICA SE OS CAMPOS SAO NULOS
		if (isNull(pessoa) == true) {
			JOptionPane.showMessageDialog(null, "É necessario preencher todos os campos", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;

		}

		// VERIFICA SE A PESSOA JA EXISTE
		if (exist(pessoa) == true) {
			JOptionPane.showMessageDialog(null, "Essa pessoa ja foi cadastrada", "Erro", JOptionPane.ERROR_MESSAGE);
			return false;

		}

		// VERIFICA SE O EMAIL É VALIDO
		if (isEmail(pessoa) == false) {
			JOptionPane.showMessageDialog(null,
					"Email invalido, tente complementar com @gmail.com, @hotmail.com ou @senai.sc", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;

		}

		// VERIFICA SE O CPF POSSUI 11 NUMEROS E NENHUMA LETRA
		if (isCpf(pessoa) == false) {

			JOptionPane.showMessageDialog(null, "Um CPF precisa conter 11 digitos", "Erro", JOptionPane.ERROR_MESSAGE);
			return false;

		}

		// REALIZA O INSERT CASO SEJA POSSIVEL
		GenericDao genericDao = new GenericDao();

		switch (genericFrame.getTitle()) {
		case "Editar":
			genericDao.insert(pessoa.getUsuario());
			genericDao.insert(pessoa.getEndereco());
			genericDao.update(pessoa);
			return true;
		case "Cadastrar":
			genericDao.insert(pessoa.getUsuario());
			genericDao.insert(pessoa.getEndereco());
			genericDao.insert(pessoa);
			return true;
		}

		return false;

	}

}
