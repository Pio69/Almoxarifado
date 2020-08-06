package br.com.main;

import java.awt.EventQueue;

import br.com.view.Login;

public class Main {

	/*
	 * 05/08/2020 - Vinícius Silva Sena, Leonador Pio, Kelvin Schneider, Guilherme Witkosky, Rafael Adriano - Normativas: - só são aceitos
	 * emails @gmail.com, @hotmail.com e @senai.sc; - a idade maxima é 80 anos; -
	 * não podem haver pessoas com email ou cpf iguais; - é necessario selecionar um
	 * item para poder chamar a tela de edicao;
	 * 
	 * O programa como sua principal função é exercer a leitura de código de barras, qrcode. Assim, através dessa leitura buscando manter a organização
	 * de um almoxarifado.
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
