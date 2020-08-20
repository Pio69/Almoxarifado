package br.com.dao;

/*
SENAI
PSIN
MI-66
Autores: Leonardo Pio, Kelvin Schneider, Guilherme Witkosky, Rafael Adriano e Vinicius Silva Sena
Data: 06/08/2020

Alterações: 
Nome: Vinícius Sena
Alterou: Documentou o codigo

Objetivo: Classe responsavel pela validação do login

*/

import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.com.model.Pessoa;
import br.com.model.Usuario;

@SuppressWarnings("unchecked")
public class LoginDao {

	public Pessoa login(JTextField txtEmail, JPasswordField pswSenha) {

		GenericDao genericDao = new GenericDao();
		List<Pessoa> pessoas = (List<Pessoa>) genericDao.select(new Pessoa());

		for (Pessoa p : pessoas) {
			if (p.getUsuario().getEmail().equals(txtEmail.getText())
					&& Arrays.equals(p.getUsuario().getSenha().toCharArray(), pswSenha.getPassword())) {
				return p;
			}
		}

		JOptionPane.showMessageDialog(null, "Login invalido", "Erro", JOptionPane.ERROR_MESSAGE);

		return null;

	}
}
