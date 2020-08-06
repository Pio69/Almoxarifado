/*SENAI 
*PSIN
*MI-66
*Objetivo: Vizualisação dos campos necessarios para a edição de Usuario
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
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Toolkit;

public class Editar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtEmail;
	private JTextField txtEntrada;
	private JTextField txtSaida;
	private JTextField txtSenha;
	private JComboBox<String> comboBox;

	/* 
	 * Objetivo: Carregar os componentes presentes na tela
	 *
	*/
	public Editar( Pessoa pessoa,  Pessoa user) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Editar.class.getResource("/imagens/weg-logo.png")));
		setTitle("Editar");
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
		txtNome.setColumns(10);
		txtNome.setBounds(198, 75, 254, 20);
		contentPane.add(txtNome);

		txtCpf = new JTextField();
		txtCpf.setHorizontalAlignment(SwingConstants.CENTER);
		txtCpf.setFont(new Font("3ds", Font.PLAIN, 14));
		txtCpf.setColumns(10);
		txtCpf.setBounds(198, 116, 254, 20);
		contentPane.add(txtCpf);

		txtEmail = new JTextField();
		txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmail.setFont(new Font("3ds", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		txtEmail.setBounds(198, 164, 254, 20);
		contentPane.add(txtEmail);
		
		txtSenha = new JTextField();
		txtSenha.setText((String) null);
		txtSenha.setHorizontalAlignment(SwingConstants.CENTER);
		txtSenha.setFont(new Font("3ds", Font.PLAIN, 14));
		txtSenha.setColumns(10);
		txtSenha.setBounds(198, 212, 254, 20);
		contentPane.add(txtSenha);
		
		txtEntrada = new JTextField();
		txtEntrada.setText((String) null);
		txtEntrada.setHorizontalAlignment(SwingConstants.CENTER);
		txtEntrada.setFont(new Font("3ds", Font.PLAIN, 14));
		txtEntrada.setColumns(10);
		txtEntrada.setBounds(198, 254, 254, 20);
		contentPane.add(txtEntrada);
		
		txtSaida = new JTextField();
		txtSaida.setText((String) null);
		txtSaida.setHorizontalAlignment(SwingConstants.CENTER);
		txtSaida.setFont(new Font("3ds", Font.PLAIN, 14));
		txtSaida.setColumns(10);
		txtSaida.setBounds(198, 302, 254, 20);
		contentPane.add(txtSaida);
		
		comboBox = new JComboBox<String>();
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("3ds", Font.PLAIN, 14));
		comboBox.addItem("Professor");
		comboBox.addItem("Gerente");
		comboBox.setBounds(198, 357, 254, 20);
		contentPane.add(comboBox);

		// SETA OS VALORES PARA OS CAMPOS
		txtNome.setText(pessoa.getNome());
		txtEmail.setText(pessoa.getUsuario().getEmail());
		txtCpf.setText(pessoa.getCpf());
		txtEntrada.setText(pessoa.getEntrada());
		txtSaida.setText(pessoa.getSaida());
		txtSenha.setText(pessoa.getUsuario().getSenha());
		comboBox.setSelectedItem(pessoa.getUsuario().getTipo());

		// LABELS PARA OS CAMPOS DE TEXTO
		JLabel label = new JLabel("Nome");
		label.setFont(new Font("3ds", Font.BOLD, 15));
		label.setBounds(198, 58, 46, 14);
		contentPane.add(label);

		JLabel label_1 = new JLabel("CPF");
		label_1.setFont(new Font("3ds", Font.BOLD, 15));
		label_1.setBounds(198, 99, 46, 14);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("Email");
		label_2.setFont(new Font("3ds", Font.BOLD, 15));
		label_2.setBounds(198, 147, 46, 14);
		contentPane.add(label_2);

		//BOTAO PARA SALVAR AS ALTERACOES
		JButton btnUpdate = new JButton("Salvar");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/*
				 *  inseri os dados coletados na model de usuario
				 * */
				Usuario usuario = pessoa.getUsuario();
				usuario.setEmail(txtEmail.getText());
				usuario.setSenha(txtSenha.getText());
				usuario.setTipo(comboBox.getSelectedItem().toString());
				
				Pessoa pessoas = new Pessoa();
				pessoas.setId(pessoa.getId());
				pessoas.setNome(txtNome.getText());
				pessoas.setCpf(txtCpf.getText());
				pessoas.setEntrada(txtEntrada.getText());
				pessoas.setSaida(txtSaida.getText());
				pessoas.setUsuario(usuario);

				// VERIFICA SE ACAO FOI REALIZADA PARA APAGAR OS CAMPOS
				if (PessoaController.genericFunction(pessoas, Editar.this)) {
					Home home = new Home(user);
					home.setVisible(true);
					setVisible(false);
				}

			}
		});
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("3ds", Font.BOLD, 14));
		btnUpdate.setBackground(Color.BLACK);
		btnUpdate.setBounds(198, 397, 101, 23);
		contentPane.add(btnUpdate);

		// LIMPA OS CAMPOS
		JButton btnClear = new JButton("Limpar");
		btnClear.setForeground(Color.WHITE);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PessoaController.clearUpdate(txtNome, txtCpf, txtEmail, txtSenha, txtEntrada, txtSaida);
			}
		});
		btnClear.setFont(new Font("3ds", Font.BOLD, 14));
		btnClear.setBackground(Color.BLACK);
		btnClear.setBounds(351, 397, 101, 23);
		contentPane.add(btnClear);

		// RETORNA PARA A TELA PRINCIPAL
		JButton btnReturn = new JButton("Voltar");
		btnReturn.setBackground(Color.BLACK);
		btnReturn.setForeground(Color.WHITE);
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Home home = new Home(user);
				home.setVisible(true);
				setVisible(false);

			}
		});
		btnReturn.setFont(new Font("3ds", Font.BOLD, 14));
		btnReturn.setBounds(46, 425, 89, 23);
		contentPane.add(btnReturn);

		JLabel lblSenha = new JLabel("Entrada");
		lblSenha.setFont(new Font("3ds", Font.BOLD, 15));
		lblSenha.setBounds(198, 237, 59, 14);
		contentPane.add(lblSenha);

		JLabel lblSaida = new JLabel("Saida");
		lblSaida.setFont(new Font("3ds", Font.BOLD, 15));
		lblSaida.setBounds(198, 285, 59, 14);
		contentPane.add(lblSaida);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("3ds", Font.BOLD, 15));
		lblTipo.setBounds(198, 333, 59, 14);
		contentPane.add(lblTipo);

		JLabel lblSenha_1 = new JLabel("Senha");
		lblSenha_1.setFont(new Font("3ds", Font.BOLD, 15));
		lblSenha_1.setBounds(198, 195, 46, 14);
		contentPane.add(lblSenha_1);

	}
}
