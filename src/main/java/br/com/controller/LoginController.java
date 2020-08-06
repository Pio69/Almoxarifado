package br.com.controller;

/*
 * SENAI
 * PSIN
 * MI-66
 * Objetivo: Realizar login da pessoa no sistema, e também verificar o tipo de usuario
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

import java.util.List;


import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.com.dao.GenericDao;
import br.com.model.Pessoa;
import br.com.view.Alugar;
import br.com.view.Home;

public class LoginController {

	/* 
	 * Retorno: Void
	 * Objetivo: Limpar os campos de login
	 *
	*/
	public static void clearLogin(JTextField txtEmail, JPasswordField pswSenha) {

		txtEmail.setText(null);
		pswSenha.setText(null);

	}

	/* 
	 * Retorno: String
	 * Objetivo: Retornar o tipo de usuario
	 *
	*/
	public static String getTipo(JTextField txtEmail) {

		GenericDao genericDao = new GenericDao();
		@SuppressWarnings("unchecked")
		List<Pessoa> pessoas = (List<Pessoa>) genericDao.select(new Pessoa());

		for (Pessoa p : pessoas) {

			if (txtEmail.getText().equalsIgnoreCase(p.getUsuario().getEmail())) {

				return p.getUsuario().getTipo();

			}

		}
		
		return null;

	}

	/* 
	 * Retorno: Void
	 * Objetivo: Ira chamar o frame referente ao tipo de usuário
	 *
	*/
	public static void callFrame(String tipo, JFrame login, Pessoa pessoa) {

		switch (tipo) {

		case "Gerente":
			Home home = new Home(pessoa);
			home.setVisible(true);
			login.setVisible(false);
			break;
			
		case "Professor":
			Alugar alugar= new Alugar(pessoa);
			alugar.setVisible(true);
			login.setVisible(false);
			break;

		}

	}
	
}
