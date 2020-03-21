package br.com.main;

import java.awt.EventQueue;

import br.com.view.Login;

public class Main {

	/*
	 * 18/02/2020 - Vinícius Silva Sena - Normativas: - só são aceitos
	 * emails @gmail.com, @hotmail.com e @senai.sc; - a idade maxima é 80 anos; -
	 * não podem haver pessoas com email ou cpf iguais; - é necessario selecionar um
	 * item para poder chamar a tela de edicao;
	 * 
	 */

	// CHAMA O FRAME HOME
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
