/*
*SENAI 
*PSIN
*MI-66
*Objetivo: Vizualisação dos campos necessarios para o cadastro de Usuario
*Autores: Guilherme Witkosky, Kelvin Schneider, Leonardo Pio, Rafael Adriano e Vinicius Sena
*Data: 06/08/2020
*
*Alterações:
*Nome: Kelvin Schneider
*Data: 06/08/2020
*Alterou: Documentação de código
*/

package br.com.view;

import javax.swing.JFrame;


import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.controller.PessoaController;
import br.com.model.Pessoa;
import br.com.model.Usuario;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.awt.Toolkit;

public class Cadastrar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtEmail;
	private JPasswordField pswSenha;
	private JTextField txtEntrada;
	private JTextField txtSaida;

	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;

	/* 
	 *
	 * Objetivo: Carregar os componentes presentes na tela
	 *
	*/
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Cadastrar( Pessoa pessoa) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cadastrar.class.getResource("/imagens/weg-logo.png")));
		setTitle("Cadastrar");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 488);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// CAMPOS DE TEXTO
		txtNome = new JTextField();
		txtNome.setHorizontalAlignment(SwingConstants.CENTER);
		txtNome.setFont(new Font("3ds", Font.PLAIN, 14));
		txtNome.setBounds(191, 39, 254, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		txtCpf = new JTextField();
		txtCpf.setHorizontalAlignment(SwingConstants.CENTER);
		txtCpf.setFont(new Font("3ds", Font.PLAIN, 14));
		txtCpf.setBounds(191, 80, 254, 20);
		contentPane.add(txtCpf);
		txtCpf.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmail.setFont(new Font("3ds", Font.PLAIN, 14));
		txtEmail.setBounds(191, 128, 254, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		// LABELS PARA CAMPOS DE TEXTO
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("3ds", Font.BOLD, 15));
		lblNome.setBounds(191, 22, 64, 14);
		contentPane.add(lblNome);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("3ds", Font.BOLD, 15));
		lblCpf.setBounds(191, 63, 64, 14);
		contentPane.add(lblCpf);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("3ds", Font.BOLD, 15));
		lblEmail.setBounds(191, 111, 64, 14);
		contentPane.add(lblEmail);

		// BOTAO DE CADASTRAR OS DADOS
		JButton btnInsert = new JButton("Cadastrar");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Pessoa pessoas = new Pessoa();
				Usuario usuario = new Usuario();

				/*
				 * Inseri os dados coletados na model de usuario
				 * */
				usuario.setEmail(txtEmail.getText());
				usuario.setSenha(String.copyValueOf(pswSenha.getPassword()));
				usuario.setTipo(comboBox.getSelectedItem().toString());

				pessoas.setId(0);
				pessoas.setNome(txtNome.getText());
				pessoas.setCpf(txtCpf.getText());
				pessoas.setEntrada(txtEntrada.getText());
				pessoas.setSaida(txtSaida.getText());
				pessoas.setUsuario(usuario);

					// VERIFICA SE ACAO FOI REALIZADA PARA APAGAR OS CAMPOS
					if (PessoaController.genericFunction(pessoas, Cadastrar.this)) {
						PessoaController.clear(txtNome, txtCpf, txtEmail, pswSenha, txtEntrada, txtSaida);
					}
					

			}
		});
		btnInsert.setForeground(Color.WHITE);
		btnInsert.setBackground(Color.BLACK);
		btnInsert.setFont(new Font("3ds", Font.BOLD, 14));
		btnInsert.setBounds(193, 368, 101, 23);
		contentPane.add(btnInsert);

		// LIMPA OS CAMPOS
		JButton btnClear = new JButton("Limpar");
		btnClear.setForeground(Color.WHITE);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PessoaController.clear(txtNome, txtCpf, txtEmail, pswSenha, txtEntrada, txtSaida);
			}
		});
		btnClear.setBackground(Color.BLACK);
		btnClear.setFont(new Font("3ds", Font.BOLD, 14));
		btnClear.setBounds(346, 368, 101, 23);
		contentPane.add(btnClear);

		// BOTAO PARA VOLTAR PARA A TELA PRINCIPAL
		JButton btnReturn = new JButton("Voltar");
		btnReturn.setForeground(Color.WHITE);
		btnReturn.setBackground(Color.BLACK);
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Home home = new Home(pessoa);
				home.setVisible(true);
				setVisible(false);

			}
		});
		btnReturn.setFont(new Font("3ds", Font.BOLD, 14));
		btnReturn.setBounds(46, 425, 89, 23);
		contentPane.add(btnReturn);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("3ds", Font.BOLD, 15));
		lblSenha.setBounds(191, 160, 64, 14);
		contentPane.add(lblSenha);

		pswSenha = new JPasswordField();
		pswSenha.setHorizontalAlignment(SwingConstants.CENTER);
		pswSenha.setFont(new Font("3ds", Font.PLAIN, 14));
		pswSenha.setBounds(191, 179, 254, 20);
		contentPane.add(pswSenha);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("3ds", Font.BOLD, 15));
		lblTipo.setBounds(191, 299, 64, 14);
		contentPane.add(lblTipo);

		// COMBOBOX DE TIPO DE USUARIO
		comboBox = new JComboBox();
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("3ds", Font.PLAIN, 14));
		comboBox.addItem("Professor");
		comboBox.addItem("Gerente");
		comboBox.setBounds(191, 324, 254, 20);
		contentPane.add(comboBox);

		JLabel lblEntrada = new JLabel("Entrada");
		lblEntrada.setFont(new Font("3ds", Font.BOLD, 15));
		lblEntrada.setBounds(191, 210, 64, 14);
		contentPane.add(lblEntrada);

		txtEntrada = new JTextField();
		txtEntrada.setHorizontalAlignment(SwingConstants.CENTER);
		txtEntrada.setFont(new Font("3ds", Font.PLAIN, 14));
		txtEntrada.setColumns(10);
		txtEntrada.setBounds(191, 227, 254, 20);
		contentPane.add(txtEntrada);

		JLabel lblSaida = new JLabel("Saida");
		lblSaida.setFont(new Font("3ds", Font.BOLD, 15));
		lblSaida.setBounds(191, 258, 64, 14);
		contentPane.add(lblSaida);

		txtSaida = new JTextField();
		txtSaida.setHorizontalAlignment(SwingConstants.CENTER);
		txtSaida.setFont(new Font("3ds", Font.PLAIN, 14));
		txtSaida.setColumns(10);
		txtSaida.setBounds(191, 275, 254, 20);
		contentPane.add(txtSaida);

	}
}
