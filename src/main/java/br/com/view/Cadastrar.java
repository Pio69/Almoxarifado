/*
*SENAI 
*PSIN
*MI-66
*Objetivo: Vizualisação dos campos necessarios para o cadastro de Usuario
*Autores: Guilherme Witkosky, Kelvin Schneider, Leonardo Pio, Rafael Adriano e Vinicius Silva Sena
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

import br.com.comboBoxModel.ComboBoxUfModel;
import br.com.controller.PessoaController;
import br.com.dao.GenericDaoUf;
import br.com.model.Endereco;
import br.com.model.Pessoa;
import br.com.model.UnidadeFederativa;
import br.com.model.Usuario;
import br.com.util.JSONReader;

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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

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
	private JTextField txtCep;
	private JTextField txtLogradouro;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField txtLocalidade;
	private JComboBox cmbUF; 

	private boolean validouCep;
	
	/*
	 *
	 * Objetivo: Carregar os componentes presentes na tela
	 *
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Cadastrar(Pessoa pessoa) {
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
		txtNome.setBounds(10, 36, 254, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		txtCpf = new JTextField();
		txtCpf.setHorizontalAlignment(SwingConstants.CENTER);
		txtCpf.setFont(new Font("3ds", Font.PLAIN, 14));
		txtCpf.setBounds(10, 92, 254, 20);
		contentPane.add(txtCpf);
		txtCpf.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmail.setFont(new Font("3ds", Font.PLAIN, 14));
		txtEmail.setBounds(10, 148, 254, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		// LABELS PARA CAMPOS DE TEXTO
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("3ds", Font.BOLD, 15));
		lblNome.setBounds(10, 11, 64, 14);
		contentPane.add(lblNome);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("3ds", Font.BOLD, 15));
		lblCpf.setBounds(10, 67, 64, 14);
		contentPane.add(lblCpf);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("3ds", Font.BOLD, 15));
		lblEmail.setBounds(10, 123, 64, 14);
		contentPane.add(lblEmail);

		// BOTAO DE CADASTRAR OS DADOS
		JButton btnInsert = new JButton("Cadastrar");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Pessoa pessoas = new Pessoa();
				Usuario usuario = new Usuario();
				Endereco endereco = new Endereco();

				/*
				 * Inseri os dados coletados na model de usuario
				 */
				usuario.setEmail(txtEmail.getText());
				usuario.setSenha(String.copyValueOf(pswSenha.getPassword()));
				usuario.setTipo(comboBox.getSelectedItem().toString());
				
				endereco.setCep(txtCep.getText());
				endereco.setBairro(txtBairro.getText());
				endereco.setComplemento(txtComplemento.getText());
				endereco.setLocalidade(txtLocalidade.getText());
				endereco.setLogradouro(txtLogradouro.getText());
				endereco.setUnidadeFederativa(((UnidadeFederativa) cmbUF.getSelectedItem()));

				pessoas.setId(0);
				pessoas.setNome(txtNome.getText());
				pessoas.setCpf(txtCpf.getText());
				pessoas.setEntrada(txtEntrada.getText());
				pessoas.setSaida(txtSaida.getText());
				pessoas.setUsuario(usuario);
				pessoas.setEndereco(endereco);

				// VERIFICA SE ACAO FOI REALIZADA PARA APAGAR OS CAMPOS
				if (PessoaController.genericFunction(pessoas, Cadastrar.this)) {
					PessoaController.clear(txtNome, txtCpf, txtEmail, pswSenha, txtEntrada, txtSaida, txtCep, txtLogradouro, txtComplemento, txtBairro, txtLocalidade);
				}

			}
		});
		btnInsert.setForeground(Color.WHITE);
		btnInsert.setBackground(Color.BLACK);
		btnInsert.setFont(new Font("3ds", Font.BOLD, 14));
		btnInsert.setBounds(506, 425, 118, 23);
		contentPane.add(btnInsert);

		// LIMPA OS CAMPOS
		JButton btnClear = new JButton("Limpar");
		btnClear.setForeground(Color.WHITE);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PessoaController.clear(txtNome, txtCpf, txtEmail, pswSenha, txtEntrada, txtSaida, txtCep, txtLogradouro, txtComplemento, txtBairro, txtLocalidade);
			}
		});
		btnClear.setBackground(Color.BLACK);
		btnClear.setFont(new Font("3ds", Font.BOLD, 14));
		btnClear.setBounds(395, 425, 101, 23);
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
		btnReturn.setBounds(10, 425, 89, 23);
		contentPane.add(btnReturn);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("3ds", Font.BOLD, 15));
		lblSenha.setBounds(10, 179, 64, 14);
		contentPane.add(lblSenha);

		pswSenha = new JPasswordField();
		pswSenha.setHorizontalAlignment(SwingConstants.CENTER);
		pswSenha.setFont(new Font("3ds", Font.PLAIN, 14));
		pswSenha.setBounds(10, 204, 254, 20);
		contentPane.add(pswSenha);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("3ds", Font.BOLD, 15));
		lblTipo.setBounds(10, 346, 64, 14);
		contentPane.add(lblTipo);

		// COMBOBOX DE TIPO DE USUARIO
		comboBox = new JComboBox();
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("3ds", Font.PLAIN, 14));
		comboBox.addItem("Professor");
		comboBox.addItem("Gerente");
		comboBox.setBounds(10, 369, 254, 20);
		contentPane.add(comboBox);

		JLabel lblEntrada = new JLabel("Entrada");
		lblEntrada.setFont(new Font("3ds", Font.BOLD, 15));
		lblEntrada.setBounds(10, 235, 64, 14);
		contentPane.add(lblEntrada);

		txtEntrada = new JTextField();
		txtEntrada.setHorizontalAlignment(SwingConstants.CENTER);
		txtEntrada.setFont(new Font("3ds", Font.PLAIN, 14));
		txtEntrada.setColumns(10);
		txtEntrada.setBounds(10, 260, 254, 20);
		contentPane.add(txtEntrada);

		JLabel lblSaida = new JLabel("Saida");
		lblSaida.setFont(new Font("3ds", Font.BOLD, 15));
		lblSaida.setBounds(10, 291, 64, 14);
		contentPane.add(lblSaida);

		txtSaida = new JTextField();
		txtSaida.setHorizontalAlignment(SwingConstants.CENTER);
		txtSaida.setFont(new Font("3ds", Font.PLAIN, 14));
		txtSaida.setColumns(10);
		txtSaida.setBounds(10, 316, 254, 20);
		contentPane.add(txtSaida);

		JLabel lblCep = new JLabel("CEP");
		lblCep.setFont(new Font("Dialog", Font.BOLD, 15));
		lblCep.setBounds(322, 13, 64, 14);
		contentPane.add(lblCep);

		txtCep = new JTextField();
		txtCep.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (!validouCep) {

					JSONReader reader = new JSONReader();
					Endereco estado = reader.ReaderJSONViaCep(txtCep.getText().trim());

					if (estado != null) {
						txtLogradouro.setText(estado.getLogradouro());
						txtComplemento.setText(estado.getComplemento());
						txtBairro.setText(estado.getBairro());
						
						ComboBoxUfModel comboBoxUfModel = new ComboBoxUfModel();
						comboBoxUfModel.setSelectedItem(estado.getUnidadeFederativa());
						cmbUF.setModel(comboBoxUfModel);
						txtLocalidade.setText(estado.getLocalidade());
						
					}

					validouCep = true;
				}
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				validouCep = false;
			}
		});
		txtCep.setHorizontalAlignment(SwingConstants.CENTER);
		txtCep.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtCep.setColumns(10);
		txtCep.setBounds(322, 38, 254, 20);
		contentPane.add(txtCep);

		JLabel lblLogradouro = new JLabel("Logradouro");
		lblLogradouro.setFont(new Font("Dialog", Font.BOLD, 15));
		lblLogradouro.setBounds(322, 67, 101, 14);
		contentPane.add(lblLogradouro);

		txtLogradouro = new JTextField();
		txtLogradouro.setHorizontalAlignment(SwingConstants.CENTER);
		txtLogradouro.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtLogradouro.setColumns(10);
		txtLogradouro.setBounds(322, 92, 254, 20);
		contentPane.add(txtLogradouro);

		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setFont(new Font("Dialog", Font.BOLD, 15));
		lblComplemento.setBounds(322, 123, 101, 14);
		contentPane.add(lblComplemento);

		txtComplemento = new JTextField();
		txtComplemento.setHorizontalAlignment(SwingConstants.CENTER);
		txtComplemento.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(322, 148, 254, 20);
		contentPane.add(txtComplemento);

		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setFont(new Font("Dialog", Font.BOLD, 15));
		lblBairro.setBounds(322, 179, 64, 14);
		contentPane.add(lblBairro);

		txtBairro = new JTextField();
		txtBairro.setHorizontalAlignment(SwingConstants.CENTER);
		txtBairro.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtBairro.setColumns(10);
		txtBairro.setBounds(322, 204, 254, 20);
		contentPane.add(txtBairro);

		JLabel lblUf = new JLabel("UF");
		lblUf.setFont(new Font("Dialog", Font.BOLD, 15));
		lblUf.setBounds(322, 291, 64, 14);
		contentPane.add(lblUf);

		JLabel lblCidade = new JLabel("Localidade");
		lblCidade.setFont(new Font("Dialog", Font.BOLD, 15));
		lblCidade.setBounds(322, 235, 101, 14);
		contentPane.add(lblCidade);

		txtLocalidade = new JTextField();
		txtLocalidade.setHorizontalAlignment(SwingConstants.CENTER);
		txtLocalidade.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtLocalidade.setColumns(10);
		txtLocalidade.setBounds(322, 260, 254, 20);
		contentPane.add(txtLocalidade);

		cmbUF = new JComboBox();
		cmbUF.setFont(new Font("Dialog", Font.PLAIN, 14));
		cmbUF.setBackground(Color.WHITE);
		
		ComboBoxUfModel cmbUfmodel = new ComboBoxUfModel();
	
		cmbUF.setBounds(322, 316, 254, 20);
		cmbUF.setModel(cmbUfmodel);
				
		contentPane.add(cmbUF);

		validouCep = true;

	}
}
