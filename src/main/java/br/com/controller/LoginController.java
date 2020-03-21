package br.com.controller;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.com.dao.GenericDao;
import br.com.model.Pessoa;
import br.com.model.Usuario;
import br.com.view.Almoxarifado;
import br.com.view.Home;

public class LoginController {

	// LIMPA OS CAMPOS
	public static void clearLogin(JTextField txtEmail, JPasswordField pswSenha) {

		txtEmail.setText(null);
		pswSenha.setText(null);

	}

	// RETORNA O TIPO DE PESSOA
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

	// CHAMA O FRAME
	public static void callFrame(String tipo, JFrame login, Pessoa pessoa) {

		switch (tipo) {

		case "Gerente":
			Home home = new Home(pessoa);
			home.setVisible(true);
			login.setVisible(false);
			break;
		case "Professor":
			Almoxarifado almoxarifado = new Almoxarifado(pessoa);
			almoxarifado.setVisible(true);
			login.setVisible(false);
			break;

		}

	}
	
}
